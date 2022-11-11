package com.koban.timer;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimerFragment extends Fragment implements View.OnClickListener{

    private TimerTask mTimerTask;
    private final Timer mTimer = new Timer();

    private Button btn_start;
    private Button btn_stop;

    private TextView tv_timer ;

    private int i_hor = 0;
    private int i_min = 0;
    private int i_sec = -1;

    private View fake_An;

    private int count = 0;

    public TimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        fake_An = (View) view.findViewById(R.id.Fake_android);
        tv_timer =  (TextView) view.findViewById(R.id.timer_01);
        btn_start = (Button) view.findViewById(R.id.btn_start);
        btn_stop = (Button) view.findViewById(R.id.btn_stop);
        Context context = inflater.getContext();

        // 숨겨진 기능 찾으면 대박 .. !
        fake_An.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count ++;
                Log.e("qqq", String.valueOf(count));

                if (count >= 15) {
                    Toast.makeText(context, "개발자 계좌 \n xxx xxx xxxxxx  xxx ", Toast.LENGTH_LONG).show();
                }
            }
        });


        SaveFragment saveFragment = new SaveFragment();









        // timer start btn
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start.setVisibility(View.GONE);
                btn_stop.setVisibility(View.VISIBLE);

                if (btn_start.getVisibility() != View.GONE) {
                    btn_start.setVisibility(View.GONE);
                }

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
                    }
                };
                mTimer.schedule(mTimerTask, 0, 1000);
//                mTimer.schedule(mTimerTask, 0, 1);
            }
        });

        // timer stop btn
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start.setVisibility(View.VISIBLE);
                btn_stop.setVisibility(View.GONE);
                String inputData = tv_timer.getText().toString();

                mTimer.purge();
                mTimerTask.cancel();
                mTimerTask = null;

                i_hor = 0;
                i_min = 0;
                i_sec = -1;

                tv_timer.setText("00 : 00 : 00");

                Toast.makeText(context, "저장 후 초기화", Toast.LENGTH_SHORT).show();


                // TODO 프레그먼트끼리 데이터 전달시 번들 사용하여 하기.
                Bundle bundle = new Bundle();
                bundle.putString("tag", inputData);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

//                SaveFragment saveFragment = new SaveFragment();
                saveFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frame, saveFragment);
                fragmentTransaction.commit();


            }

        });

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
