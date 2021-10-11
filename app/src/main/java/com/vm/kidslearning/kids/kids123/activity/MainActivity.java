package com.vm.kidslearning.kids.kids123.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.vm.kidslearning.kids.kids123.R;
import com.vm.kidslearning.kids.kids123.adapter.GalleryAdapter;
import com.vm.kidslearning.kids.kids123.utility.GridSpacingItemDecoration;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView rc_categoryView;
    private GalleryAdapter mAdapter;
    private ProgressDialog pDialog;

    String imageFilePath = "file:///android_asset/dashboard/";
    private AssetManager assetManager;
    private ArrayList<String> image_assets;
    String[] imgPath;

    LinearLayout linearlayout;
    LinearLayout ad_mlayout,re_mlayout;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Learn Words");

        int spanCount = 2; // 2 columns
        int spacing =30; // 30px
        boolean includeEdge = true;

        rc_categoryView = findViewById(R.id.recycler_view);
        assetManager = getAssets();
        pDialog = new ProgressDialog(this);

        image_assets=new ArrayList<>();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-1113372948108698~7659497393");
        re_mlayout = findViewById(R.id.cat_Rclayout);
        linearlayout=(LinearLayout) findViewById(R.id.cat_main_layout);

        AdRequest adRequest = new AdRequest.Builder().build();



        mAdapter = new GalleryAdapter(getApplicationContext(), image_assets);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rc_categoryView.setLayoutManager(mLayoutManager);
        rc_categoryView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        rc_categoryView.setItemAnimator(new DefaultItemAnimator());
        rc_categoryView.setAdapter(mAdapter);

        rc_categoryView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(this, rc_categoryView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getApplicationContext(),Detail_Activity.class);
                i.putExtra("position",String.valueOf(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        fetchImages();
    }

    private void fetchImages() {

        try {
            imgPath = assetManager.list("dashboard");
            Log.d("Path", imgPath.toString());
            //image_assets= Arrays.asList(imgPath);
            //Log.d("Path1", image_assets.toString());
            for (int i = 0; i< imgPath.length; i++) {
                image_assets.add(imageFilePath+imgPath[i]);
                Log.d("Completepath", imageFilePath+imgPath[i].toString());

            }
            mAdapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //    boolean doubleBackToExitPressedOnce = false;
//    @Override
//    public void onBackPressed() {
//
//        if(doubleBackToExitPressedOnce)
//        {
//            mInterstitialAd = new InterstitialAd(getApplicationContext());
//
//            // set the ad unit ID
//            mInterstitialAd.setAdUnitId("ca-app-pub-1113372948108698/1471710604");
//
//            AdRequest adRequestInter = new AdRequest.Builder()
//                    .build();
//
//            // Load ads into Interstitial Ads
//            mInterstitialAd.loadAd(adRequestInter);
//
//            mInterstitialAd.setAdListener(new AdListener() {
//                public void onAdLoaded() {
//                    showInterstitial();
//                }
//            });
//            super.onBackPressed();
//
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//
////        Snackbar snackbar = Snackbar
////                .make(linearlayout, "Tap Back Again to Exit", Snackbar.LENGTH_SHORT);
////
////        snackbar.show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//
//    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
