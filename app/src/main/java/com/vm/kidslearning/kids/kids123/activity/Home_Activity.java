package com.vm.kidslearning.kids.kids123.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.snackbar.Snackbar;
import com.vm.kidslearning.kids.kids123.R;

public class Home_Activity extends AppCompatActivity {
    Button btnLearn, btnTest, btnUManual;
    LinearLayout linearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        linearlayout = findViewById(R.id.linearlayout);

        btnLearn = findViewById(R.id.learn);
        btnTest = findViewById(R.id.test);
        btnUManual = findViewById(R.id.btnUManual);
        btnLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Home_Activity.this, MainActivity.class);
                startActivity(in);
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Home_Activity.this, TCategory_Activity.class);
                startActivity(in);
            }
        });

        btnUManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Home_Activity.this, User_Manual_Activity.class);
                startActivity(in);
            }
        });
    }
    InterstitialAd mInterstitialAd;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {

        if(doubleBackToExitPressedOnce)
        {
            mInterstitialAd = new InterstitialAd(getApplicationContext());

            // set the ad unit ID
            mInterstitialAd.setAdUnitId("ca-app-pub-1113372948108698/1471710604");

            AdRequest adRequestInter = new AdRequest.Builder()
                    .build();

            // Load ads into Interstitial Ads
            mInterstitialAd.loadAd(adRequestInter);

            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    showInterstitial();
                }
            });
            super.onBackPressed();

            return;
        }

        this.doubleBackToExitPressedOnce = true;

        Snackbar snackbar = Snackbar
                .make(linearlayout, "Tap Back Again to Exit", Snackbar.LENGTH_SHORT);

        snackbar.show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}