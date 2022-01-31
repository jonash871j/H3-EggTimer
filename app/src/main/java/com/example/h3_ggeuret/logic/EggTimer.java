package com.example.h3_ggeuret.logic;

import android.os.Handler;

public class EggTimer implements Runnable {
    private int timer;

    public EggTimer(int timer){
        this.timer = timer;
    }

    @Override
    public void run(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do stuff
                if(timer <= 0) {
                }else {
                    handler.postDelayed(this, 1000);
                    timer--;
                }
            }
        },1000);
    }

    public String toString()
    {
        int minutes = timer / 60;
        int second = timer % 60;
        return minutes + ":" + second;
    }
}
