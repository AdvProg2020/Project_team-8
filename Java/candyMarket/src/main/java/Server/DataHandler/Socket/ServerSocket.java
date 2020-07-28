package Server.DataHandler.Socket;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSocket {
    public static java.net.ServerSocket serverSocket;
    public static ArrayList<Socket> clientSockets;
    public static int port = 6667;
    static class ClientListeningThread extends Thread{
        Socket clientSocket;
        public ClientListeningThread(Socket clientSocket){
            this.clientSocket = clientSocket;
        }
        @Override
        public void run() {
            try {
                listenToClient(clientSocket);
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
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
                    clientSockets.add(clientSocket);
                    ClientListeningThread clientListeningThread = new ClientListeningThread(clientSocket);
                    clientListeningThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void listenToClient(Socket clientSocket) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println(clientSocket+"connected");
        MessageHandler.getMessageListener(clientSocket);
    }
    public static void connectToClients() throws IOException {
        serverSocket=new java.net.ServerSocket(port);
        clientSockets = new ArrayList<>();
        ClientThread clientThread = new ClientThread();
        clientThread.start();
    }
}
