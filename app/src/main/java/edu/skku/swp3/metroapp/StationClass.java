package edu.skku.swp3.metroapp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by TS on 2018. 5. 31..
 */


public class StationClass {
    HashMap<ArrayList<String>,SeatClass> stationtable;
    String stationname;
    public boolean isinterchange;
    public int upstart;
    public int uplast;
    public int downstart;
    public int downlast;
    public int upintv;
    public int downintv;
    StationClass(String name){

        stationtable= new HashMap<>();
        stationname=name;
        isinterchange=false;
    }

    public void addcar(int lane,String updown, int time,SeatClass car){
        ArrayList<String> key=new ArrayList<>();
        key.add(Integer.toString(lane));//lane info
        key.add(updown);//up,down info
        key.add(Integer.toString(time));//car time
        stationtable.put(key,car);
        return;
    }

    public SeatClass getcar(int lane,String updown, int time){
        ArrayList<String> key=new ArrayList<>();
        key.add(Integer.toString(lane));//lane info
        key.add(updown);//up,down info
        key.add(Integer.toString(time));//car time
        return stationtable.get(key);
    }
    public int closest(int target,String updown){
        int t,calculated;
        int starttime,endtime,interval;
        if(updown.equals("Up")){
            starttime=upstart;
            endtime=uplast;
            interval=upintv;
        }else{
            starttime=downstart;
            endtime=downlast;
            interval=downintv;
        }
        t=(target-starttime)/interval+1;
        calculated=starttime + interval * t;
        if(calculated>endtime)
        {
            return -1;//there is no train available
        }
        else
        {
            return calculated;
        }
    }
}
