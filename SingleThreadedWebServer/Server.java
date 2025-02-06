package SingleThreadedWebServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void launch() {
        int port = 8081;

        try {

            // opening a server socket on the defined port
            ServerSocket serverSocket = new ServerSocket(port);
            // if ideal for 1st 10 sec close the socket
            serverSocket.setSoTimeout(50000);

            while (true) {

                System.out.println("Server is listening...\n");
                Socket acceptedConnection = serverSocket.accept();
                System.out.println("Server is connected...\n");
                System.out.println(acceptedConnection.getRemoteSocketAddress());

                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(),true);
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnection.getInputStream()));

                toClient.println("Hello from Server !!!");
                System.out.println("Response from Client "+fromClient.readLine());

                toClient.close();
                fromClient.close();
                serverSocket.close();
                acceptedConnection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.launch();

    }
}