package com.example.compulsorydicecup;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button butt2;
    ViewGroup constLayout;
    ViewGroup constLayout2;
    Random rand = new Random();
    int kurwa = 0;
    NumberPicker num;

    Button butt3;
    ArrayList<int[]> diceSets = new ArrayList<>();

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            dick();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constLayout2 = new LinearLayout(this);
        constLayout = (ViewGroup) findViewById(R.id.layBItch);
        num = new NumberPicker(this);
        constLayout.addView(constLayout2);
        butt2 = new Button(this);
        butt2.setText("Roll the D");
        constLayout.addView(butt2);
        constLayout.addView(num);
        num.setMinValue(0);
        num.setMaxValue(6);
        butt2.setOnClickListener(mCorkyListener);

        butt3 = new Button(this);
        constLayout.addView(butt3);
        butt3.setText("Rolled dice sets");
        butt3.setOnClickListener(openDiceSetsActivity);
    }

    private View.OnClickListener openDiceSetsActivity = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, DiceSetsActivity.class);
            i.putExtra("diceSetsSize", diceSets.size());
            for (int j = 0; j < diceSets.size(); j++) {
                i.putExtra("diceSets" + j, diceSets.get(j));
            }
            MainActivity.this.startActivity(i);
        }
    };

    public View.OnClickListener kutaz() {
        dick();
        return null;
    }

    private void dick() {
        kurwa = Integer.parseInt("" +num.getValue());
        constLayout2.removeAllViews();

        int[] ds = new int[6];

        for(int i=0; i<kurwa; i++) {

            int r = rand.nextInt(6) + 1;
            ds[i] = r;

            drawDie(r, constLayout2);
        }

        diceSets.add(ds);
    }

    private void drawDie(int die, ViewGroup view) {
        View draw = new DrawView(this, die);
        view.addView(draw, 100, 100);
        TextView txt = new TextView(this);
    }

}
