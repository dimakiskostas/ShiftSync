package Client;

import NetWorkProtocol.Network;
import org.w3c.dom.Text;

import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.util.Date;

public class Client {
    private Network protocol = new Network();

    public String sendFileSignIn(String serverIP, int serverPort, String s, String t) throws IOException {
        Socket clientSocket = new Socket(serverIP, serverPort);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ObjectInputStream ois = new ObjectInputStream(inputStream);

        String prompt = ois.readUTF();

        oos.writeUTF("I AM CLIENT FOR SIGN IN");
        oos.flush();

        String response = ois.readUTF();

        if (!response.equalsIgnoreCase("HELLO")) {
            System.out.println("Server rejected the connection");
        }

        protocol.sendStrings(s, t, oos);
        String res = ois.readUTF();
        String res2 = ois.readUTF();

        return res + res2;
    }



    public String sendFileForName(String serverIP, int serverPort, String s, String t) throws IOException {
        Socket clientSocket = new Socket(serverIP, serverPort);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ObjectInputStream ois = new ObjectInputStream(inputStream);

        String prompt = ois.readUTF();

        oos.writeUTF("I AM CLIENT FOR SIGN IN");
        oos.flush();

        String response = ois.readUTF();

        if (!response.equalsIgnoreCase("HELLO")) {
            System.out.println("Server rejected the connection");
        }

        protocol.sendStrings(s, t, oos);
        String res = ois.readUTF();
        String res2 = ois.readUTF();

        return res2;
    }


    public String sendFileForPhone(String serverIP, int serverPort, String s, String t) throws IOException {
        Socket clientSocket = new Socket(serverIP, serverPort);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ObjectInputStream ois = new ObjectInputStream(inputStream);

        String prompt = ois.readUTF();

        oos.writeUTF("I AM CLIENT FOR PHONE");
        oos.flush();

        String response = ois.readUTF();

        if (!response.equalsIgnoreCase("HELLO")) {
            System.out.println("Server rejected the connection");
        }

        protocol.sendStrings(s, t, oos);
        String res2 = ois.readUTF();

        return res2;
    }

    public String sendrequest(String serverIP, int serverPort, String s, String t) throws IOException {
        Socket clientSocket = new Socket(serverIP, serverPort);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ObjectInputStream ois = new ObjectInputStream(inputStream);

        String prompt = ois.readUTF();

        oos.writeUTF("I AM CLIENT FOR SHIFTS");
        oos.flush();

        String response = ois.readUTF();

        if (!response.equalsIgnoreCase("HELLO")) {
            System.out.println("Server rejected the connection");
        }

        protocol.sendStrings(s, t, oos);

        String res3= ois.readUTF();
        System.out.println(res3);

        return res3;
    }

    public String sendavailabity(String serverIP, int serverPort, String s, String t, String g, String username, String password) throws IOException {
        Socket clientSocket = new Socket(serverIP, serverPort);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ObjectInputStream ois = new ObjectInputStream(inputStream);

        String prompt = ois.readUTF();

        oos.writeUTF("I AM CLIENT FOR AVAILABILITY");
        oos.flush();

        String response = ois.readUTF();

        if (!response.equalsIgnoreCase("HELLO")) {
            System.out.println("Server rejected the connection");
        }


        protocol.sendStrings(username, password, s, t, g, oos);

        String res3= ois.readUTF();
        System.out.println(res3);

        return res3;


    }

    public String sendrequestavailability(String serverIP, int serverPort, String s, String t) throws IOException {
        Socket clientSocket = new Socket(serverIP, serverPort);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ObjectInputStream ois = new ObjectInputStream(inputStream);

        String prompt = ois.readUTF();

        oos.writeUTF("I AM CLIENT FOR CHECK");
        oos.flush();

        String response = ois.readUTF();

        if (!response.equalsIgnoreCase("HELLO")) {
            System.out.println("Server rejected the connection");
        }

        protocol.sendStrings(s, t, oos);

        String res3= ois.readUTF();
        System.out.println(res3);

        return res3;

    }

    public String sendalert(String serverIP, int serverPort, String t, String p, String l) throws IOException {
        Socket clientSocket = new Socket(serverIP, serverPort);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ObjectInputStream ois = new ObjectInputStream(inputStream);

        String prompt = ois.readUTF();

        oos.writeUTF("I AM CLIENT FOR ALERT");
        oos.flush();

        String response = ois.readUTF();

        if (!response.equalsIgnoreCase("HELLO")) {
            System.out.println("Server rejected the connection");
        }

        protocol.sendStrings(t, p, l, oos);


        String res3= ois.readUTF();
        System.out.println(res3);

        return res3;
    }


    public String sendreport(String serverIP, int serverPort, String date, String p, String l, String q, String username, String password) throws IOException {
        Socket clientSocket = new Socket(serverIP, serverPort);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ObjectInputStream ois = new ObjectInputStream(inputStream);

        String prompt = ois.readUTF();

        oos.writeUTF("I AM CLIENT FOR REPORT");
        oos.flush();

        String response = ois.readUTF();

        if (!response.equalsIgnoreCase("HELLO")) {
            System.out.println("Server rejected the connection");
        }

        protocol.sendStrings(date, p, l, q, username, password, oos);


        String res3= ois.readUTF();
        System.out.println(res3);

        return res3;

    }

    public String sendDeleteAvailability(String serverIP, int serverPort, String t, String p, String l) throws IOException {
        Socket clientSocket = new Socket(serverIP, serverPort);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ObjectInputStream ois = new ObjectInputStream(inputStream);

        String prompt = ois.readUTF();

        oos.writeUTF("I AM CLIENT FOR DELETE");
        oos.flush();

        String response = ois.readUTF();

        if (!response.equalsIgnoreCase("HELLO")) {
            System.out.println("Server rejected the connection");
        }

        protocol.sendStrings(t, p, l, oos);


        String res3= ois.readUTF();
        System.out.println(res3);


        return res3;
    }




    public void login(String serverIP, int serverPort)  throws IOException  {

        Socket clientSocket = new Socket(serverIP, serverPort);

        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        ObjectInputStream ois = new ObjectInputStream(inputStream);

        String prompt = ois.readUTF();

        oos.writeUTF("I AM CLIENT");
        oos.flush();
        String response = ois.readUTF();

        if (!response.equalsIgnoreCase("HELLO")) {
            System.out.println("Server rejected the connection");
        } else {


        }
    }


}
