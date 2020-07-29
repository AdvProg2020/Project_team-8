package Server.DataHandler.Socket;

import BothUtl.PathHandler;
import Client.DataHandler.ClientSocket;
import Server.Model.FileGood;
import Server.Model.ManageInfo;
import Server.Model.User;
import Server.Model.UserHandler;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;

public class MessageHandler {
    public static void sendSetDataMessage(Socket clientSocket,String json) throws IOException {
        DataOutputStream dos = null;
        dos =new DataOutputStream(clientSocket.getOutputStream());
        dos.writeUTF("S."+json);
        dos.flush();
    }
    public static void getMessageListener(Socket clientSocket) throws IOException, ClassNotFoundException, InterruptedException {
        DataInputStream dis = null;
        dis =new DataInputStream(clientSocket.getInputStream());
        String input;
        while (true){
            Thread.sleep(1);
            try {
                input = dis.readUTF();
            }catch (Exception e){
                continue;
            }
            if(input.startsWith("C.")){
                if(input.startsWith("C.setData")){
                    String[] inputs = input.split("#");
                    String className = inputs[2];
                    String json = inputs[3];
                    sendSetDataMessageToAllClients(clientSocket,json,className);
                    JsonHandler.updateOrSaveData(json,className);
                }
                else if(input.startsWith("C.getDataById")){
                    String[] inputs = input.split("#");
                    String id = inputs[1];
                    String className = inputs[3];
                    JsonHandler.sendDataById(className,id,clientSocket);
                }
                if(input.startsWith("C.deleteDataById")){
                    String[] inputs = input.split("#");
                    String className = inputs[3];
                    String id = inputs[1];
                    sendDeleteDataMessageToAllClients(clientSocket,id,className);
                    DataAccessor.deleteDataById(className,id);
                }
                else if(input.startsWith("C.getAllData")){
                    String[] inputs = input.split("#");
                    String className = inputs[2];
                    JsonHandler.sendAllData(className,clientSocket);
                }
                else if(input.startsWith("C.getLoginUsers")){
                    String json = JsonHandler.gson.toJson(UserHandler.onlineUsers);
                    DataOutputStream dos =new DataOutputStream(clientSocket.getOutputStream());
                    dos.writeUTF("S.login#"+json);
                    dos.flush();
                }
                else if(input.startsWith("C.disconnected")){
                    ServerSocket.clientSockets.remove(clientSocket);
                    System.out.println(clientSocket+"disconnected");
                }
                else if(input.startsWith("C.Bank")){
                    String[] inputs = input.split("#");
                    String response = BankClientSocket.sendMessage(inputs[1]);
                    DataOutputStream dos =new DataOutputStream(clientSocket.getOutputStream());
                    dos.writeUTF("S.Bank#"+response);
                    dos.flush();
                }
                else if(input.startsWith("C.uploadFile")){
                    String fileName = input.split("#")[1];
                    String fileType = input.split("#")[2];
                    int bytesRead;
                    int current = 0;
                    FileOutputStream fos = null;
                    BufferedOutputStream bos = null;
                    try {

                        // receive file
                        byte [] mybytearray  = new byte [20002];
                        InputStream is = clientSocket.getInputStream();
                        fos = new FileOutputStream(PathHandler.resourcePath+"FileGoods\\"+fileName+"."+fileType);
                        bos = new BufferedOutputStream(fos);
                        bytesRead = is.read(mybytearray,0,mybytearray.length);
                        current = bytesRead;

                        do {
                            bytesRead =
                                    is.read(mybytearray, current, (mybytearray.length-current));
                            if(bytesRead >= 0) current += bytesRead;
                        } while(bytesRead > -1);

                        bos.write(mybytearray, 0 , current);
                        bos.flush();
                        System.out.println("File " + PathHandler.resourcePath+"FileGoods\\myTestTxt.txt"
                                + " downloaded (" + current + " bytes read)");
                    }
                    finally {
                        if (fos != null) fos.close();
                        if (bos != null) bos.close();
                    }
                }
                else if(input.startsWith("C.downloadFile")){
                    String fileGoodName = input.split("#")[1];
                    FileGood fileGood = FileGood.getFileGoodByName(fileGoodName);
                    String fileName = fileGood.getName();
                    String fileType = fileGood.getFileType();
                    String path = PathHandler.resourcePath+"FileGoods\\"+fileName+"."+fileType;
                    DataOutputStream dos;
                    try {
                        dos=new DataOutputStream(ClientSocket.clientSocket.getOutputStream());
                        dos.writeUTF("S.uploadFile#"+fileName+"#"+fileType);
                        dos.flush();
                        FileInputStream fis = null;
                        BufferedInputStream bis = null;
                        try {
                            File myFile = new File(path);
                            byte[] mybytearray = new byte[(int) myFile.length()];
                            fis = new FileInputStream(myFile);
                            bis = new BufferedInputStream(fis);
                            bis.read(mybytearray, 0, mybytearray.length);
                            dos.write(mybytearray, 0, mybytearray.length);
                            dos.flush();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (bis != null) {
                                try {
                                    bis.close();
                                    dos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void sendDeleteDataMessageToAllClients(Socket senderClient,String id,String className) throws IOException {
        DataOutputStream dos = null;
        for (Socket clientSocket : ServerSocket.clientSockets) {
            if (clientSocket != senderClient) {
                try {
                    dos = new DataOutputStream(clientSocket.getOutputStream());
                    dos.writeUTF("S.deleteDataById#" + id + "#from#" + className);
                    dos.flush();
                }catch (Exception e){
                ServerSocket.clientSockets.remove(clientSocket);
            }
            }
        }
    }
    public static void sendSetDataMessageToAllClients(Socket senderClient,String json,String className) throws IOException {
        DataOutputStream dos = null;
        for (Socket clientSocket : ServerSocket.clientSockets) {
            if (clientSocket != senderClient) {
                dos =new DataOutputStream(clientSocket.getOutputStream());
                try {
                    dos.writeUTF("S.setData#to#" + className + "#" + json);
                    dos.flush();
                }catch (Exception e){
                    ServerSocket.clientSockets.remove(clientSocket);
                }
            }
        }
    }
}
