package Server;

import Client.Model.ManageInfo;
import Server.DataHandler.DataBase.DBHandler;
import Server.DataHandler.Socket.BankClientSocket;
import Server.DataHandler.Socket.ServerSocket;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        while (true){
            if(BankClientSocket.connectToBankServer())
                break;
        }
        DBHandler.loadData();
        ServerSocket.connectToClients();
    }
}
