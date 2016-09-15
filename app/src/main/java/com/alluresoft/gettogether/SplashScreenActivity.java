package com.alluresoft.gettogether;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vikas on 8/26/2016.
 */
public class SplashScreenActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

      //  Animation anim= AnimationUtils.loadAnimation(this,R.anim.move_up);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                finish();
            }
        },4000);


    }
}
