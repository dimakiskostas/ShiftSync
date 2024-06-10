package Threads;

import Server.Server;

public class AcceptThread extends Thread{


    private Server server;

    public AcceptThread(Server server){
        this.server = server;
    }


    public void run(){
        super.run();

        server.start();
    }


    public void end(){
        server.stop();
    }

}
