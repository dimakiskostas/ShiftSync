package Emploey;


import Server.Server;
import Threads.AcceptThread;

import java.util.Scanner;

public class main {
    public static void main(String args[]) {


        Server server = new Server();

        AcceptThread thread = new AcceptThread(server);

        thread.start();


        System.out.print("Press enter to shut down the server");

        new Scanner(System.in).nextLine();

        thread.end();

        System.out.print("Shut down completed");

        Scanner in = new Scanner(System.in);


    }
}



