package edu.skku.swp3.metroapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

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
    private long minutes;
    private long si;
    private long bun;
    private PathData path;

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

    private void scaleContents(View rootView, View container) {
        // Compute the scaling ratio
        float xScale = (float) container.getWidth() / rootView.getWidth();
        float yScale = (float) container.getHeight() / rootView.getHeight();
        float scale = Math.min(xScale, yScale);

        // Scale our contents
        scaleViewAndChildren(rootView, scale);
    }

    // Scale the given view, its contents, and all of its children by the given factor.
    public static void scaleViewAndChildren(View root, float scale) {
        // Retrieve the view's layout information
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        // Scale the view itself
        if (layoutParams.width != ViewGroup.LayoutParams.FILL_PARENT &&
                layoutParams.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
            layoutParams.width *= scale;
        }
        if (layoutParams.height != ViewGroup.LayoutParams.FILL_PARENT &&
                layoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
            layoutParams.height *= scale;
        }

        // If this view has margins, scale those too
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginParams =
                    (ViewGroup.MarginLayoutParams) layoutParams;
            marginParams.leftMargin *= scale;
            marginParams.rightMargin *= scale;
            marginParams.topMargin *= scale;
            marginParams.bottomMargin *= scale;
        }

        // Set the layout information back into the view
        root.setLayoutParams(layoutParams);
        root.setPadding(
                (int)(root.getPaddingLeft() * scale),
                (int)(root.getPaddingTop() * scale),
                (int)(root.getPaddingRight() * scale),
                (int)(root.getPaddingBottom() * scale));

        // If the root view is a TextView, scale the size of its text
        if (root instanceof TextView)
        {
            TextView textView = (TextView)root;
            textView.setTextSize(textView.getTextSize() * scale);
        }

        // If the root view is a ViewGroup, scale all of its children recursively
        if (root instanceof ViewGroup)
        {
            ViewGroup groupView = (ViewGroup)root;
            for (int cnt = 0; cnt < groupView.getChildCount(); ++cnt)
                scaleViewAndChildren(groupView.getChildAt(cnt), scale);
        }
    }

}

