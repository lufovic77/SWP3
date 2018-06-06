package edu.skku.swp3.metroapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;

/**
 * Created by TS on 2018. 5. 31..
 */

public class MetroClass extends Activity{
    private Spinner carSpin;
    private Integer[] SeatId = { R.id.left_seat1, R.id.left_seat2, R.id.left_seat3, R.id.left_seat4, R.id.left_seat5
                                , R.id.right_seat1, R.id.right_seat2, R.id.right_seat3, R.id.right_seat4, R.id.right_seat5};
    private Button SeatBtn[] = new Button[10];

    //car[0] = 비고
    //car[1]~[10] = 해당 호차의 좌석정보
    //car[][0]~[][4] = left seat의 좌석정보
    //car[][5]~[][9] = right seat의 좌석정보
    //seat state는 0: no one, 1: some one, 2: me!
    private Integer[][] car = new Integer[11][10];
    private boolean seat_myself = false; //whether I seat or not.(I can seat only one.)
    private int present_car = 1;
    private Integer[] whereIseat = new Integer[2];
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get intent(with 행성지 이름, 호차)=>textview설정해주기

        carSpin = (Spinner)findViewById(R.id.carSpinner);
        for(int i=0;i<10;i++){
            SeatBtn[i]=(Button)findViewById(SeatId[i]);
        }

        carSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //호차 선택시 할 일
                String str = (String) parent.getItemAtPosition(position);
                str.substring(0, str.length()-2);
                present_car = Integer.parseInt(str);
                seatUpdate(); //car[present]에 해당하는 정보에 맞게 좌석이미지 update하는 함수 호출
            }
        });

        SeatBtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car[present_car][0] == 0) noone_popUp(v, 0);
                else if(car[present_car][0] == 2) my_popUp(v, 0);
            }
        });
        SeatBtn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car[present_car][1] == 0) noone_popUp(v, 0);
                else if(car[present_car][1] == 2) my_popUp(v, 0);
            }
        });
        SeatBtn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car[present_car][2] == 0) noone_popUp(v, 0);
                else if(car[present_car][2] == 2) my_popUp(v, 0);
            }
        });
        SeatBtn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car[present_car][3] == 0) noone_popUp(v, 0);
                else if(car[present_car][3] == 2) my_popUp(v, 0);
            }
        });
        SeatBtn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car[present_car][4] == 0) noone_popUp(v, 0);
                else if(car[present_car][4] == 2) my_popUp(v, 0);
            }
        });

        SeatBtn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car[present_car][5] == 0) noone_popUp(v, 0);
                else if(car[present_car][5] == 2) my_popUp(v, 0);
            }
        });
        SeatBtn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car[present_car][6] == 0) noone_popUp(v, 0);
                else if(car[present_car][6] == 2) my_popUp(v, 0);
            }
        });
        SeatBtn[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car[present_car][7] == 0) noone_popUp(v, 0);
                else if(car[present_car][7] == 2) my_popUp(v, 0);
            }
        });
        SeatBtn[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car[present_car][8] == 0) noone_popUp(v, 0);
                else if(car[present_car][8] == 2) my_popUp(v, 0);
            }
        });
        SeatBtn[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(car[present_car][9] == 0) noone_popUp(v, 0);
                else if(car[present_car][9] == 2) my_popUp(v, 0);
            }
        });
    }

    public void seatUpdate(){
        //left seat image update
        for(int i=0; i<5; i++){
            switch(car[present_car][i]){
                case 0:
                    SeatBtn[index].setBackgroundResource(R.drawable.Lseat_noone);
                    break;
                case 1:
                    SeatBtn[index].setBackgroundResource(R.drawable.Lseat_someone);
                    break;
                case 2:
                    SeatBtn[index].setBackgroundResource(R.drawable.Lseat_myself);
                    break;
            }
        }
        //right seat image update
        for(int i=5; i<10; i++){
            switch(car[present_car][i]){
                case 0:
                    SeatBtn[index].setBackgroundResource(R.drawable.Rseat_noone);
                    break;
                case 1:
                    SeatBtn[index].setBackgroundResource(R.drawable.Rseat_someone);
                    break;
                case 2:
                    SeatBtn[index].setBackgroundResource(R.drawable.Rseat_myself);
                    break;
            }
        }
    }
    public void noone_popUp(View v, int arg){
        index = arg;
        PopupMenu popup = new PopupMenu(getApplicationContext(), v);
        getMenuInflater().inflate(R.menu.seat_no, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //앉았다고 표시한 자리가 있었으면, 그 자리 0(no_one)으로 set.
                if(seat_myself) car[whereIseat[0]][whereIseat[1]] = 0;
                //앉은 자리 whereIseat에 표시
                whereIseat[0] = present_car;
                whereIseat[1] = index;
                //선택한 자리 2(myself)로 바꾸는 작업
                car[present_car][index] = 2;
                seat_myself = true;
                if(index<5){
                    SeatBtn[index].setBackgroundResource(R.drawable.Lseat_myself);
                }
                else{
                    SeatBtn[index].setBackgroundResource(R.drawable.Rseat_myself);
                }
                return false;
            }
        });
        popup.show();
    }
    public void my_popUp(View v, int arg){
        index = arg;
        PopupMenu popup = new PopupMenu(getApplicationContext(), v);
        getMenuInflater().inflate(R.menu.seat_my, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //선택한 자리 0(no_one)으로 바꾸는 작업
                car[present_car][index] = 0;
                seat_myself = false;    //앉은 자리 없다고 다시 표시
                if(index<5){
                    SeatBtn[index].setBackgroundResource(R.drawable.Lseat_noone);
                }
                else{
                    SeatBtn[index].setBackgroundResource(R.drawable.Rseat_noone);
                }
                return false;
            }
        });
        popup.show();
    }
}
