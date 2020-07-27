package BankServer.src;

import java.util.Random;

public class Account {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String accountNum;
    private int balance;

    public Account(String firstName, String lastName, String userName, String password) {
        this.balance = 0;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        createRandomAccountNum();
        BankServer.accountsWithTokens.put(this, null);
    }

    private void createRandomAccountNum(){
        String accountNum;
        while (true){
            Random random = new Random();
            accountNum = String.valueOf(random.nextInt(89999)+10000);
            if(!isAccountWithNumber(accountNum))
                break;
        }
        this.accountNum = accountNum;
    }

    public static boolean isAccountWithNumber(String number){
        for (Account account : BankServer.accountsWithTokens.keySet()) {
            if(account.getAccountNum().equals(number)) return true;
        }
        return false;
    }

    public static boolean isAccountWithUserName(String userName){
        for (Account account : BankServer.accountsWithTokens.keySet()) {
            if(account.getUserName().equals(userName)) return true;
        }
        return false;
    }

    public static boolean isPasswordCorrect(String username, String password) {
        for (Account account : BankServer.accountsWithTokens.keySet()) {
            if (account.getUserName().equals(username)) {
                if (account.getPassword().equals(password))
                    return true;
                break;
            }
        }
        return false;
    }

    public static Account getAccountByUsername(String userName) {
        for (Account account : BankServer.accountsWithTokens.keySet()) {
            if (account.getUserName().equals(userName))
                return account;
        }
        return null;
    }

    public static Account getAccountById(String id) {
        for (Account account : BankServer.accountsWithTokens.keySet()) {
            if (account.getAccountNum().equals(id))
                return account;
        }
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public int getBalance() {
        return balance;
    }

    public void addBalance(int money) {
        this.balance += money;
    }
}
