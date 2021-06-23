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
import com.example.myapplication.ViewModel.ViewModel;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModel(new Model());

        setContentView(R.layout.activity_main);

        Joystick joystick = (Joystick) findViewById(R.id.myJoystick);
        joystick.myOnChange = (a, e) -> {
            System.out.println("a: " + (a * 2 - 500) / 360 + ", e: " + (e * 2 - 500) / 360);
            viewModel.setAileron((a * 2 - 500) / 360);
            viewModel.setElevator((e * 2 - 500) / 360);
        };

        textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.ed);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t = (EditText) findViewById(R.id.ed);
                String ip = t.getText().toString();
                t = (EditText) findViewById(R.id.ed2);
                int port = Integer.parseInt(t.getText().toString());
                viewModel.connectVM(ip, port);
            }
        });


        SeekBar seekBarThrottle = (SeekBar) findViewById(R.id.seekBarThrottle);
        seekBarThrottle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Double myProgress = ((double) (progress)) / 100;
                System.out.println("Throttle is " + myProgress);
                viewModel.setThrottle(myProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar seekBarRudder = (SeekBar) findViewById(R.id.seekBarRudder);
        seekBarRudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double myProgress = ((double) (progress) * 2 - 100) / 100;
                System.out.println("rudder is " + myProgress);
//                progressBar.setProgress(progress);
                textView.setText("" + myProgress + "%");
                viewModel.setRudder(myProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}