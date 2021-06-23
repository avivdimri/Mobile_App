package com.example.myapplication.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// The model of the application part of the MVVM architecture
public class Model {
    private Socket mySocket;
    private PrintWriter out;
    private final ExecutorService executor;
    private boolean isConnect;

    //constructor
    public Model() {
        executor = Executors.newSingleThreadExecutor(); // single thread pool
        isConnect = false;
        mySocket = null;
    }

    // The function gets string and value which represent a setting to thr FG
    public void setAttribute(double var, String str) {
        executor.execute(() -> {
            if (mySocket != null) {
                out.print(str + var + "\r\n");
                out.flush();
            }
        });
    }

    // The function opens socket with the parameters it gets ip and port
    // and initialize the attributes for new flight
    public void connect(String ip, int port) {
        executor.execute(() -> {
            try {
                this.mySocket = new Socket(ip, port);
                out = new PrintWriter(mySocket.getOutputStream(), true);
                //  initialize the attributes only once
                if (!isConnect) {
                    isConnect = true;
                    setAttribute(0, "set /controls/flight/aileron ");
                    setAttribute(0, "set /controls/flight/elevator ");
                    setAttribute(0.04, "set /controls/flight/rudder ");
                    setAttribute(0, "set /controls/engines/current-engine/throttle ");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


}
