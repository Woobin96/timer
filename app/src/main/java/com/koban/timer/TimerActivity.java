package com.koban.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {

    private TimerTask mTimerTask;
    private final Timer mTimer = new Timer();

    private Button btn_start;
    private Button btn_stop;

    private TextView tv_timer ;

    private int i_hor = 0;
    private int i_min = 0;
    private int i_sec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        tv_timer =  findViewById(R.id.timer_01);

        // timer start btn
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start.setVisibility(View.GONE);
                btn_stop.setVisibility(View.VISIBLE);

                Log.e("qqq", "qqq");

                mTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        i_sec ++;

                        if (i_sec > 59) {
                            i_min ++;
                            i_sec = 0;
                        }

                        if (i_min > 59) {
                            i_hor ++;
                            i_min = 0;
                        }

                        final String mhour = String.format(Locale.getDefault(),"%02d", i_hor);
                        final String minute = String.format(Locale.getDefault(),"%02d", i_min);
                        final String second = String.format(Locale.getDefault(),"%02d", i_sec);

                        tv_timer.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_timer.setText(mhour + " : " + minute + " : " + second);
                            }
                        });

                        Log.e("qqq", "" + second);
                        Log.e("qqq", "" + minute);
                    }
                };
                try {
                    mTimer.schedule(mTimerTask, 2, 1000);
                }catch (Exception e){
                    Log.e("[timer_error]", "Exception OK");
                    Toast.makeText(TimerActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // timer stop btn
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start.setVisibility(View.VISIBLE);
                btn_stop.setVisibility(View.GONE);

                mTimer.purge();
                mTimerTask.cancel();
                mTimerTask = null;

                i_hor = 0;
                i_min = 0;
                i_sec = 0;

                tv_timer.setText("00 : 00 : 00");

                Toast.makeText(TimerActivity.this, "저장 후 초기화", Toast.LENGTH_SHORT).show();

            }
        });

    }
}