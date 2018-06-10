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

    private long minutes;
    private long si;
    private long bun;
    private PathData path;
    private String[] fourHo={"남태령", "사당", "총신대입구"};
    private String[] sevenHo={"총신대입구", "남성", "내방"};
    private String[] twoHo={"낙성대","사당", "방배"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_info_1);
        elapsed = (TextView) findViewById(R.id.elapsed);
        time = (TextView) findViewById(R.id.time);

        deTime = (TextView) findViewById(R.id.deTime);
        arTime = (TextView) findViewById(R.id.arTime);
        deName = (TextView) findViewById(R.id.deName);
        arName = (TextView) findViewById(R.id.arName);
        deRound = (TextView) findViewById(R.id.deRound);
        arRound = (TextView) findViewById(R.id.arRound);
        vertical = (View) findViewById(R.id.vertical);

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
        for(int i=0;i<fourHo.length;i++){
            if(fourHo[i].equals(station.get(0))) {
                for (int j = 0; j < fourHo.length; j++) {
                    if(fourHo[j].equals(station.get(1))) {
                        deRound.setBackgroundResource(R.drawable.round_button_lightblue);
                        arRound.setBackgroundResource(R.drawable.round_button_lightblue);
                        vertical.setBackgroundColor(Color.parseColor("#00bfff"));
                    }
                }
            }
        }

        for(int i=0;i<twoHo.length;i++){
            if(twoHo[i].equals(station.get(0))) {
                for (int j = 0; j < twoHo.length; j++) {
                    if(twoHo[j].equals(station.get(1))) {
                        deRound.setBackgroundResource(R.drawable.round_button_green);
                        arRound.setBackgroundResource(R.drawable.round_button_green);
                        vertical.setBackgroundColor(Color.parseColor("#3cb371"));
                    }
                }
            }
        }

        for(int i=0;i<sevenHo.length;i++){
            if(sevenHo[i].equals(station.get(0))) {
                for (int j = 0; j < sevenHo.length; j++) {
                    if(sevenHo[j].equals(station.get(1))) {
                        deRound.setBackgroundResource(R.drawable.round_button_darkgreen);
                        arRound.setBackgroundResource(R.drawable.round_button_darkgreen);
                        vertical.setBackgroundColor(Color.parseColor("#6b8e23"));
                    }
                }
            }
        }


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

