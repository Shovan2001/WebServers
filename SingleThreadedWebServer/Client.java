package SingleThreadedWebServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public void launch() {

        int port = 8081;

        try {

            InetAddress address = InetAddress.getByName("localhost");
            Socket socket = new Socket(address, port);

            PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            toServer.println("Hello from Client !!");
            String line=fromServer.readLine();
            System.out.println("Response from Server: "+line);

            toServer.close();
            fromServer.close();        
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.launch();

    }
}