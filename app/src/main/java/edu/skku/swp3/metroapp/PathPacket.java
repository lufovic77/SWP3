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
    private HashMap<String,StationClass> stationmp;
    private int length;
    private String updown;
    PathPacket(ArrayList<String> pth,HashMap<String,StationClass> sm, int len, String ud){
        path=pth;
        stationmp=sm;
        length=len;
        updown=ud;

    }
    public ArrayList<Integer> closest(StationHolder sh,int current){
        int i=0;
        int len=path.size();
        StationClass cur;
        int curtime;
        ArrayList<Integer> times=new ArrayList<>();
        cur=sh.getstation(path.get(0));
        curtime=cur.closest(current,updown);
        if(curtime==-1){
            return null;
        }
        times.add(curtime);
        Log.i("Reading Time", Integer.toString(curtime));
        for(i=1;i<len;i++){
            curtime+=sh.getintv(path.get(i-1),path.get(i));
            //cur=sh.getstation(path.get(i));
            times.add(curtime);
            Log.i("Reading Time", Integer.toString(curtime));
        }
        return times;
    }
}
