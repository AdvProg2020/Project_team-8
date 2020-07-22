package Server;

import Server.DataHandler.DataBase.DBHandler;
import Server.DataHandler.Socket.ServerSocket;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        DBHandler.loadData();
        ServerSocket.connectToClients();
    }
}
