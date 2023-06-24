package com.example.whiteboard_sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

public class MainActivity  extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener  {

    public Button leftbutton;
    public Button leftturnbutton;
    public Button gobutton;

    public Button rightturnbutton;

    public Button rightbutton;
    public String[][] UrlArray = {
            {"ForwardLeft","TurnForwardRight", "Forward","TurnForwardLeft", "ForwardRight"},
            {"BackLeft","TurnBackRight", "Back","TurnBackLeft", "BackRight"}
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

        leftbutton.setOnClickListener(this);
        leftbutton.setOnLongClickListener(this);
        leftturnbutton.setOnClickListener(this);
        leftturnbutton.setOnLongClickListener(this);
        gobutton.setOnClickListener(this);
        gobutton.setOnLongClickListener(this);
        rightturnbutton.setOnClickListener(this);
        rightturnbutton.setOnLongClickListener(this);
        rightbutton.setOnClickListener(this);
        rightbutton.setOnLongClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        String url_prm="click";
        if (id == R.id.button_left) {
            url_prm=UrlArray[num_forward_or_back][0];
        } else if (id == R.id.button_go) {

        } else if (id == R.id.button_right) {

        } else {

        }
        RaspiHttp task=new RaspiHttp(this);
        task.execute(url_prm.toString());
    }

    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        String url_prm="Long";
        if (id == R.id.button_left) {
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
        RaspiHttp task=new RaspiHttp(this);
        task.execute(url_prm.toString());
        return false;
    }
}