package NetWorkProtocol;

import java.io.*;
import java.sql.Time;


public class Network {

    public void receive(String str, ObjectInputStream ois) throws IOException {
        while (true) {
            String data = ois.readUTF();
            if (data.equalsIgnoreCase("END")) {
                break;
            }
        }
    }

    public void sendString(String s, ObjectOutputStream oos) throws IOException {
        oos.writeUTF(s);
        oos.flush();

        oos.writeUTF("END");
        oos.flush();
    }

    public void sendStrings(String s, String t, ObjectOutputStream oos) throws IOException {
        oos.writeUTF(s);
        oos.writeUTF(t);
        oos.flush();

        oos.writeUTF("END");
        oos.flush();
    }

    public void sendStrings(String s, String t, String g, ObjectOutputStream oos) throws IOException {
        oos.writeUTF(s);
        oos.writeUTF(t);
        oos.writeUTF(g);
        oos.flush();

        oos.writeUTF("END");
        oos.flush();
    }

    public void sendStrings(String s, String t, String g,String f2, String f4, ObjectOutputStream oos) throws IOException {
        oos.writeUTF(s);
        oos.writeUTF(t);
        oos.writeUTF(g);
        oos.writeUTF(f2);
        oos.writeUTF(f4);
        oos.flush();

        oos.writeUTF("END");
        oos.flush();
    }

    public void sendavailability(String s, Time t, Time g, ObjectOutputStream oos) throws IOException {
        oos.writeUTF(s);
        oos.writeObject(t);
        oos.writeObject(g);
        oos.flush();


        oos.writeUTF("END");
        oos.flush();
    }


    public void sendnum(int i, ObjectOutputStream oos) throws IOException {
        oos.write(i);
        oos.flush();

        oos.writeUTF("END");
        oos.flush();
    }

    public void sendStrings(String date, String p, String l, String q,String username, String password, ObjectOutputStream oos) throws IOException {
        oos.writeUTF(date);
        oos.writeUTF(p);
        oos.writeUTF(l);
        oos.writeUTF(q);
        oos.writeUTF(username);
        oos.writeUTF(password);

        oos.flush();


        oos.writeUTF("END");
        oos.flush();

    }
}
