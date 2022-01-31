package com.example.h3_ggeuret;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.h3_ggeuret.logic.EggTimer;

public class MainActivity extends AppCompatActivity {

    private EggTimer eggTimer;
    private int dummy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
//        eggTimer = new EggTimer(100);
//        handler.post(eggTimer);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView tvTimer = (TextView)findViewById(R.id.tvTimer);
                tvTimer.setText(dummy);
                dummy++;
            }
        },1000);
    }

    public void onButtonEggSelectedClicked(View view){
        findViewById(R.id.bnStart).setEnabled(true);
        TextView tvTimer = (TextView)findViewById(R.id.tvTimer);

        switch (view.getId()) {
            case R.id.bnSoftBoil:
                tvTimer.setText("5:00");
                break;
            case R.id.bnMediumBoiled:
                tvTimer.setText("7:00");
                break;
            case R.id.bnHardBoiled:
                tvTimer.setText("10:00");
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
    }

    public void onButtonStartClicked(final View view){

    }
}