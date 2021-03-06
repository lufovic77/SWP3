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

public class RouteInfo3 extends Activity {
    private TextView elapsed;
    private Button depart;
    private Button arrive;
    private Button seat;
    private TextView time, time2;
    private TextView deTime, deTime2, deTime3;
    private TextView arTime, arTime2, arTime3;
    private TextView deName, deName2, deName3;
    private TextView arName, arName2, arName3;
    private TextView deRound, deRound2, deRound3;
    private TextView arRound, arRound2, arRound3;
    private View vertical1, vertical2, vertical3;

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
        setContentView(R.layout.route_info_3);

        elapsed = (TextView) findViewById(R.id.elapsed);
        time = (TextView) findViewById(R.id.time);
        seat = (Button) findViewById(R.id.seat);
        deRound = (TextView) findViewById(R.id.deRound);
        arRound = (TextView) findViewById(R.id.arRound);
        deTime = (TextView) findViewById(R.id.deTime);
        arTime = (TextView) findViewById(R.id.arTime);
        deName = (TextView) findViewById(R.id.deName);
        arName = (TextView) findViewById(R.id.arName);
        depart = (Button) findViewById(R.id.depart);
        arrive = (Button) findViewById(R.id.arrive);
        vertical1 = (View) findViewById(R.id.vertical);

        deTime2 = (TextView) findViewById(R.id.deTime2);
        arTime2 = (TextView) findViewById(R.id.arTime2);
        deName2 = (TextView) findViewById(R.id.deName2);
        arName2 = (TextView) findViewById(R.id.arName2);
        deRound2 = (TextView) findViewById(R.id.deRound2);
        arRound2 = (TextView) findViewById(R.id.arRound2);
        vertical2 = (View) findViewById(R.id.vertical2);

        deTime3 = (TextView) findViewById(R.id.deTime3);
        arTime3 = (TextView) findViewById(R.id.arTime3);
        deName3 = (TextView) findViewById(R.id.deName3);
        arName3 = (TextView) findViewById(R.id.arName3);
        deRound3 = (TextView) findViewById(R.id.deRound3);
        arRound3 = (TextView) findViewById(R.id.arRound3);
        vertical3 = (View) findViewById(R.id.vertical3);

        depart.setClickable(false);
        arrive.setClickable(false);
        Intent intent = getIntent();
        pk = (PathPacket) intent.getSerializableExtra("pathPacket");

        time.setText("15분");
        final ArrayList<String> station;
        final ArrayList<Integer> stationTime;
        station = pk.getpath();
        stationTime = pk.gettime();

        deName.setText(station.get(0));
        arName.setText(station.get(1));
        deName2.setText(station.get(1));
        arName2.setText(station.get(2));
        deName3.setText(station.get(2));
        arName3.setText(station.get(3));
        deName.setTextColor(Color.BLACK);
        arName.setTextColor(Color.BLACK);
        deName2.setTextColor(Color.BLACK);
        arName2.setTextColor(Color.BLACK);
        deName3.setTextColor(Color.BLACK);
        arName3.setTextColor(Color.BLACK);


        for (int i = 0; i < fourHo.length; i++) {
            if (fourHo[i].equals(station.get(0))) {
                for (int j = 0; j < fourHo.length; j++) {
                    if (fourHo[j].equals(station.get(1))) {
                        deRound.setBackgroundResource(R.drawable.round_button_lightblue);
                        arRound.setBackgroundResource(R.drawable.round_button_lightblue);
                        vertical1.setBackgroundColor(Color.parseColor("#00bfff"));
                        deRound.setText("4");
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
                    }
                }
            }
            if (fourHo[i].equals(station.get(2))) {
                for (int j = 0; j < fourHo.length; j++) {
                    if (fourHo[j].equals(station.get(3))) {
                        deRound3.setBackgroundResource(R.drawable.round_button_lightblue);
                        arRound3.setBackgroundResource(R.drawable.round_button_lightblue);
                        vertical3.setBackgroundColor(Color.parseColor("#00bfff"));
                        deRound3.setText("4");
                    }
                }
            }

        }

        for (int i = 0; i < twoHo.length; i++) {
            if (twoHo[i].equals(station.get(0))) {
                for (int j = 0; j < twoHo.length; j++) {
                    if (twoHo[j].equals(station.get(1))) {
                        deRound.setBackgroundResource(R.drawable.round_button_green);
                        arRound.setBackgroundResource(R.drawable.round_button_green);
                        vertical1.setBackgroundColor(Color.parseColor("#3cb371"));
                        deRound.setText("2");
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
                    }
                }
            }
            if (twoHo[i].equals(station.get(2))) {
                for (int j = 0; j < twoHo.length; j++) {
                    if (twoHo[j].equals(station.get(3))) {
                        deRound3.setBackgroundResource(R.drawable.round_button_green);
                        arRound3.setBackgroundResource(R.drawable.round_button_green);
                        vertical3.setBackgroundColor(Color.parseColor("#3cb371"));
                        deRound3.setText("2");
                    }
                }
            }
        }

        for (int i = 0; i < sevenHo.length; i++) {
            if (sevenHo[i].equals(station.get(0))) {
                for (int j = 0; j < sevenHo.length; j++) {
                    if (sevenHo[j].equals(station.get(1))) {
                        deRound.setBackgroundResource(R.drawable.round_button_darkgreen);
                        arRound.setBackgroundResource(R.drawable.round_button_darkgreen);
                        vertical1.setBackgroundColor(Color.parseColor("#6b8e23"));
                        deRound.setText("7");
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
                    }
                }
            }
            if (sevenHo[i].equals(station.get(2))) {
                for (int j = 0; j < sevenHo.length; j++) {
                    if (sevenHo[j].equals(station.get(3))) {
                        deRound3.setBackgroundResource(R.drawable.round_button_darkgreen);
                        arRound3.setBackgroundResource(R.drawable.round_button_darkgreen);
                        vertical3.setBackgroundColor(Color.parseColor("#6b8e23"));
                        deRound3.setText("7");
                    }
                }
            }
        }


        minutes = stationTime.get(0);
        this.si = this.minutes / 60;
        this.bun = this.minutes % 60;
        String text = "";

        text = si + ":" + bun;
        deTime.setText(text);
        text = "출발 " + si + ":" + bun;
        depart.setText(text);
        minutes = stationTime.get(1);
        this.si = this.minutes / 60;
        this.bun = this.minutes % 60;
        text = si + ":" + bun;
        arTime.setText(text);

        this.si = this.minutes / 60;
        this.bun = this.minutes % 60;
        text = si + ":" + bun;
        deTime2.setText(text);
        minutes = stationTime.get(2);
        this.si = this.minutes / 60;
        this.bun = this.minutes % 60;
        text = si + ":" + bun;
        arTime2.setText(text);

        this.si = this.minutes / 60;
        this.bun = this.minutes % 60;
        text = si + ":" + bun;
        deTime3.setText(text);
        minutes = stationTime.get(3);
        this.si = this.minutes / 60;
        this.bun = this.minutes % 60;
        text = "도착: " + si + ":" + bun;
        arrive.setText(text);
        text = si + ":" + bun;
        arTime3.setText(text);

        seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc = pk.getseats();

                Intent intent = new Intent(getBaseContext(), MetroClass.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("seatInstance", sc);
                bundle.putInt("offTime", stationTime.get(3));
                bundle.putString("offStation", station.get(3));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

