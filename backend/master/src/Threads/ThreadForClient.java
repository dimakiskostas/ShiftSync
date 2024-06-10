package Threads;

import Emploey.LoginChecker;
import Server.NetWorkProtocol;
import Server.Server;
import Shifts.Shifts;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Time;

public class ThreadForClient extends Thread {


    private Server server;
    private NetWorkProtocol protocol;
    private final String token;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;
    private final String username;
    private final String password;


    public ThreadForClient(Server server, NetWorkProtocol protocol, String token, ObjectOutputStream oos, ObjectInputStream ois, String username, String password) {

        this.server = server;
        this.protocol = protocol;
        this.token = token;
        this.oos = oos;
        this.ois = ois;
        this.username = username;
        this.password = password;
    }


    public void run() {

        super.run();
        LoginChecker loginChecker = new LoginChecker();
        Shifts shifts = new Shifts();

        try {

            if (token.equalsIgnoreCase("I AM CLIENT FOR SIGN IN")) {
                if (loginChecker.checkif(username, password)) {
                    oos.writeUTF("Welcome");
                    oos.flush();

                    oos.writeUTF(shifts.printemploeyinfo(username));
                    oos.flush();


                } else {
                    oos.writeUTF("Not Welcome");
                    oos.flush();

                    oos.writeUTF("Wrong username or password");
                    oos.flush();
                }

            }


            if (token.equalsIgnoreCase("I AM CLIENT FOR AVAILABILITY")) {

                String date = ois.readUTF();
                String time1 = ois.readUTF();
                String time2 = ois.readUTF();

                System.out.print("->" + date +" DATE " +time1 + " " + time2+ " ");

                if (loginChecker.checkif(username, password)) {
                    if(shifts.insertavailability(username, date, time1, time2)){
                        oos.writeUTF("Availability added!");
                        oos.flush();
                    }else{
                        System.out.print("NOPE");
                    }


                } else {
                    oos.writeUTF("Not Welcome");
                    oos.flush();
                }

            }


            if (token.equalsIgnoreCase("I AM CLIENT FOR SHIFTS")) {

                System.out.println(username);
                System.out.println(password);

                if (loginChecker.checkif(username, password)) {

                    oos.writeUTF(shifts.printemploeyshift(username));
                    oos.flush();

                } else {
                    oos.writeUTF("Not Welcome");
                    oos.flush();
                }
            }


            if (token.equalsIgnoreCase("I AM CLIENT FOR CHECK")) {

                System.out.println(username);
                System.out.println(password);

                if (loginChecker.checkif(username, password)) {

                    oos.writeUTF(shifts.printemplo eyavailability(username));
                    oos.flush();

                } else {
                    oos.writeUTF("Not Welcome");
                    oos.flush();
                }
            }

            if (token.equalsIgnoreCase("I AM CLIENT FOR PHONE")) {

                System.out.println(username);
                System.out.println(password);

                if (loginChecker.checkif(username, password)) {

                    oos.writeUTF(shifts.printemploeyPhone(username));
                    oos.flush();

                } else {
                    oos.writeUTF("Not Welcome");
                    oos.flush();
                }
            }

            //---------------------------------------------------------------------------------------


            if (token.equalsIgnoreCase("I AM CLIENT FOR DELETE")) {

                System.out.println(username);
                System.out.println(password);
                System.out.println("DELETE");

                String date = ois.readUTF();

                if (loginChecker.checkif(username, password)) {

                    if (shifts.deleteAvailability(username, date)) {
                        oos.writeUTF("Availability deleted!! ");
                        oos.flush();

                    } else {
                        oos.writeUTF("Please insert a the correct date of the availability and follow the date template");
                        oos.flush();
                    }
                }else{
                    oos.writeUTF("Not Welcome");
                    oos.flush();
                }
            }



            if (token.equalsIgnoreCase("I AM CLIENT FOR REPORT")) {

                System.out.println(username);
                System.out.println(password);
                System.out.println("---------------------------------------------------------------");

                String date = ois.readUTF();
                String sales = ois.readUTF();
                String stock = ois.readUTF();
                String comments = ois.readUTF();


                System.out.println(date);
                System.out.println(sales);
                System.out.println(stock);
                System.out.println(comments);

                if (loginChecker.checkif(username, password)) {
                    if(shifts.insertreport(username, date, sales, stock, comments)){
                        oos.writeUTF("Report has been added! ");
                        oos.flush();
                    }else{
                        oos.writeUTF(" Please insert a date that you had a shift and follow the date template");
                        oos.flush();
                    }

                } else {
                    oos.writeUTF("Chechif problem");
                    oos.flush();
                }
            }


            if (token.equalsIgnoreCase("I AM CLIENT FOR ALERT")) {
//
                String alter_report = ois.readUTF();

                System.out.println(username);
                System.out.println(password);
                System.out.println("Starting  +   " + alter_report);

                if (loginChecker.checkif(username, password)) {
                    if (shifts.insertalert(username, alter_report)) {
                        oos.writeUTF("Alert has been added!");
                        oos.flush();
                    }


                } else {
                    oos.writeUTF("Not Welcome");
                    oos.flush();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
