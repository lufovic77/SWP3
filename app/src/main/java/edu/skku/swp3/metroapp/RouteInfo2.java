package edu.skku.swp3.metroapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by lufov on 2018-06-04.
 */

public class RouteInfo2 extends Activity {
    private TextView elapsed;
    private Button depart;
    private Button arrive;
    private TextView time, time2;
    private TextView deTime, deTime2;
    private TextView arTime, arTime2;
    private TextView deName, deName2;
    private TextView arName, arName2;
    private TextView deRound, deRound2;
    private TextView arRound, arRound2;
    private View vertical1, vertical2;
    private Button seat;

    private int flag,count;
    private long minutes;
    private long si;
    private long bun;
    private PathData path;
    private StationHolder stationdata;
    private String[] fourHo = {"남태령", "사당", "총신대입구"};
    private String[] sevenHo = {"총신대입구", "남성", "내방"};
    private String[] twoHo = {"낙성대", "사당", "방배"};
    private PathPacket pk;
    private SeatClass sc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_info_2);
        elapsed = (TextView) findViewById(R.id.elapsed);
        time = (TextView) findViewById(R.id.time);
        seat = (Button) findViewById(R.id.seat);
        deTime = (TextView) findViewById(R.id.deTime);
        arTime = (TextView) findViewById(R.id.arTime);
        deName = (TextView) findViewById(R.id.deName);
        arName = (TextView) findViewById(R.id.arName);
        deRound = (TextView) findViewById(R.id.deRound);
        arRound = (TextView) findViewById(R.id.arRound);
        vertical1 = (View) findViewById(R.id.vertical);

        deTime2 = (TextView) findViewById(R.id.deTime2);
        arTime2 = (TextView) findViewById(R.id.arTime2);
        deName2 = (TextView) findViewById(R.id.deName2);
        arName2 = (TextView) findViewById(R.id.arName2);
        deRound2 = (TextView) findViewById(R.id.deRound2);
        arRound2 = (TextView) findViewById(R.id.arRound2);
        vertical2 = (View) findViewById(R.id.vertical2);

        depart = (Button) findViewById(R.id.depart);
        arrive = (Button) findViewById(R.id.arrive);
        depart.setClickable(false);
        arrive.setClickable(false);
        Intent intent = getIntent();
        pk= (PathPacket) intent.getSerializableExtra("pathPacket");

        time.setText("10 분");
        final ArrayList<String> station;
        final ArrayList<Integer> stationTime;
        station = pk.getpath();
        stationTime=pk.gettime();

        deName.setText(station.get(0));
        arName.setText(station.get(1));
        deName2.setText(station.get(1));
        arName2.setText(station.get(2));
        deName.setTextColor(Color.BLACK);
        arName.setTextColor(Color.BLACK);
        deName2.setTextColor(Color.BLACK);
        arName2.setTextColor(Color.BLACK);

        flag=0;
        count=0;
        for (int i = 0; i < fourHo.length; i++) {
            if (fourHo[i].equals(station.get(0))) {
                for (int j = 0; j < fourHo.length; j++) {
                    if (fourHo[j].equals(station.get(1))) {
                        deRound.setBackgroundResource(R.drawable.round_button_lightblue);
                        arRound.setBackgroundResource(R.drawable.round_button_lightblue);
                        vertical1.setBackgroundColor(Color.parseColor("#00bfff"));
                        deRound.setText("4");
                        count++;
                    }
                }
            }

            if (fourHo[i].equals(station.get(1))) {
                for (int j = 0; j < fourHo.length; j++) {
                    if (fourHo[j].equals(station.get(2))) {
                        deRound2.setBackgroundResource(R.drawable.round_button_lightblue);
                        arRound2.setBackgroundResource(R.drawable.round_button_lightblue);
                        vertical2.setBackgroundColor(Color.parseColor("#00bfff"));
                        deRound2.setText("4");
                        count++;
                    }
                }
            }
        }
        if(count==2){
            flag=1;
            time.setText("10분");
        }

        count=0;
        for (int i = 0; i < twoHo.length; i++) {
            if (twoHo[i].equals(station.get(0))) {
                for (int j = 0; j < twoHo.length; j++) {
                    if (twoHo[j].equals(station.get(1))) {
                        deRound.setBackgroundResource(R.drawable.round_button_green);
                        arRound.setBackgroundResource(R.drawable.round_button_green);
                        vertical1.setBackgroundColor(Color.parseColor("#3cb371"));
                        deRound.setText("2");
                        count++;
                    }
                }
            }
            if (twoHo[i].equals(station.get(1))) {
                for (int j = 0; j < twoHo.length; j++) {
                    if (twoHo[j].equals(station.get(2))) {
                        deRound2.setBackgroundResource(R.drawable.round_button_green);
                        arRound2.setBackgroundResource(R.drawable.round_button_green);
                        vertical2.setBackgroundColor(Color.parseColor("#3cb371"));
                        deRound2.setText("2");
                        count++;
                    }
                }
            }
        }
        if(count==2){
            flag=1;
            time.setText("10분");
        }

        count=0;
        for (int i = 0; i < sevenHo.length; i++) {
            if (sevenHo[i].equals(station.get(0))) {
                for (int j = 0; j < sevenHo.length; j++) {
                    if (sevenHo[j].equals(station.get(1))) {
                        deRound.setBackgroundResource(R.drawable.round_button_darkgreen);
                        arRound.setBackgroundResource(R.drawable.round_button_darkgreen);
                        vertical1.setBackgroundColor(Color.parseColor("#6b8e23"));
                        deRound.setText("7");
                        count++;
                    }
                }
            }
            if (sevenHo[i].equals(station.get(1))) {
                for (int j = 0; j < sevenHo.length; j++) {
                    if (sevenHo[j].equals(station.get(2))) {
                        deRound2.setBackgroundResource(R.drawable.round_button_darkgreen);
                        arRound2.setBackgroundResource(R.drawable.round_button_darkgreen);
                        vertical2.setBackgroundColor(Color.parseColor("#6b8e23"));
                        deRound2.setText("7");
                        count++;
                    }
                }
            }
        }
        if(count==2){
            flag=1;
            time.setText("10분");
        }

        minutes=stationTime.get(0);
        this.si = this.minutes / 60;
        this.bun = this.minutes % 60;
        String text = "";

        if(flag!=1) {
            text = si + ":" + bun;
            deTime.setText(text);
            text = "출발 " + si + ":" + bun;
            depart.setText(text);
            minutes =stationTime.get(1);
            this.si = this.minutes / 60;
            this.bun = this.minutes % 60;
            text = "도착: " + si + ":" + bun;
            arrive.setText(text);
            text = si + ":" + bun;
            arTime.setText(text);

            minutes = stationTime.get(1);
            this.si = this.minutes / 60;
            this.bun = this.minutes % 60;
            text = si + ":" + bun;
            deTime2.setText(text);
            minutes = stationTime.get(2);
            this.si = this.minutes / 60;
            this.bun = this.minutes % 60;
            text = "도착" + si + ":" + bun;
            arrive.setText(text);
            text = si + ":" + bun;
            arTime2.setText(text);
        }
        else{   //flag==2
            text = si + ":" + bun;
            deTime.setText(text);
            text = "출발 " + si + ":" + bun;
            depart.setText(text);
            minutes =stationTime.get(1);
            this.si = this.minutes / 60;
            this.bun = this.minutes % 60;
            text = si + ":" + bun;
            arTime.setText(text);

            text = si + ":" + bun;
            deTime2.setText(text);
            minutes = stationTime.get(2);
            this.si = this.minutes / 60;
            this.bun = this.minutes % 60;
            text = "도착: " + si + ":" + bun;
            arrive.setText(text);
            text = si + ":" + bun;
            arTime2.setText(text);

        }
        seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc = pk.getseats();

                Intent intent = new Intent(getBaseContext(), MetroClass.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("seatInstance", sc);
                bundle.putInt("offTime", stationTime.get(2));
                bundle.putString("offStation", station.get(2));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}

