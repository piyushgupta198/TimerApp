package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    boolean checkActiveTimer = false;
    TextView timerText;
    Button timerButton;
    CountDownTimer counter;

    public void resetTimer() {

        timerText.setText("00:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        timerButton.setText("GO!!");
        checkActiveTimer = false;
        counter.cancel();
    }

    public void Timer(View view){

        if(checkActiveTimer){
            resetTimer();
        }

        else {
            checkActiveTimer = true;
            timerSeekBar.setEnabled(false);
            timerButton.setText("Stop!");

            counter = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) (millisUntilFinished / 1000));

                }

                @Override
                public void onFinish() {

                    MediaPlayer timerTone = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    timerTone.start();
                    resetTimer();

                }
            }.start();
        }

    }

    public void updateTimer(int progress){

        int minute = progress/60;
        int second =progress-(minute*60);
        String secondConverter = Integer.toString(second);

        if(second <=9){
            secondConverter= "0"+secondConverter;
        }

        timerText.setText( Integer.toString(minute)+ ":" + secondConverter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerText= (TextView) findViewById(R.id.timerText);
        timerButton = (Button) findViewById(R.id.buton);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
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
