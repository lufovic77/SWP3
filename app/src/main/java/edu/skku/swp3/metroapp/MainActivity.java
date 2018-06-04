package edu.skku.swp3.metroapp;
/**
 * Created by lufov, jinsun on 2018-06-04.
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button map;
    private Button btnList; //출발시간 선택 버튼
    private TextView departText;    //출발 시간 텍스트뷰 선택시 내용 바뀜
    private String departTime;  //출발 시간

    private Button departList; //출발지 선택 버튼
    private Button arrivalList; //도착지 선택 버튼
    private String departure; //출발지
    private String arrival;     //도착지
    private boolean select_depart=false; //출발지 선택 햇는지 유무
    private boolean select_arrive=false; //도착지 선택 했는지 유무

    private Integer[] numBtnIDs = { R.id.nam, R.id.sadang, R.id.chongsin, R.id.namsung, R.id.naebang, R.id.naksung, R.id.bangbae, };
    private String [] name ={"남태령", "사당", "총신대입구(이수)", "남성", "내방", "낙성대", "방배"};
    private Button btn[] = new Button[10];
    private Button ok;
    private int index;
    private int i;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final StationHolder station= new StationHolder(MainActivity.this);
        station.execute();
        final List<String> selectedItems = new ArrayList<String>();

        departList=(Button)findViewById(R.id.depart_btn);
        arrivalList=(Button)findViewById(R.id.arrival_btn);
        for(i=0;i<7;i++){
            btn[i]=(Button)findViewById(numBtnIDs[i]);
        }
        ok = (Button)findViewById(R.id.ok);

        //DrawView에 넣기
/*        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"오전 11시", "오후 12시", "오후 1시"};
                final int[] selectedIndex = {0};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog .setTitle("출발 시간")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { selectedIndex[0] = which; } })
                                .setPositiveButton("선택", new DialogInterface.OnClickListener() {
                            @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    departTime=items[selectedIndex[0]];
                                    departText.setText(items[selectedIndex[0]]);

                                }
                            }).create().show();
            }
        });*/

        departList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"남성", "충신대입구(이수)", "내방", "낙성대", "사당", "방배", "남태령"};
                final int[] selectedIndex = {0};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog .setTitle("출발역")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { selectedIndex[0] = which; } })
                        .setPositiveButton("선택", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(select_arrive) {
                                    if (arrival.equals(items[selectedIndex[0]])) {
                                        Toast.makeText(MainActivity.this, "출발지와 도착지가 같습니다. \n다시 선택해주세요!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        departList.setText(items[selectedIndex[0]]);
                                        departure = items[selectedIndex[0]];
                                        select_depart=true;
                                    }
                                }
                                else{
                                    departList.setText(items[selectedIndex[0]]);
                                    departure = items[selectedIndex[0]];
                                    select_depart=true;
                                }
                            }

                        }).create().show();
            }
        });
        arrivalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = new String[]{"남성", "충신대입구(이수)", "내방", "낙성대", "사당", "방배", "남태령"};
                final int[] selectedIndex = {0};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog .setTitle("도착역")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { selectedIndex[0] = which; } })
                        .setPositiveButton("선택", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(select_depart) {
                                    if (departure.equals(items[selectedIndex[0]])) {
                                        Toast.makeText(MainActivity.this, "출발지와 도착지가 같습니다. \n다시 선택해주세요!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        arrivalList.setText(items[selectedIndex[0]]);
                                        arrival = items[selectedIndex[0]];
                                        select_arrive=true;
                                    }
                                }
                                else{
                                    arrivalList.setText(items[selectedIndex[0]]);
                                    arrival = items[selectedIndex[0]];
                                    select_arrive=true;
                                }
                            }
                        }).create().show();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PathData path;

                Log.i("input:", departure+","+arrival);
                path = station.findpath(departure, arrival);
                String text="";
                int pathLength=(path.path.size());
                for(int i=0;i<path.path.size();i++){
                    text=text+path.path.get(i)+",";
                }
                Toast.makeText(MainActivity.this, "!!"+text, Toast.LENGTH_SHORT).show();
                Log.i("done", text);
                path.closest(station,900);

                if(pathLength==2){//환승 없음
                    Intent intent = new Intent(MainActivity.this, RouteInfo.class);
                    intent.putExtra("path", text);
                    startActivity(intent);
                }
                else if(pathLength==3){//환승 1개
                   /* Intent intent = new Intent(getBaseContext(), RouteInfo2.class);
                    startActivity(intent);*/

                }
                else{   //환승 2개
                    /*Intent intent = new Intent(getBaseContext(), RouteInfo3.class);
                    startActivity(intent);*/

                }
                /*Intent intent = new Intent(getBaseContext(), RouteInfo.class);
                startActivity(intent);*/
            }
        });

        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(v, 0);
            }
        });
        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(v, 1);
            }
        });
        btn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(v, 2);
            }
        });
        btn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(v, 3);
            }
        });
        btn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(v, 4);
            }
        });
        btn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(v, 5);
            }
        });
        btn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(v, 6);
            }
        });

    }
    //남태령, 사당, 총신, 남성, 내방, 낙성, 방배
    public void popUp(View v, int i){
        index=i;
        Log.d("popUp", String.valueOf(index));
        PopupMenu popup = new PopupMenu(getApplicationContext(), v);

        getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.m1:
                        setDeparture();
                        break;
                    case R.id.m2:
                        setArrival();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popup.show();
    }

    public void setDeparture(){
        if(select_arrive) {
            if (arrival.equals(name[index])) {
                Toast.makeText(MainActivity.this, "출발지와 도착지가 같습니다. \n다시 선택해주세요!", Toast.LENGTH_SHORT).show();
            } else {
                departList.setText(name[index]);
                departure = name[index];
                select_depart=true;
            }
        }
        else{
            departList.setText(name[index]);
            departure = name[index];
            select_depart=true;
        }
    }

    public void setArrival(){
        if(select_depart) {
            if (departure.equals(name[index])) {
                Toast.makeText(MainActivity.this, "출발지와 도착지가 같습니다. \n다시 선택해주세요!", Toast.LENGTH_SHORT).show();
            } else {
                arrivalList.setText(name[index]);
                arrival = name[index];
                select_arrive=true;
            }
        }
        else{
            arrivalList.setText(name[index]);
            arrival = name[index];
            select_arrive=true;
        }
    }

}

