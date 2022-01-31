package com.example.h3_ggeuret;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.h3_ggeuret.logic.EggTimer;
import com.example.h3_ggeuret.logic.EggTimeListener;

public class MainActivity extends AppCompatActivity implements EggTimeListener {
    private EggTimer eggTimer = new EggTimer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eggTimer.addTimeListener(this);
    }

    public void onButtonEggSelectedClicked(View view){
        findViewById(R.id.bnStart).setEnabled(true);
        TextView tvTimer = (TextView)findViewById(R.id.tvTimer);

        switch (view.getId()) {
            case R.id.bnSoftBoiled:
                eggTimer.setSeconds(60 * 5);
                break;
            case R.id.bnMediumBoiled:
                eggTimer.setSeconds(60 * 7);
                break;
            case R.id.bnHardBoiled:
                eggTimer.setSeconds(60 * 10);
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }
    }

    public void onButtonStartClicked(final View view){
        TextView tvTimer = (TextView)findViewById(R.id.tvTimer);
        Button bnStart = (Button)findViewById(R.id.bnStart);

        if (bnStart.getText().equals("Start")){
            bnStart.setText("Stop");
            setBoilButtonsEnabled(false);
            eggTimer.start();
        }
        else{
            bnStart.setText("Start");
            setBoilButtonsEnabled(true);
            eggTimer.stop();
        }
    }

    private void setBoilButtonsEnabled(boolean state){
        findViewById(R.id.bnSoftBoiled).setEnabled(state);
        findViewById(R.id.bnMediumBoiled).setEnabled(state);
        findViewById(R.id.bnHardBoiled).setEnabled(state);
    }

    @Override
    public void onTimeUpdate() {
        TextView tvTimer = (TextView)findViewById(R.id.tvTimer);
        tvTimer.setText(eggTimer.toString());
    }

    @Override
    public void onStopped() {
        findViewById(R.id.bnStart).setEnabled(false);
    }
}