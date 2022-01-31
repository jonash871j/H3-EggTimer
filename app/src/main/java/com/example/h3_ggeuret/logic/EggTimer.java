package com.example.h3_ggeuret.logic;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class EggTimer implements EggTimeListener {
    private int seconds;
    private boolean isStarted = false;
    private List<EggTimeListener> timeListeners = new ArrayList<EggTimeListener>();
    private Handler handler;

    public EggTimer(){
        beginTimingLogic();
    }

    public void addTimeListener(EggTimeListener timeListener){
        timeListeners.add(timeListener);
    }

    public void removeTimeListener(EggTimeListener timeListener){
        timeListeners.remove(timeListener);
    }

    public void setSeconds(int seconds){
        this.seconds = seconds;
        onTimeUpdate();
    }

    public void start(){
        isStarted = true;
    }

    public void stop(){
        seconds = 0;
        isStarted = false;
        onTimeUpdate();
        onStopped();
    }

    public String toString(){
        int minutes = seconds / 60;
        int second = seconds % 60;
        return String.format("%02d", minutes) + ":" + String.format("%02d", second);
    }

    @Override
    public void onTimeUpdate() {
        for (EggTimeListener timeListener : timeListeners) {
            timeListener.onTimeUpdate();
        }
    }

    @Override
    public void onStopped() {
        for (EggTimeListener timeListener : timeListeners) {
            timeListener.onStopped();
        }
    }

    private void beginTimingLogic(){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isStarted) {
                    if (seconds <= 0) {
                        isStarted = false;
                    } else {
                        seconds--;
                    }
                    onTimeUpdate();
                }
                handler.postDelayed(this, 1000);
            }
        },1000);
    }
}
