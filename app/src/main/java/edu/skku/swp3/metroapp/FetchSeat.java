package edu.skku.swp3.metroapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by TS on 2018. 5. 31..
 */

public class FetchSeat extends Thread {

    public FetchSeat(){

    }
    public void run()
    {

        try {
            Socket soc = new Socket("localhost", 5000);
            ObjectInputStream ois = new ObjectInputStream (soc.getInputStream());
           // ois.readObject(); //read here
            ois.close();
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
