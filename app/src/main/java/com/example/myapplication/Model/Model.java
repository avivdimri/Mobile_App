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
    private final ExecutorService executor;
    private boolean isConnect;

    public Model() {
        executor = Executors.newSingleThreadExecutor();
        isConnect = false;
    }

    public void setAttribute(double var, String str) {
        executor.execute(() -> {
            out.print(str + var + "\r\n");
            out.flush();
        });
    }

    /* public void setElevator(Double var) {
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
         executor.execute(()->{
                 out.print("set /controls/engines/current-engine/throttle " + var + "\r\n");
                 out.flush();

         });
     }
 */
    public void connect(String ip, int port) {
        System.out.println("my ip is:" + ip + "my port is: " + port);
        executor.execute(() -> {
            try {
                mySocket = new Socket(ip, port);
                out = new PrintWriter(mySocket.getOutputStream(), true);
                if (!isConnect) {
                    isConnect = true;
                    setAttribute(0, "set /controls/flight/aileron ");
                    setAttribute(0, "set /controls/flight/elevator ");
                    setAttribute(0.1, "set /controls/flight/rudder ");
                    setAttribute(0, "set /controls/engines/current-engine/throttle ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


}
