package com.vm.kidslearning.kids.kids123.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.vm.kidslearning.kids.kids123.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

//import androidx.core.view.PagerAdapter;
//import androidx.core.view.ViewPager;

public class Detail_Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    String imageFilePath = "file:///android_asset/";
    private AssetManager assetManager;
    private ArrayList<String> image_assets;
    String[] imgPath;
    private ProgressDialog pDialog;
    int pos;
    TextToSpeech t1;
    ImageView imgSoundStatus;

    String numbers[]= {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty",
            "Twenty-One", "Twenty-Two", "Twenty-Three", "Twenty-Four", "Twenty-Five","Twenty-Six", "Twenty-Seven", "Twenty-Eight", "Twenty-Nine", "Thirty",
            "Thirty-One", "Thirty-Two", "Thirty-Three", "Thirty-Four", "Thirty-Five","Thirty-Six", "Thirty-Seven", "Thirty-Eight", "Thirty-Nine", "Forty",
            "Forty-One", "Forty-Two", "Forty-Three", "Forty-Four", "Forty-Five","Forty-Six", "Forty-Seven", "Forty-Eight", "Forty-Nine", "Fifty"};

    String alphabets[] = {"Apple", "Ball", "Cat", "Dog", "Elephant", "Fish", "Grapes", "Horse", "Ice-Cream", "Joker", "Kite",
            "Lion","Monkey", "Nest", "Orange", "Peacock", "Queen", "Rat", "Ship", "Tree", "Umbrella", "Violin", "Watch", "X-ray", "Yan", "Zebra"};

    String animals[] = {"Buffalo", "Cat", "Cheetah", "Cow", "Crocodile", "Fox", "Giraffe", "Hippo", "Horse", "Leopard",
            "Lion","Rabbit", "Tiger", "Whale", "Zebra"};

    String colors[] = {"Black", "Blue", "Brown", "Gray", "Green", "Maroon", "Orange", "Pink", "Purple", "Red",
            "White","Yellow"};

    String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
            "November","December"};

    String flowers[] = {"Chamomile", "Dahila", "Lily", "Lotus", "Reslight", "Rose", "Sunflower"};

    String fruits[] = {"Apple", "Apricot", "Avocado", "Balckberry", "Banana", "Cherry", "Custard", "Dragon", "Grapes", "Guava", "Jack Fruit",
            "Kiwi","Litchis", "Mango", "Orange", "Passion", "Peach", "Pear", "Pepaya", "Pineapple", "Pomegranate", "Raspberry", "Stoberry", "Watermelon"};

    String shapes[] = {"Circle", "Cone", "Cylinder", "Heart", "Ractangle", "Square", "Star", "Triangle"};

    String vegetables[] = {"Beetroot", "Brinjal", "Brocoli", "Cabbage", "Carrot", "Chili", "Cucumber", "Guar", "Ladies Finger", "Onion", "Parwal",
            "Pepper","Potato", "Radis", "Sweet Potato", "Tomato"};

    String vehicles[] = {"Airplane", "Ambulance", "Bicycle", "Bike", "Bus", "Car", "Helicopter", "Rickshaw", "Schoolbus", "Tractor", "Train",
            "Tram", "Truck"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        assetManager = getAssets();
        pDialog = new ProgressDialog(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.app_id));

        AdRequest adRequest = new AdRequest.Builder().build();

        imgSoundStatus = findViewById(R.id.imgSound);
        final AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        imgSoundStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                int media_current_volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                audioManager.setStreamVolume(
                        AudioManager.STREAM_MUSIC, // Stream type
                        media_current_volume, // Index
                        AudioManager.FLAG_SHOW_UI // Flags
                );;

                //imgSoundStatus.setImageResource(R.drawable.sound_disable);
            }
        });

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });

        pos = Integer.parseInt(getIntent().getStringExtra("position"));
        if(pos==0)
        {
            imageFilePath = imageFilePath+"1-50/";
            getSupportActionBar().setTitle("Numbers");
        }
        else if(pos==1)
        {
            imageFilePath = imageFilePath+"a-z/";
            getSupportActionBar().setTitle("Alphabets");
        }
        else if(pos==2)
        {
            imageFilePath = imageFilePath+"animals/";
            getSupportActionBar().setTitle("Animals");
        }
        else if(pos==3)
        {
            imageFilePath = imageFilePath+"colors/";
            getSupportActionBar().setTitle("Colors");
        }
        else if(pos==4)
        {
            imageFilePath = imageFilePath+"days/";
            getSupportActionBar().setTitle("Days");
        }
        else if(pos==5)
        {
            imageFilePath = imageFilePath+"flowers/";
            getSupportActionBar().setTitle("Flowers");
        }
        else if(pos==6)
        {
            imageFilePath = imageFilePath+"fruits/";
            getSupportActionBar().setTitle("Fruits");
        }
        else if(pos==7)
        {
            imageFilePath = imageFilePath+"months/";
            getSupportActionBar().setTitle("Months");
        }
        else if(pos==8)
        {
            imageFilePath = imageFilePath+"shapes/";
            getSupportActionBar().setTitle("Shapes");
        }
        else if(pos==9)
        {
            imageFilePath = imageFilePath+"vegetables/";
            getSupportActionBar().setTitle("Vegetables");
        }
        else
        {
            imageFilePath = imageFilePath+"vehicles/";
            getSupportActionBar().setTitle("Vehicles");
        }

        image_assets=new ArrayList<>();
        fetchImages();

        viewPager = (ViewPager) findViewById(R.id.animal_viewpager);
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);

        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    private void fetchImages() {
        try {
            if(pos==0)
            {
                imgPath = assetManager.list("1-50");
            }
            else if(pos==1)
            {
                imgPath = assetManager.list("a-z");
            }
            else if(pos==2)
            {
                imgPath = assetManager.list("animals");
            }
            else if(pos==3)
            {
                imgPath = assetManager.list("colors");
            }
            else if(pos==4)
            {
                imgPath = assetManager.list("days");
            }
            else if(pos==5)
            {
                imgPath = assetManager.list("flowers");
            }
            else if(pos==6)
            {
                imgPath = assetManager.list("fruits");
            }
            else if(pos==7)
            {
                imgPath = assetManager.list("months");
            }
            else if(pos==8)
            {
                imgPath = assetManager.list("shapes");
            }
            else if(pos==9)
            {
                imgPath = assetManager.list("vegetables");
            }
            else
            {
                imgPath = assetManager.list("vehicles");
            }
            Log.d("Path", imgPath.toString());
            //image_assets= Arrays.asList(imgPath);
            //Log.d("Path1", image_assets.toString());
            for (int i = 0; i< imgPath.length; i++) {
                image_assets.add(imageFilePath+imgPath[i]);
                Log.d("Completepath", imageFilePath+imgPath[i].toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            //displayMetaInfo(position);
            if(pos==0)
            {
                t1.speak(numbers[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(pos==1)
            {
                t1.speak(alphabets[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(pos==2)
            {
                t1.speak(animals[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(pos==3)
            {
                t1.speak(colors[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(pos==4)
            {
                t1.speak(days[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(pos==5)
            {
                t1.speak(flowers[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(pos==6)
            {
                t1.speak(fruits[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(pos==7)
            {
                t1.speak(months[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(pos==8)
            {
                t1.speak(shapes[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(pos==9)
            {
                t1.speak(vegetables[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else
            {
                t1.speak(vehicles[position], TextToSpeech.QUEUE_FLUSH, null);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    //Adapter
    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            layoutInflater = (LayoutInflater)  getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

            ImageView imageViewPreview = (ImageView) view.findViewById(R.id.image_preview);
            TextView txtName = (TextView) view.findViewById(R.id.txt_lblCat);
            Glide.with(getApplicationContext()).load(image_assets.get(position))
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageViewPreview);
            if(pos==0)
            {
                txtName.setText(numbers[position]);
                // t1.speak(numbers[position], TextToSpeech.QUEUE_FLUSH, null);
            }
            else if(pos==1)
            {
                txtName.setText(alphabets[position]);
            }
            else if(pos==2)
            {
                txtName.setText(animals[position]);
            }
            else if(pos==3)
            {
                txtName.setText(colors[position]);
            }
            else if(pos==4)
            {
                txtName.setText(days[position]);
            }
            else if(pos==5)
            {
                txtName.setText(flowers[position]);
            }
            else if(pos==6)
            {
                txtName.setText(fruits[position]);
            }
            else if(pos==7)
            {
                txtName.setText(months[position]);
            }
            else if(pos==8)
            {
                txtName.setText(shapes[position]);
            }
            else if(pos==9)
            {
                txtName.setText(vegetables[position]);
            }
            else
            {
                txtName.setText(vehicles[position]);
            }

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return image_assets.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == ((View) obj);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch( item.getItemId())
        {
            case android.R.id.home:

                Intent i  = new Intent(getApplicationContext(),MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
