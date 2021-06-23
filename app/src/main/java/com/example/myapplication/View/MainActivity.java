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
import com.example.myapplication.ViewModel.ViewModel;
// The main view of the application part of the MVVM architecture
public class MainActivity extends AppCompatActivity {
    private ViewModel viewModel;

    @Override
    // create the views
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModel(new Model());
        setContentView(R.layout.activity_main);

        //Joystick view
        Joystick joystick = (Joystick) findViewById(R.id.myJoystick);
        // data binding when change the joystick
        joystick.myOnChange = (a, e) -> {
            viewModel.setAileron((a * 2 - 500) / 360);
            viewModel.setElevator((e * 2 - 500) / 360);
        };

        TextView textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.ed);
        Button button = (Button) findViewById(R.id.button);

        // data binding when the button is pressed connect to the FG
        button.setOnClickListener(v -> {
            EditText t = (EditText) findViewById(R.id.ed);
            String ip = t.getText().toString();
            t = (EditText) findViewById(R.id.ed2);
            int port = Integer.parseInt(t.getText().toString());
            viewModel.connectVM(ip, port);
        });

        //The vertical SeekBar responsible on the Throttle
        SeekBar seekBarThrottle = (SeekBar) findViewById(R.id.seekBarThrottle);
        seekBarThrottle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double myProgress = ((double) (progress)) / 100;
                viewModel.setThrottle(myProgress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //The horizontal SeekBar responsible on the Rudder
        SeekBar seekBarRudder = (SeekBar) findViewById(R.id.seekBarRudder);
        seekBarRudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double myProgress = ((double) (progress) * 2 - 100) / 100;
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