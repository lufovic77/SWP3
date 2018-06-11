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

public class RouteInfo extends Activity {
    private TextView elapsed;
    private Button depart;
    private Button arrive;
    private TextView time;
    private TextView deTime;
    private TextView arTime;
    private TextView deName;
    private TextView arName;
    private TextView deRound;
    private TextView arRound;
    private View vertical;
    private Button seat;

    private int sTime;
    private int laneNum;
    private int minutes;
    private long si;
    private long bun;
    private PathData path;
    private StationHolder stationdata;
    private String[] fourHo = {"남태령", "사당", "총신대입구"};
    private String[] sevenHo = {"총신대입구", "남성", "내방"};
    private String[] twoHo = {"낙성대", "사당", "방배"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_info_1);
        elapsed = (TextView) findViewById(R.id.elapsed);
        time = (TextView) findViewById(R.id.time);
        seat = (Button) findViewById(R.id.seat);

        deTime = (TextView) findViewById(R.id.deTime);
        arTime = (TextView) findViewById(R.id.arTime);
        deName = (TextView) findViewById(R.id.deName);
        arName = (TextView) findViewById(R.id.arName);
        deRound = (TextView) findViewById(R.id.deRound);
        arRound = (TextView) findViewById(R.id.arRound);
        vertical = (View) findViewById(R.id.vertical);
//출발 시간 (분), 몇호선 , 둘다 integer
        depart = (Button) findViewById(R.id.depart);
        arrive = (Button) findViewById(R.id.arrive);
        depart.setClickable(false);
        arrive.setClickable(false);
        Intent intent = getIntent();
        stationdata = (StationHolder) intent.getSerializableExtra("stationInstance");
        path=stationdata.findpath();
        //stationdata = (StationHolder) intent.getExtras().getSerializable("stationInstance");

        time.setText("5 분");
        final ArrayList<String> station;
        final ArrayList<Integer> stationTime;
        station = path.path;

        String one = station.get(0);
        String two;
        two = station.get(1);
        deName.setText(one);
        arName.setText(two);
        for (int i = 0; i < fourHo.length; i++) {
            if (fourHo[i].equals(one)) {
                for (int j = 0; j < fourHo.length; j++) {
                    if (fourHo[j].equals(two)) {
                        deRound.setBackgroundResource(R.drawable.round_button_lightblue);
                        arRound.setBackgroundResource(R.drawable.round_button_lightblue);
                        vertical.setBackgroundColor(Color.parseColor("#00bfff"));
                        deRound.setText("4");
                        laneNum=4;
                    }
                }
            }
        }

        for (int i = 0; i < twoHo.length; i++) {
            if (twoHo[i].equals(one)) {
                for (int j = 0; j < twoHo.length; j++) {
                    if (twoHo[j].equals(two)) {
                        deRound.setBackgroundResource(R.drawable.round_button_green);
                        arRound.setBackgroundResource(R.drawable.round_button_green);
                        vertical.setBackgroundColor(Color.parseColor("#3cb371"));
                        deRound.setText("2");
                        laneNum=2;
                    }
                }
            }
        }

        for (int i = 0; i < sevenHo.length; i++) {
            if (sevenHo[i].equals(one)) {
                for (int j = 0; j < sevenHo.length; j++) {
                    if (sevenHo[j].equals(two)) {
                        deRound.setBackgroundResource(R.drawable.round_button_darkgreen);
                        arRound.setBackgroundResource(R.drawable.round_button_darkgreen);
                        vertical.setBackgroundColor(Color.parseColor("#6b8e23"));
                        deRound.setText("7");
                        laneNum=7;
                    }
                }
            }
        }

        deName.setTextColor(Color.BLACK);
        arName.setTextColor(Color.BLACK);

        Calendar calendar = Calendar.getInstance();
        this.minutes = calendar.get(Calendar.MINUTE) + calendar.get(Calendar.HOUR_OF_DAY) * 60;

      //  stationTime=path.closest(stationdata, minutes);

        //minutes=stationTime.get(0);
        this.si = this.minutes / 60;
        this.bun = this.minutes % 60;
        String text = "";
        text = si + ":" + bun;
        deTime.setText(text);
        text = "출발 " + si + ":" + bun;
        depart.setText(text);
        //minutes=stationTime.get(1);
        minutes+=5;
        this.si = this.minutes / 60;
        this.bun = this.minutes % 60;
        text = "도착: " + si + ":" + bun;
        arrive.setText(text);
        text = "0" + si + ":" + bun;
        arTime.setText(text);

        seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MetroClass.class);
                //stationdata.getstation(station.get(0)).getcar()
                startActivity(intent);
            }
        });
    }

}

