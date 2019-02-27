package com.example.compulsorydicecup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class DiceSetsActivity extends AppCompatActivity {

    ViewGroup constLayout;
    ViewGroup constLayout2;
    Button butt;

    ArrayList<int[]> diceSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_sets);
        constLayout = (ViewGroup) findViewById(R.id.linearLayout1);
        constLayout2 = new LinearLayout(this);
        constLayout.addView(constLayout2);
        butt = new Button(this);
        constLayout.addView(butt);
        butt.setText("Clear");
        //butt.setOnClickListener();
    }

    private void drawDiceSets() {
        for (int i = 0; i < getIntent().getIntExtra("diceSetsSize", 0); i++) {
            diceSets.add(getIntent().getIntArrayExtra("diceSets" + i));
        }
    }
}
