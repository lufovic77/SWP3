package edu.skku.swp3.metroapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by lufov on 2018-06-04.
 */

public class RouteInfo extends Activity {
    private TextView elapsed;
    private TextView time;
    private TextView deTime;
    private TextView arTime;
    private TextView deName;
    private TextView arName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_info_1);
        elapsed=(TextView)findViewById(R.id.elapsed);
        time=(TextView)findViewById(R.id.time);
        deTime=(TextView)findViewById(R.id.deTime);
        arTime=(TextView)findViewById(R.id.arTime);
        deName=(TextView)findViewById(R.id.deName);
        arName=(TextView)findViewById(R.id.arName);

        Intent intent= getIntent();
        String path = intent.getStringExtra("path");
        StringTokenizer st = new StringTokenizer(path,",");
        ArrayList<String> station=new ArrayList<>();
        while(st.hasMoreTokens()) {
            station.add(st.nextToken());
        }
        deName.setText(station.get(0));
        arName.setText(station.get(1));

    }
}
