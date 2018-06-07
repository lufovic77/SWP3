package edu.skku.swp3.metroapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by TS on 2018. 5. 31..
 */

class PathData implements Serializable{
    public ArrayList<String> path;
    public int length;
    public String updown;
    PathData(String ud){
        path=new ArrayList<>();
        updown=ud;
    }
    void addstation(String station){
        path.add(station);
    }
   /* public int pathlen(){
        length=path.size();
        return length;
    }*/
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
public class StationHolder extends AsyncTask< Void, Void, Void> {
    HashMap<String,StationClass> stationmap;
    HashMap<Integer,ArrayList<String>> stationorder;
    HashMap<ArrayList<String>,PathData> pathtable;
    HashMap<Set<String>,Integer> intervaltable;
    int numlines;
    Context ctxt;
    //ArrayList<String> stations;
    StationHolder(Context context){
        stationmap=new HashMap<>();
        stationorder=new HashMap<>();
        pathtable=new HashMap<>();
        intervaltable=new HashMap<>();
        ctxt=context;
    }
    public int getintv(String s1,String s2){
        ArrayList<String> pair=new ArrayList<>();
        pair.add(s1);
        pair.add(s2);
        return intervaltable.get(new HashSet<String>(pair));
    }
    public void addstation(String name,int lane){
        if(stationmap.containsKey(name)) {
            stationmap.get(name).isinterchange=true;
        }else{
            stationmap.put(name,new StationClass(name));
        }
        stationorder.get(lane).add(name);
        return;
    }

    public PathData findpath(String from,String to){        //스트링을 받아서
        ArrayList<String> target=new ArrayList<>();
        target.add(from);
        target.add(to);
        return pathtable.get(target);
    }
    public StationClass getstation(String name){
        return stationmap.get(name);
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.i("Reading db", "background process for file read starts");
        Scanner sc = new Scanner(ctxt.getResources().openRawResource(R.raw.db));
        StringTokenizer tokenizer;
        //1. get number of lane infos
        String line,stationname;
        line=sc.nextLine();
        numlines=Integer.parseInt(line);
        ArrayList<String> order;
        int linenum;
        for(int i=0;i<numlines;i++){
            line=sc.nextLine();
            Log.i("Reading First Part", line);
            tokenizer = new StringTokenizer(line, ",");
            order=new ArrayList<>();
            linenum=Integer.parseInt(tokenizer.nextToken());
            stationorder.put(linenum,new ArrayList<String>());
            while(tokenizer.hasMoreTokens()){//get stations
                stationname=tokenizer.nextToken();
                order.add(stationname);//add to order
                addstation(stationname,linenum);
            }
            stationorder.put(linenum,order);
        }//first part complete
        //get interval data
        line=sc.nextLine();
        int numlines2=Integer.parseInt(line);
        ArrayList<String> pair;
        int interval;
        for(int i=0;i<numlines2;i++){
            pair=new ArrayList<>();
            line=sc.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            pair.add(tokenizer.nextToken());
            pair.add(tokenizer.nextToken());
            interval=Integer.parseInt(tokenizer.nextToken());
            intervaltable.put(new HashSet<String>(pair),interval);
        }
        //get lane info
        String updown;
        int first,last,intv,numstation,nujuk,nujukl;
        SeatClass car;
        for(int i=0;i<numlines;i++){
            line=sc.nextLine();
            Log.i("Reading second part ", line);
            tokenizer = new StringTokenizer(line, ",");
            //up
            linenum=Integer.parseInt(tokenizer.nextToken());
            order=stationorder.get(linenum);
            updown=tokenizer.nextToken();
            first=Integer.parseInt(tokenizer.nextToken());
            last=Integer.parseInt(tokenizer.nextToken());
            intv=Integer.parseInt(tokenizer.nextToken());

            //add start,end time
            numstation=order.size();
            stationmap.get(order.get(0)).upstart=first;
            stationmap.get(order.get(0)).uplast=last;
            stationmap.get(order.get(0)).upintv=intv;
            for(int k=first;k<=last;k+=intv){
                car=new SeatClass();
                stationmap.get(order.get(0)).addcar(linenum,updown,k,car);
            }
            nujuk=first;
            nujukl=last;
            for(int j=1;j<numstation;j++){
                nujuk+=getintv(order.get(j-1),order.get(j));
                nujukl+=getintv(order.get(j-1),order.get(j));
                stationmap.get(order.get(j)).upstart=nujuk;
                stationmap.get(order.get(j)).uplast=nujukl;
                stationmap.get(order.get(j)).upintv=nujukl;
                for(int k=nujuk;k<=nujukl;k+=intv){
                    car=new SeatClass();
                    stationmap.get(order.get(0)).addcar(linenum,updown,k,car);
                }
            }
            //down - reverse order
            line=sc.nextLine();
            Log.i("Reading second part ", line);
            tokenizer = new StringTokenizer(line, ",");
            //up
            linenum=Integer.parseInt(tokenizer.nextToken());
            order=stationorder.get(linenum);
            updown=tokenizer.nextToken();
            first=Integer.parseInt(tokenizer.nextToken());
            last=Integer.parseInt(tokenizer.nextToken());
            intv=Integer.parseInt(tokenizer.nextToken());
            /*for(int j=first;j<=last;j+=intv) {
                car = new SeatClass();
                numstation = order.size();
                for (int k = numstation - 1; k > 0; k--) {
                    stationmap.get(order.get(k)).addcar(linenum, updown, j, car);
                }
            }*/
            //add start,end time
            numstation=order.size();
            stationmap.get(order.get(0)).downstart=first;
            stationmap.get(order.get(0)).downlast=last;
            stationmap.get(order.get(0)).downintv=intv;
            for(int k=first;k<=last;k+=intv){
                car=new SeatClass();
                stationmap.get(order.get(0)).addcar(linenum,updown,k,car);
            }
            nujuk=first;
            nujukl=last;
            for(int j=1;j<numstation;j++){
                nujuk+=getintv(order.get(j-1),order.get(j));
                nujukl+=getintv(order.get(j-1),order.get(j));
                stationmap.get(order.get(j)).downstart=nujuk;
                stationmap.get(order.get(j)).downlast=nujukl;
                stationmap.get(order.get(j)).downintv=intv;
                for(int k=nujuk;k<=nujukl;k+=intv){
                    car=new SeatClass();
                    stationmap.get(order.get(0)).addcar(linenum,updown,k,car);
                }
            }
        }//completed stations

        //get paths
        String from,to;
        int count;
        ArrayList<String> target;
        while(sc.hasNext()){
            line=sc.nextLine();
            tokenizer = new StringTokenizer(line, ",");
            target=new ArrayList<>();
            from=tokenizer.nextToken();
            to=tokenizer.nextToken();
            target.add(from);
            target.add(to);
            PathData pth=new PathData(tokenizer.nextToken());
            count=0;
            while(tokenizer.hasMoreTokens()){
                pth.addstation(tokenizer.nextToken());
                count++;
            }
            pth.length=count;
            pathtable.put(target,pth);
        }

        Log.i("Finished Reading db", "Read complete");
        sc.close();
        return null;
    }

    //유아이 처리하는 메인 쓰레드
    @Override
    public void onPostExecute(Void v) {

        //toast message - db load complete
        Toast.makeText(ctxt,"DB loading complete!",Toast.LENGTH_LONG).show();

    }


}

