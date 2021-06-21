package com.example.myapplication.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Model {
    private Socket mySocket;
    private PrintWriter out;
    private String ip;
    private int port ;
    private ExecutorService executor;

    public void setElevator(Double var) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                out.print("set /controls/flight/elevator " + var + "\r\n");
                out.flush();
            }
        });
    }



    public void setAileron(Double var) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                out.print("set /controls/flight/aileron " + var + "\r\n");
                out.flush();
            }
        });
    }
    public void setRudder(Double var) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                out.print("set /controls/flight/rudder " + var + "\r\n");
                out.flush();
            }
        });
    }
    public void setThrottle(Double var) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                out.print("set /controls/engines/current-engine/throttle " + var + "\r\n");
                out.flush();
            }
        });
    }

    public void connect(String ip, int port) {
        this.ip = ip;
        this.port = port;
        System.out.println("myip is:"+ ip + "my port is: " + port);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mySocket = new Socket(ip, port);
                    out = new PrintWriter(mySocket.getOutputStream(), true);
                    out.print("set /controls/engines/current-engine/throttle " + 0 + "\r\n");
                    out.flush();
                    out.print("set /controls/flight/rudder " + 0 + "\r\n");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();

                }


            }
        });
    }


    public Model() {
        //ip = "10.0.0.28";
        //port = 6400;

        executor = Executors.newSingleThreadExecutor();

       /* executor.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    mySocket = new Socket(ip, port);
                    out = new PrintWriter(mySocket.getOutputStream(), true);
                    out.print("set /controls/engines/current-engine/throttle " + 0 + "\r\n");
                    out.flush();
                    out.print("set /controls/flight/rudder " + 0 + "\r\n");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();

                }


            }
        });*/


    }


}

/*import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {
    private int port;
    private String ip;
    private Socket fg;
    private PrintWriter out;
    ExecutorService exec;

    Model() {
        port = 6400;
        exec = Executors.newSingleThreadExecutor();
    }
    void setVar(double v){
        exec.execute(new Runnable() {
            @Override
            public void run() {
                out.print("set /controls/flight/aileron "+v+"\r\n");
                out.flush();
            }
        });

    }
    void connect() {
        exec.execute(new Runnable() {

            @Override
            public void run() {
                //System.out.println("avivvvvvvv");
                try {
                    fg = new Socket("172.18.56.71", 6400);
                    System.out.println("connected");
                    out = new PrintWriter(fg.getOutputStream(), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}*/
