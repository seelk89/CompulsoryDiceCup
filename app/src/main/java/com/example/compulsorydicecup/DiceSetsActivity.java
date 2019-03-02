package com.example.compulsorydicecup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DiceSetsActivity extends AppCompatActivity {

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    ArrayList<String> diceDate = new ArrayList<>();
    ViewGroup constLayout;
    ViewGroup constLayout2;
    Button butt;

    ArrayList<ArrayList<Integer>> diceSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_sets);
        constLayout = (ViewGroup) findViewById(R.id.linearLayout1);
        butt = new Button(this);
        constLayout.addView(butt);
        butt.setText("Clear");
        butt.setOnClickListener(clearLayout2);
        drawDiceSets();
    }

    private void drawDiceSets() {
        diceDate = getIntent().getStringArrayListExtra("rollDate");
        for (int i = 0; i < getIntent().getIntExtra("diceSetsSize", 0); i++) {
            diceSets.add(getIntent().getIntegerArrayListExtra("diceSets" + i));
        }
        for (int i = 0; i < diceSets.size(); i++) {
            constLayout2 = new LinearLayout(this);
            constLayout.addView(constLayout2);
            ArrayList<Integer> ds = diceSets.get(i);
            for (int j = 0; j < ds.size(); j++) {
                drawDie(ds.get(j), constLayout2);

            }
            TextView rollDate = new TextView(this);
            constLayout2.addView(rollDate);
            rollDate.setText(diceDate.get(i));
        }
    }

    private View.OnClickListener clearLayout2 = new View.OnClickListener() {
        public void onClick(View v) {
            constLayout.removeViews(1, constLayout.getChildCount() - 1);
            Intent intent=new Intent();
            setResult(1, intent);
            finish();
        }
    };

    private void drawDie(int die, ViewGroup view) {
        View draw = new DrawView(this, die);
        view.addView(draw, 100, 100);
    }
}
