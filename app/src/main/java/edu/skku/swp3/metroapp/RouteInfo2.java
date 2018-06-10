package edu.skku.swp3.metroapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private long minutes;
    private long si;
    private long bun;
    private PathData path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_info_2);
        elapsed = (TextView) findViewById(R.id.elapsed);
        time = (TextView) findViewById(R.id.time);
        deTime = (TextView) findViewById(R.id.deTime);
        arTime = (TextView) findViewById(R.id.arTime);
        deName = (TextView) findViewById(R.id.deName);
        arName = (TextView) findViewById(R.id.arName);
        deRound = (TextView) findViewById(R.id.deRound);

        deTime2 = (TextView) findViewById(R.id.deTime2);
        arTime2 = (TextView) findViewById(R.id.arTime2);
        deName2 = (TextView) findViewById(R.id.deName2);
        arName2 = (TextView) findViewById(R.id.arName2);
        deRound2 = (TextView) findViewById(R.id.deRound2);

        depart = (Button)findViewById(R.id.depart);
        arrive = (Button)findViewById(R.id.arrive);
        depart.setClickable(false);
        arrive.setClickable(false);
        Intent intent = getIntent();
        path = (PathData) intent.getSerializableExtra("pathInstance");

        time.setText("5 분");
        ArrayList<String> station = new ArrayList<>();
        station = path.path;

        deName.setText(station.get(0));
        arName.setText(station.get(1));
        deName.setTextColor(Color.BLACK);
        arName.setTextColor(Color.BLACK);

        Calendar calendar = Calendar.getInstance();
        this.minutes = calendar.get(Calendar.MINUTE) + calendar.get(Calendar.HOUR_OF_DAY) * 60;
        for (int i = 0; i < 10; i++) {
            this.minutes++;
            if (this.minutes % 5 == 0)
                break;
        }
        this.si = this.minutes / 60;
        this.bun = this.minutes % 60;
        String text = "";
        if (si < 10) {
            text = "0" + si + ":" + bun;
            deTime.setText(text);
            text= "출발 "+"0" + si + ":" + bun;
            depart.setText(text);
            bun+=5;
            text = "0" + si + ":" + bun;
            arrive.setText(text);
            text = "0" + si + ":" + bun;
            arTime.setText(text);
        }
        else if (bun < 10) {
            text ="출발: "+ si + ":" + "0" + bun;
            depart.setText(text);
            text = si + ":" + "0" + bun;
            deTime.setText(text);
            bun+=5;
            text ="도착: "+ si + ":" + "0" + bun;
            arrive.setText(text);
            text = si + ":" + "0" + bun;
            arTime.setText(text);
        }
        else { //둘다 10보다 큰 경우
            text = "출발: "+si + ":" + bun;
            depart.setText(text);
            text = si + ":" + bun;
            deTime.setText(text);
            bun+=5;
            text = "도착: "+si + ":" + bun;
            arrive.setText(text);
            text = si + ":" + bun;
            arTime.setText(text);
        }
        Toast.makeText(this, "!!" + this.minutes, Toast.LENGTH_LONG).show();
    }

}

