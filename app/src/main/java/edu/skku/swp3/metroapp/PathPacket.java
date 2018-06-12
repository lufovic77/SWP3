package edu.skku.swp3.metroapp;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by TS on 12/06/2018.
 */

public class PathPacket implements Serializable {
    private ArrayList<String> path;
    private int length;
    private String updown;
    private ArrayList<Integer> times;
    private SeatClass seats;
    PathPacket(ArrayList<String> pth,SeatClass st, int len, String ud,ArrayList<Integer> tm){
        path=pth;
        seats=st;
        length=len;
        updown=ud;
        times=tm;

    }
    public ArrayList<String> getpath(){
        return path;
    }
    public ArrayList<Integer> gettime(){
        return times;
    }
    public SeatClass getseats(){
        return seats;
    }


}
