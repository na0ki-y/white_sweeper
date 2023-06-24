package com.example.whiteboard_sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity  extends AppCompatActivity implements View.OnTouchListener,CompoundButton.OnCheckedChangeListener{

    public Button leftbutton;
    public Button leftturnbutton;
    public Button gobutton;

    public Button rightturnbutton;

    public Button rightbutton;
    public TextView textView_ForB;
    public String[][] UrlArray = {
            {"ForwardLeft","TurnForwardLeft", "Forward","TurnForwardRight", "ForwardRight"},
            {"BackLeft","TurnBackLeft", "Back","TurnBackRight", "BackRight"}
    };
    public int num_forward_or_back=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftbutton=(Button) findViewById(R.id.button_left);
        leftturnbutton=(Button) findViewById(R.id.button_left_turn);
        gobutton=(Button) findViewById(R.id.button_go);
        rightturnbutton=(Button) findViewById(R.id.button_right_turn);
        rightbutton=(Button) findViewById(R.id.button_right);
        textView_ForB = (TextView) findViewById(R.id.textView_ForB);

        leftbutton.setOnTouchListener(this);
        leftturnbutton.setOnTouchListener(this);
        gobutton.setOnTouchListener(this);
        rightturnbutton.setOnTouchListener(this);
        rightbutton.setOnTouchListener(this);

        Switch switch_ForB = findViewById(R.id.switch_ForB);
        switch_ForB.setOnCheckedChangeListener(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("RaspiHttp","onTouch");
        int id = view.getId();
        String url_prm="touch";
        if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){//押した時
            if (id == R.id.button_left) {//どのボタンなのかによってurlを決める
                url_prm=UrlArray[num_forward_or_back][0];
            } else if (id == R.id.button_left_turn) {
                url_prm=UrlArray[num_forward_or_back][1];
            } else if (id == R.id.button_go) {
                url_prm=UrlArray[num_forward_or_back][2];
            } else if (id == R.id.button_right_turn) {
                url_prm=UrlArray[num_forward_or_back][3];
            } else if (id == R.id.button_right) {
                url_prm=UrlArray[num_forward_or_back][4];
            } else {

            }
//            task.execute(1);
        }

        else {//離した時
            url_prm="STOP";
        }
        RaspiHttp task=new RaspiHttp(this);
        task.execute(url_prm.toString());
        return true;
    }
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("RaspiHttp","onCheckedChanged");
        if (buttonView.getId() == R.id.switch_ForB) {
            // Switch の状態が変更されたときの処理
            if (isChecked) {
                // Switch が ON の場合の処理
                num_forward_or_back=1;
                textView_ForB.setText("Back");
            } else {
                // Switch が OFF の場合の処理
                num_forward_or_back=0;
                textView_ForB.setText("Forward");

            }
        }
        Log.d("RaspiHttp","num_forward_or_back = " + num_forward_or_back);

    }

}