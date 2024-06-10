package Server;


import java.io.*;


public class NetWorkProtocol {
    public void receive(String data,ObjectInputStream ois) throws IOException{
        while (true){

            data = ois.readUTF();
        }
    }


    public void send(String data,ObjectOutputStream oos) throws IOException{
        oos.writeUTF(data);
        oos.flush();
    }


}
