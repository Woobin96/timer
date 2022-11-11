package com.koban.timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bng);

        TimerFragment timerFragment = new TimerFragment();
        SaveFragment saveFragment = new SaveFragment();

        // 프레그먼트 초기화면 설정
        getSupportFragmentManager().beginTransaction().add(R.id.frame, new TimerFragment()).commit();

        // 바텀네비게이션 스위치 작업
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.timer :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, timerFragment).commit();
                        break;

                    case R.id.save :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, saveFragment).commit();
                        break;
                }
                return true;
            }
        });

    }
}