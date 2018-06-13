package edu.skku.swp3.metroapp;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Created by TS on 06/06/2018.
 */
@SuppressWarnings("serial")
public class SeatClass implements Serializable {


    //car[0] = 비고
    //car[1]~[10] = 해당 호차의 좌석정보
    //car[][0]~[][4] = left seat의 좌석정보
    //car[][5]~[][9] = right seat의 좌석정보
    //seat state는 0: no one, 1: some one, 2: me!
    //off_time 내리는 시간(if seat state = 1)
    //off_station 내리는 역(if seat state = 1)
    public Integer[][] car = new Integer[11][10];
    public Integer[][] off_time = new Integer[11][10];
    public String[][] off_station = new String[11][10];
    public String lane;
    public String updown;
    public String time;
    public String station;
    public int carnum;
    public int seatnum;
    public int newval;
    public class Client extends AsyncTask<Void,Void,Integer[]> {

        @Override
        protected Integer[] doInBackground(Void... voids) {
            Integer[] seats = new Integer[10];
            try {
                Socket soc = new Socket("115.145.238.76", 5000);
                OutputStream os = soc.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(0);//acquire data
                dos.writeInt(carnum);
                PrintWriter pw = new PrintWriter(os);
                pw.append(station + "\n");
                pw.append(lane + "\n");
                pw.append(updown + "\n");
                pw.append(time + "\n");
                pw.flush();


                DataInputStream dis = new DataInputStream(soc.getInputStream());
                int readed;
                for (int i = 0; i < 10; i++) {
                    readed = dis.readInt();
                    car[carnum][i] = readed;
                }
                pw.close();
                dos.close();
                os.close();
                dis.close();


                soc.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return car[carnum];
        }

    }
    public class Getter extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Socket soc = new Socket("115.145.238.76", 5000);

                OutputStream os = soc.getOutputStream ();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(0);//acquire data
                dos.writeInt(carnum);
                dos.writeInt(seatnum);
                dos.writeInt(newval);
                car[carnum][seatnum]=newval;
                PrintWriter pw = new PrintWriter(os);
                pw.append(station+"\n");
                pw.append(lane+"\n");
                pw.append(updown+"\n");
                pw.append(time+"\n");
                pw.flush();
                pw.close();

                dos.close();
                os.close();
                soc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
    public SeatClass(){
        for(int i=0;i<11;i++){
            for(int j=0;j<10;j++){
                car[i][j]=0;
            }
        }
    }
    public void init(String sn,String ln,String ud,String tm){
        station=sn;
        lane=ln;
        updown=ud;
        time=tm;
    }
    public void updateseat(int t,String station,int cn,int sn,int nv){
        carnum=cn;
        seatnum=sn;
        newval=nv;
        try {
            new Getter().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return;

    }
    public Integer[] getseat(int cn) {
        carnum=cn;
        Integer[] seats=new Integer[10];
        for(int i=0;i<10;i++){
            seats[0]=0;
        }
        try {
            seats= new Client().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return seats;
    }


}
