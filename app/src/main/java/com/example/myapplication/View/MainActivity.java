package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication.Model.Model;
import com.example.myapplication.R;
import com.example.myapplication.View.Joystick;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBarThrottle, seekBarRudder;
    private TextView textView;
    private Model model;
    private Joystick joystick;
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        model = new Model();
        joystick = (Joystick) findViewById(R.id.myJoystick);
        joystick.myOnChange = (a, e) -> {
            System.out.println("a: "+(a * 2 - 500) / 360+", e: "+(e * 2 - 500) / 360);

            model.setAileron((a * 2 - 500) / 360);
            model.setElevator((e * 2 - 500) / 360);
        };
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.ed);
        button =  (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText  t = (EditText) findViewById(R.id.ed);
                String ip = t.getText().toString();
                t = (EditText) findViewById(R.id.ed2);
                int port = Integer.parseInt(t.getText().toString());
                model.connect(ip,port);
            }
        });



        seekBarThrottle = (SeekBar) findViewById(R.id.seekBarThrottle);
        seekBarThrottle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Double myProgress = ((double) (progress)) / 100;
                System.out.println("Throttle is " + myProgress);
                model.setThrottle(myProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarRudder = (SeekBar) findViewById(R.id.seekBarRudder);
        seekBarRudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Double myProgress = ((double) (progress) * 2 - 100) / 100;
                System.out.println("rudder is " + myProgress);
//                progressBar.setProgress(progress);
                textView.setText("" + myProgress + "%");
                model.setRudder(myProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        setContentView(new Joystick(this));


    }
}
/*import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;


public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private Model model;
   // private Joystick joystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model= new Model();
        model.connect();
        //joystick = new Joystick(this,200,200,30,100);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                double mypro = ((double)progress*2 - 100)/100;
                model.setVar(mypro);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}*/