package com.example.compulsorydicecup;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Button butt;
    ViewGroup mainLayout;
    ViewGroup dieLayout;
    Random rand = new Random();
    int dieNumber = 0;
    NumberPicker num;
    ArrayList<Integer> savedDice;
    ArrayList<String> rollDate;
    float x1,x2;

    ArrayList<ArrayList<Integer>> diceSets = new ArrayList<>();

    private View.OnClickListener rollDieListener = new View.OnClickListener() {
        public void onClick(View v) {
            rollDice();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            savedDice = new ArrayList<>();
            rollDate = new ArrayList<>();
            dieLayout = new LinearLayout(this);
            mainLayout = (ViewGroup) findViewById(R.id.mainLayout);
            num = new NumberPicker(this);
            butt = new Button(this);

            mainLayout.addView(dieLayout);
            mainLayout.addView(butt);
            mainLayout.addView(num);

            butt.setText("Roll the D");
            num.setMinValue(0);
            num.setMaxValue(6);
            butt.setOnClickListener(rollDieListener);
        }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putIntegerArrayList("savedDice", savedDice);
        savedInstanceState.putStringArrayList("rollDate", rollDate);
        savedInstanceState.putInt("numValue",num.getValue());

        for (int i=0; i<diceSets.size(); i++) {
            savedInstanceState.putIntegerArrayList("diceSets" + i, diceSets.get(i));
        }
        savedInstanceState.putInt("diceSetsSize",diceSets.size());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedDice = savedInstanceState.getIntegerArrayList("savedDice");
        rollDate = savedInstanceState.getStringArrayList("rollDate");
        num.setValue(savedInstanceState.getInt("numValue"));

        for (int i =0; i< savedInstanceState.getInt("diceSetsSize");i++) {
            diceSets.add(savedInstanceState.getIntegerArrayList("diceSets" + i));
        }
        checkForSavedData();
    }

    private void rollDice() {
        dieNumber = Integer.parseInt("" +num.getValue());
        dieLayout.removeAllViews();

        ArrayList<Integer> dr = new ArrayList<>();
        savedDice.clear();
        rollDate.add(format.format(new Date()));
        for(int i=0; i<dieNumber; i++) {
            int roll = rand.nextInt(6) + 1;
            savedDice.add(roll);
            dr.add(roll);
            drawDie(roll, dieLayout);
        }
        diceSets.add(dr);
    }

    private void drawDie(int die, ViewGroup view) {
        View draw = new DrawView(this, die);
        view.addView(draw, 100, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK)
        {
            diceSets.clear();
        }
    }

    private void checkForSavedData() {
        if(savedDice.size() > 0 ) {
            drawSavedDice();
        }
    }

    private void drawSavedDice() {

        for(int dieNumber : savedDice) {
            drawDie(dieNumber, dieLayout);
        }
    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();

                if( x2 < x1 - 100) {
                    Intent i = new Intent(MainActivity.this, DiceSetsActivity.class);
                    i.putExtra("diceSetsSize", diceSets.size());
                    i.putStringArrayListExtra("rollDate", rollDate);
                    for (int j = 0; j < diceSets.size(); j++) {
                        i.putExtra("diceSets" + j, diceSets.get(j));
                    }
                    MainActivity.this.startActivityForResult(i, 1);
                }
                break;
        }
        return  false;
    }
}
