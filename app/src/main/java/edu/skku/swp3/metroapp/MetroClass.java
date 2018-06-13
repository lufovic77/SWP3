package edu.skku.swp3.metroapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by TS on 2018. 5. 31..
 */

public class MetroClass extends Activity{
    private Spinner carSpin;
    private Button update;
    private TextView metroinfo;
    private Integer[] SeatId = { R.id.left_seat1, R.id.left_seat2, R.id.left_seat3, R.id.left_seat4, R.id.left_seat5
                                , R.id.right_seat1, R.id.right_seat2, R.id.right_seat3, R.id.right_seat4, R.id.right_seat5};
    private Button SeatBtn[] = new Button[10];

    private boolean seat_myself = false; //whether I seat or not.(I can seat only one.)
    private int present_car = 1;
    private Integer[] whereIseat = new Integer[2];
    private int index;
    private SeatClass seats;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        seats= (SeatClass) intent.getSerializableExtra("seatInstance");
        setContentView(R.layout.metro);

        carSpin = (Spinner)findViewById(R.id.carSpinner);
        update = (Button)findViewById(R.id.update);
        metroinfo = (TextView)findViewById(R.id.metroinfoText);
        for(int i=0;i<10;i++){
            SeatBtn[i]=(Button)findViewById(SeatId[i]);

        }

        carSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                //호차 선택시 할 일
                String str = carSpin.getSelectedItem().toString();
                str.substring(0, str.length()-2);
                present_car = Integer.parseInt(str.substring(0,1));
                Log.i("value:",Integer.toString(present_car));
                seats.car[present_car] = seats.getseat(present_car);
                seatUpdate(); //car[present]에 해당하는 정보에 맞게 좌석이미지 update하는 함수 호출

                Log.e("Selected item : ",str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seats.car[present_car] = seats.getseat(present_car);
                if(seat_myself) seats.car[whereIseat[0]][whereIseat[1]] = 2;
                seatUpdate();
            }
        });

        metroinfo.setText(seats.lane + seats.updown);

        SeatBtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seats.car[present_car][0] == 0) noone_popUp(v, 0);
                else if(seats.car[present_car][0] == 1) someone_popUp(v, 0);
                else if(seats.car[present_car][0] == 2) my_popUp(v, 0);
            }
        });
        SeatBtn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seats.car[present_car][1] == 0) noone_popUp(v, 1);
                else if(seats.car[present_car][1] == 1) someone_popUp(v, 1);
                else if(seats.car[present_car][1] == 2) my_popUp(v, 1);
            }
        });
        SeatBtn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seats.car[present_car][2] == 0) noone_popUp(v, 2);
                else if(seats.car[present_car][2] == 1) someone_popUp(v, 2);
                else if(seats.car[present_car][2] == 2) my_popUp(v, 2);
            }
        });
        SeatBtn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seats.car[present_car][3] == 0) noone_popUp(v, 3);
                else if(seats.car[present_car][3] == 1) someone_popUp(v, 3);
                else if(seats.car[present_car][3] == 2) my_popUp(v, 3);
            }
        });
        SeatBtn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seats.car[present_car][4] == 0) noone_popUp(v, 4);
                else if(seats.car[present_car][4] == 1) someone_popUp(v, 4);
                else if(seats.car[present_car][4] == 2) my_popUp(v, 4);
            }
        });

        SeatBtn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seats.car[present_car][5] == 0) noone_popUp(v, 5);
                else if(seats.car[present_car][5] == 1) someone_popUp(v, 5);
                else if(seats.car[present_car][5] == 2) my_popUp(v, 5);
            }
        });
        SeatBtn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seats.car[present_car][6] == 0) noone_popUp(v, 6);
                else if(seats.car[present_car][6] == 1) someone_popUp(v, 6);
                else if(seats.car[present_car][6] == 2) my_popUp(v, 6);
            }
        });
        SeatBtn[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seats.car[present_car][7] == 0) noone_popUp(v, 7);
                else if(seats.car[present_car][7] == 1) someone_popUp(v, 7);
                else if(seats.car[present_car][7] == 2) my_popUp(v, 7);
            }
        });
        SeatBtn[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seats.car[present_car][8] == 0) noone_popUp(v, 8);
                else if(seats.car[present_car][8] == 1) someone_popUp(v, 8);
                else if(seats.car[present_car][8] == 2) my_popUp(v, 8);
            }
        });
        SeatBtn[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seats.car[present_car][9] == 0) noone_popUp(v, 9);
                else if(seats.car[present_car][9] == 1) someone_popUp(v, 9);
                else if(seats.car[present_car][9] == 2) my_popUp(v, 9);
            }
        });
    }

    public void seatUpdate(){
        //left seat image update
        for(int i=0; i<5; i++){
            switch(seats.car[present_car][i]){
                case 0:
                    SeatBtn[index].setBackgroundResource(R.drawable.lseat_noone);
                    break;
                case 1:
                    SeatBtn[index].setBackgroundResource(R.drawable.lseat_someone);
                    break;
                case 2:
                    SeatBtn[index].setBackgroundResource(R.drawable.lseat_myself);
                    break;
            }
        }
        //right seat image update
        for(int i=5; i<10; i++){
            switch(seats.car[present_car][i]){
                case 0:
                    SeatBtn[index].setBackgroundResource(R.drawable.rseat_noone);
                    break;
                case 1:
                    SeatBtn[index].setBackgroundResource(R.drawable.rseat_someone);
                    break;
                case 2:
                    SeatBtn[index].setBackgroundResource(R.drawable.rseat_myself);
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
                if(seat_myself){
                    seats.car[whereIseat[0]][whereIseat[1]] = 0;
                    seats.updateseat(whereIseat[0], whereIseat[1], 0);
                }
                //앉은 자리 whereIseat에 표시&update
                whereIseat[0] = present_car;
                whereIseat[1] = index;
                seats.updateseat(whereIseat[0], whereIseat[1], 1); //다른 사용자에게는 someone_seat으로 보여야함
                //선택한 자리 2(myself)로 바꾸는 작업
                seats.car[present_car][index] = 2;
                seat_myself = true;
                if(index<5){
                    SeatBtn[index].setBackgroundResource(R.drawable.lseat_myself);
                }
                else{
                    SeatBtn[index].setBackgroundResource(R.drawable.rseat_myself);
                }
                return false;
            }
        });
        popup.show();
    }
    public void someone_popUp(View v, int arg){
        index = arg;
        android.widget.PopupWindow popup = new android.widget.PopupWindow(MetroClass.this);
        android.widget.TextView text = (TextView)findViewById(R.id.SomeonePopup);
        //text.setText(seats.off_time[present_car][index].toString() + seats.off_station[present_car][index]);
        View layout = getLayoutInflater().inflate(R.layout.popup_content, null);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(android.view.WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(android.view.WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        //popup.setBackgroundDrawable(new android.graphics.drawable.BitmapDrawable());
        popup.showAsDropDown(v);
    }

    public void my_popUp(View v, int arg){
        index = arg;
        PopupMenu popup = new PopupMenu(getApplicationContext(), v);
        getMenuInflater().inflate(R.menu.seat_my, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //선택한 자리 0(no_one)으로 바꾸는 작업
                seats.car[present_car][index] = 0;
                seat_myself = false;    //앉은 자리 없다고 다시 표시
                if(index<5){
                    SeatBtn[index].setBackgroundResource(R.drawable.lseat_noone);
                }
                else{
                    SeatBtn[index].setBackgroundResource(R.drawable.rseat_noone);
                }
                return false;
            }
        });
        popup.show();
    }
}
