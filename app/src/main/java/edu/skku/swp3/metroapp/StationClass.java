package edu.skku.swp3.metroapp;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by TS on 2018. 5. 31..
 */

@SuppressWarnings("serial")
public class StationClass implements Serializable {
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

    public void addcar(String startstation,int starttime,int lane,String updown, int time,SeatClass car){
        ArrayList<String> key=new ArrayList<>();
        key.add(Integer.toString(lane));//lane info
        key.add(updown);//up,down info
        key.add(Integer.toString(time));//car time
        car.init(startstation,Integer.toString(lane),updown,Integer.toString(starttime));
        //car.getseat(1);
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
        Log.i("check time start:",Integer.toString(starttime)+"interval:"+Integer.toString(interval));
        Log.i("check time t:",Integer.toString(t)+"target:"+Integer.toString(target));
        Log.i("check time cal:",Integer.toString(calculated)+"end:"+Integer.toString(endtime));
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
