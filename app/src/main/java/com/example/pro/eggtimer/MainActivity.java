package com.example.pro.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timeSeekBar;
    TextView timerTextView;
    Boolean counterInActive = false;
    Button controlButton;
    CountDownTimer countDownTimer;

    public void resetTimer() {
        timerTextView.setText("O:30");
        timeSeekBar.setProgress(30);
        countDownTimer.cancel();
        controlButton.setText("Go!");
        timeSeekBar.setEnabled(true);
        counterInActive = false;
    }
    public void updateTimer(int secondLeft) {
        int minutes = (int) secondLeft/ 60;
        int seconds = secondLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (secondString == "0") {
            secondString = "00";
        }else if (seconds <= 9) {
            secondString = "0" + secondString;
        }

        timerTextView.setText(Integer.toString(minutes)+":"+secondString);
    }
    public void controlTimer(View view) {

        if (counterInActive == false) {

            counterInActive = true;
            timeSeekBar.setEnabled(false);
            controlButton.setText("Stop");
            new CountDownTimer(timeSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0:00");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    mplayer.start();

                }
            }.start();
        } else {
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        controlButton = (Button) findViewById(R.id.controllerButton);
        timeSeekBar.setMax(600);
        timeSeekBar.setProgress(30);
        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {


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
