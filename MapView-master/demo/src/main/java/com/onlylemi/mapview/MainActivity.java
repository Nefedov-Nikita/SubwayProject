package com.onlylemi.mapview;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ImageView logo;

    //private static final String TAG = "MainActivity";
    private static int SplashTime = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = (ImageView) findViewById(R.id.imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, MarkLayerTestActivity
                        .class));
                logo.setImageDrawable(null);
            }
        },SplashTime);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
