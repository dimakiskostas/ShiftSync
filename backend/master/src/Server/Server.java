package Server;


import Config.Config;
import Emploey.LoginChecker;
import Shifts.Shifts;
import Threads.ThreadForClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final NetWorkProtocol protocol = new NetWorkProtocol();
    private ServerSocket providerSocket = null;
    LoginChecker loginChecker = new LoginChecker();
    Shifts shifts = new Shifts();

    public void start() {

        try {
            System.out.print("Creating server socket at port : " + Config.PORT + " \n");
            providerSocket = new ServerSocket(Config.PORT);

            while (true){
                System.out.print("Waiting for client");

                Socket clientsocket = providerSocket.accept();


                InputStream inputStream = clientsocket.getInputStream();
                OutputStream outputStream = clientsocket.getOutputStream();

                ObjectInputStream ois = new ObjectInputStream(inputStream);
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);

                System.out.print("\n");
                System.out.println("Handshake for connection");

                System.out.println("Sending welcome message ... ");

                oos.writeUTF("WHO ARE YOU");
                oos.flush();

                String token = ois.readUTF();

                if (token.toUpperCase().startsWith("I AM CLIENT")) {
                    System.out.println("A client has connected");
                    oos.writeUTF("HELLO");
                    oos.flush();

                    String username = ois.readUTF();
                    String password = ois.readUTF();

                    System.out.println(username);
                    System.out.println(password);

                    ThreadForClient threadForClient = new ThreadForClient(this, protocol, token, oos, ois, username, password);
                    threadForClient.start();

                }

                else {
                    System.out.println("Unkwown client tried to connect ...  (handshake failed) ");
                    //oos.writeUTF("GO AWAY");
                    System.out.println("GO AWAY");
                }

            }

        } catch (IOException ioException) {
            if (!ioException.getMessage().equalsIgnoreCase("Socket closed")) {
                ioException.printStackTrace();
            }
        }

    }

    public void stop() {
        try {
            if (providerSocket != null) {
                System.out.println("Closing server socket at port: " + Config.PORT);
                providerSocket.close();
            }
        } catch (IOException ioException) {
        }
    }
}
