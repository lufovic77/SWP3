package edu.skku.swp3.metroapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

/**
 * Created by TS on 06/06/2018.
 */

public class SeatClass implements Serializable {


    //car[0] = 비고
    //car[1]~[10] = 해당 호차의 좌석정보
    //car[][0]~[][4] = left seat의 좌석정보
    //car[][5]~[][9] = right seat의 좌석정보
    //seat state는 0: no one, 1: some one, 2: me!
    public Integer[][] car = new Integer[11][10];
    public String lane;
    public String updown;
    public String time;
    public String station;
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
    public void updateseat(int carnum,int seatnum,int newval){
        try {
            Socket soc = new Socket("192.168.0.11", 5000);

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
    }
    public void getseat(int carnum){
        try {
            Socket soc = new Socket("203.252.37.67", 5000);

            OutputStream os = soc.getOutputStream ();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(0);//acquire data
            dos.writeInt(carnum);
            PrintWriter pw = new PrintWriter(os);
            pw.append(station+"\n");
            pw.append(lane+"\n");
            pw.append(updown+"\n");
            pw.append(time+"\n");
            pw.flush();



            DataInputStream dis = new DataInputStream(soc.getInputStream());
            int readed;
            for(int i=0;i<10;i++){
                readed=dis.readInt();
                car[carnum][i]=readed;
            }
            pw.close();
            dos.close();
            os.close();
            dis.close();


            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
