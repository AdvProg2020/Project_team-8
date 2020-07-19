package Server.DataHandler;

import PathHandler.PathHandler;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerSocket {
    public static java.net.ServerSocket serverSocket;
    private static File managersFile = new File("Resources\\managers.txt");
    private static File buyersFile = new File("Resources\\buyers.txt");
    private static File sellersFile = new File("Resources\\sellers.txt");
    private static File usersFile = new File("Resources\\users.txt");
    private static File sellLogsFile = new File("Resources\\sellLogs.txt");
    private static File buyLogsFile = new File("Resources\\buyLogs.txt");
    private static File categoriesFile = new File("Resources\\categories.txt");
    private static File brandsFile = new File("Resources\\brands.txt");
    private static File requestsFile = new File("Resources\\requests.txt");
    private static File goodsFile = new File("Resources\\goods.txt");
    private static File discountsFile = new File("Resources\\discounts.txt");
    static class ClientListeningThread extends Thread{
        Socket clientSocket;
        public ClientListeningThread(Socket clientSocket){
            this.clientSocket = clientSocket;
        }
        @Override
        public void run() {
            try {
                listenToClient(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class ClientThread extends Thread{
        @Override
        public void run() {
            while (true) {
                Socket clientSocket;
                try {
                    clientSocket = serverSocket.accept();
                    ClientListeningThread clientListeningThread = new ClientListeningThread(clientSocket);
                    clientListeningThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void listenToClient(Socket clientSocket) throws IOException {

    }
    public static void connectToClients(){
        ClientThread clientThread = new ClientThread();
        clientThread.start();
    }
    private void sendAllDataToClient(Socket clientSocket) throws SQLException, ProtocolException {
       // HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/Manager/" + name).openConnection();
        //connection.setRequestMethod("GET");

    }
}
