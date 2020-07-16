package Socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static Socket clientSocket;
    class refreshThread extends Thread{
        @Override
        public void run() {
            try {
                refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void refresh() throws IOException {
        DataInputStream dis = null;
        dis =new DataInputStream(clientSocket.getInputStream());
        while (true){
            String str = dis.readUTF();

        }
    }
}
