package BankServer;

import com.sun.javafx.iio.ios.IosDescriptor;
import org.json.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class BankServer {
    public static HashMap<Account, Token> accountsWithTokens = new HashMap<>();
    public static ArrayList<Receipt> receipts = new ArrayList<>();
    private static int port = 6666;
    private static int clientNumbers = 0;

    private static void listenToClient(Socket clientSocket) throws IOException {
        while (true){
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
            String str;
            try {
                str = dis.readUTF();
            }
            catch (IOException e){
                break;
            }
            String[] inputs = str.split(" ");
            if(str.startsWith("create_account") && inputs.length == 6){
                if(!inputs[4].equals(inputs[5])){
                    dout.writeUTF("passwords do not match");
                    dout.flush();
                }
                else if(Account.isAccountWithUserName(inputs[3]))
                {
                    dout.writeUTF("username is not available");
                    dout.flush();
                }
                else {
                    Account account = new Account(inputs[1], inputs[2], inputs[3], inputs[4]);
                    dout.writeUTF(account.getAccountNum());
                    dout.flush();
                }
            }

            else if (str.startsWith("get_token") && inputs.length == 3) {
                if (!Account.isAccountWithUserName(inputs[1])) {
                    dout.writeUTF("invalid username or password");
                    dout.flush();
                }
                else if (!Account.isPasswordCorrect(inputs[1], inputs[2])) {
                    dout.writeUTF("invalid username or password");
                    dout.flush();
                }
                else {
                    accountsWithTokens.put(Account.getAccountByUsername(inputs[1]), new Token());
                    dout.writeUTF(accountsWithTokens.get(Account.getAccountByUsername(inputs[1])).getToken());
                    dout.flush();
                }
            }

            else if (str.startsWith("create_receipt") && inputs.length >= 6) {
                try {
                    int money = Integer.parseInt(inputs[3]);
                    String details = "";
                    for (int i = 7; i < inputs.length; i++) {
                        details += inputs[i] + " ";
                    }
                    if (!(inputs[2].equalsIgnoreCase("deposit") ||
                            inputs[2].equalsIgnoreCase("withdraw") ||
                            inputs[2].equalsIgnoreCase("move"))) {
                        dout.writeUTF("invalid receipt type");
                        dout.flush();
                    } else if ((inputs[2].equalsIgnoreCase("deposit") && !inputs[4].equals("-1")) ||
                            (inputs[2].equalsIgnoreCase("withdraw") && !inputs[5].equals("-1"))) {
                        dout.writeUTF("invalid parameters passed");
                        dout.flush();
                    } else if (!checkAccountIdValidation(inputs[4])) {
                        dout.writeUTF("source account id is invalid");
                        dout.flush();
                    } else if (!checkAccountIdValidation(inputs[5])) {
                        dout.writeUTF("dest account id is invalid");
                        dout.flush();
                    } else if ((inputs[2].equalsIgnoreCase("move") && inputs[4].equals("-1"))) {
                        dout.writeUTF("source account id is invalid");
                        dout.flush();
                    } else if ((inputs[2].equalsIgnoreCase("move") && inputs[5].equals("-1"))) {
                        dout.writeUTF("dest account id is invalid");
                        dout.flush();
                    } else if (inputs[4].equals(inputs[5])) {
                        dout.writeUTF("equal source and dest account");
                        dout.flush();
                    } else if ((inputs[2].equalsIgnoreCase("deposit") && inputs[5].equals("-1")) ||
                            (inputs[2].equalsIgnoreCase("withdraw") && inputs[4].equals("-1"))) {
                        dout.writeUTF("invalid account id");
                    } else if (!checkTokenValidation(inputs[1], inputs[4])) {
                        dout.writeUTF("token is invalid");
                        dout.flush();
                    } else if (checkTokenExpiration(inputs[1])) {
                        dout.writeUTF("token expired");
                        dout.flush();
                    } else if (!checkDetailsValidation(details)) {
                        dout.writeUTF("your input contains invalid characters");
                        dout.flush();
                    } else {
                        Receipt receipt = new Receipt(inputs[2], money, inputs[4], inputs[5], details);
                        dout.writeUTF(Integer.toString(receipt.getId()));
                        dout.flush();
                    }
                } catch (NumberFormatException e) {
                    dout.writeUTF("invalid money");
                    dout.flush();
                }
            }

            else if (str.startsWith("get_transactions") && inputs.length == 3) {
                if (!checkTokenValidation(inputs[1])) {
                    dout.writeUTF("token is invalid");
                    dout.flush();
                } else if (checkTokenExpiration(inputs[1])) {
                    dout.writeUTF("token expired");
                    dout.flush();
                } else if (!checkReceiptValidation(inputs[1], inputs[2])) {
                    dout.writeUTF("invalid receipt id");
                    dout.flush();
                } else {
                    Token token = Token.getTokenByTokenString(inputs[1]);
                    Account account = null;
                    for (Account account1 : accountsWithTokens.keySet()) {
                        if (accountsWithTokens.get(account1) == token) {
                            account = account1;
                            break;
                        }
                    }
                    String answer = "";
                    switch (inputs[2]) {
                        case "+":
                            for (Receipt receipt : receipts) {
                                if (receipt.getDestID().equals(account.getAccountNum())) {
                                    answer = buildTransaction(answer, receipt);
                                }
                            }
                            break;
                        case "-":
                            for (Receipt receipt : receipts) {
                                if (receipt.getSourceID().equals(account.getAccountNum()))
                                    answer = buildTransaction(answer, receipt);
                            }
                            break;
                        case "*":
                            for (Receipt receipt : receipts) {
                                if (receipt.getDestID().equals(account.getAccountNum()) ||
                                        receipt.getSourceID().equals(account.getAccountNum()))
                                    answer = buildTransaction(answer, receipt);
                            }
                            break;
                        default:
                            for (Receipt receipt : receipts) {
                                if (Integer.toString(receipt.getId()).equals(inputs[2])) {
                                    answer = buildTransaction(answer, receipt);
                                    break;
                                }
                            }
                            break;
                    }
                    dout.writeUTF(answer);
                    dout.flush();
                }
            }

            else if (str.startsWith("pay") && inputs.length == 2) {
                try {
                    int id = Integer.parseInt(inputs[1]);
                    if (!checkReceiptValidation(inputs[1])) {
                        dout.writeUTF("invalid receipt id");
                        dout.flush();
                    } else if (checkReceiptPaid(inputs[1])) {
                        dout.writeUTF("receipt is paid before");
                        dout.flush();
                    } else if (!checkEnoughMoney(inputs[1])) {
                        dout.writeUTF("source account does not have enough money");
                        dout.flush();
                    } else {
                        Receipt receipt = null;
                        for (Receipt receipt1 : receipts) {
                            if (receipt1.getId() == id) {
                                receipt = receipt1;
                                break;
                            }
                        }
                        Account account;
                        switch (receipt.getType()) {
                            case "deposit" :
                                account = Account.getAccountById(receipt.getDestID());
                                account.addBalance(receipt.getMoney());
                                receipt.setPaid(1);
                                break;
                            case "withdraw" :
                                account = Account.getAccountById(receipt.getSourceID());
                                account.addBalance((-1) * receipt.getMoney());
                                receipt.setPaid(1);
                                break;
                            case "move" :
                                account = Account.getAccountById(receipt.getSourceID());
                                Account account2 = Account.getAccountById(receipt.getDestID());
                                account.addBalance((-1) * receipt.getMoney());
                                account2.addBalance(receipt.getMoney());
                                receipt.setPaid(1);
                                break;
                        }
                        dout.writeUTF("done successfully");
                        dout.flush();
                    }
                }catch (NumberFormatException e) {
                    dout.writeUTF("invalid receipt id");
                    dout.flush();
                }
            }

            else if (str.startsWith("get_balance") && inputs.length == 2) {
                if (!checkTokenValidation(inputs[1])) {
                    dout.writeUTF("token is invalid");
                    dout.flush();
                } else if (checkTokenExpiration(inputs[1])) {
                    dout.writeUTF("token expired");
                    dout.flush();
                } else {
                    Token token = Token.getTokenByTokenString(inputs[1]);
                    Account account = null;
                    for (Account account1 : accountsWithTokens.keySet()) {
                        if (accountsWithTokens.get(account1) == token) {
                            account = account1;
                            break;
                        }
                    }
                    dout.writeUTF(Integer.toString(account.getBalance()));
                    dout.flush();
                }
            }

            else if (str.equalsIgnoreCase("exit")) {
                break;
            }

            else writeInvalidInput(clientSocket);
        }
    }

    private static boolean checkEnoughMoney(String receiptId) {
        Receipt receipt = null;
        for (Receipt receipt1 : receipts) {
            if (receipt1.getId() == Integer.parseInt(receiptId)) {
                receipt = receipt1;
                break;
            }
        }
        if (!receipt.getSourceID().equals("-1")) {
            Account account = Account.getAccountById(receipt.getSourceID());
            return account.getBalance() >= receipt.getMoney();
        }
        return true;
    }

    private static boolean checkReceiptPaid(String id) {
        for (Receipt receipt : receipts) {
            if (receipt.getId() == Integer.parseInt(id)) {
                if (receipt.getPaid() == 1)
                    return true;
                break;
            }
        }
        return false;
    }

    private static String buildTransaction(String answer, Receipt receipt) {
        JSONObject object;
        object = new JSONObject();
        object.put("receiptType", receipt.getType());
        object.put("money", receipt.getMoney());
        object.put("sourceAccountID", receipt.getSourceID());
        object.put("destAccountID", receipt.getDestID());
        object.put("description", receipt.getDetails());
        object.put("id", receipt.getId());
        object.put("paid", receipt.getPaid());
        answer += object.toString() + "*";
        return answer;
    }

    private static boolean checkReceiptValidation(String tok, String input) {
        try {
            int id = Integer.parseInt(input);
            Account account = null;
            Receipt receipt = null;
            for (Receipt receipt1 : receipts) {
                if (receipt1.getId() == id) {
                    receipt = receipt1;
                    break;
                }
            }
            if (receipt == null)
                return false;
            Token token = Token.getTokenByTokenString(tok);
            for (Account account1 : accountsWithTokens.keySet()) {
                if (accountsWithTokens.get(account1) == token) {
                    account = account1;
                    break;
                }
            }
            if (account == null)
                return false;
            return receipt.getSourceID().equals(account.getAccountNum()) || receipt.getDestID().equals(account.getAccountNum());
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static boolean checkReceiptValidation(String receipt) {
        try {
            for (Receipt receipt1 : receipts) {
                if (receipt1.getId() == Integer.parseInt(receipt))
                    return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean checkDetailsValidation(String details) {
        if (details.contains("*")) return false;
        if (details.contains("\"")) return false;
        return !details.contains(",");
    }

    private static boolean checkAccountIdValidation(String input) {
        if (input.equals("-1"))
            return true;
        for (Account account : accountsWithTokens.keySet()) {
            if (account.getAccountNum().equals(input))
                return true;
        }
        return false;
    }

    private static boolean checkTokenExpiration(String token) {
        for (Token value : accountsWithTokens.values()) {
            if (value.getToken().equals(token)) {
                return value.checkExpiration();
            }
        }
        return false;
    }

    private static boolean checkTokenValidation(String token, String id) {
        for (Token availableToken : accountsWithTokens.values()) {
            if (availableToken.getToken().equals(token)) {
                if (!id.equals("-1")) {
                    Account account = Account.getAccountById(id);
                    if (accountsWithTokens.get(account) == availableToken)
                        return true;
                } else
                    return true;
            }
        }
        return false;
    }

    private static boolean checkTokenValidation(String token) {
        for (Token availableToken : accountsWithTokens.values()) {
            if (availableToken.getToken().equals(token))
                    return true;
        }
        return false;
    }

    private static void writeInvalidInput(Socket clientSocket) throws IOException {
        DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
        dout.writeUTF("invalid input");
        dout.flush();
    }

    public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
                dout.writeUTF("hello "+ clientNumbers++);
                dout.flush();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            listenToClient(clientSocket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
    }
}
