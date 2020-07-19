package Server;

import Server.DataHandler.DBHandler;
import Server.DataHandler.ServerSocket;

public class main {
    public static void main(String[] args) {
        DBHandler.loadData();
        ServerSocket.connectToClients();

    }
}
