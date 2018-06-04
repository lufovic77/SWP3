package edu.skku.swp3.metroapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by lufov on 2018-06-04.
 */

public class RouteInfo extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_info_1);

        Intent intent= getIntent();
        String path = intent.getStringExtra("path");
        StringTokenizer st = new StringTokenizer(path,",");
        ArrayList<String> station=new ArrayList<>();
        while(st.hasMoreTokens()) {
            station.add(st.nextToken());
        }
        Toast.makeText(getApplicationContext(), "!!"+station.get(0)+station.get(1), Toast.LENGTH_SHORT).show();


    }
}
