package ucucite.cite.scrathflashcards;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {

    private long backPressedTime;
    private  Toast backToast;
    Button nextbtn,backbtn,skipbtn,speakBtn,finishBtn;
    MediaPlayer mp,mp1;
    private TextView actTitle;



    //UI VIEWS

    private ViewPager viewPager;
    private ArrayList<MyModel> modelArrayList;
    private MyAdapter myAdapter;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    int [] audio;
    //MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabets);

        audio = new int[] {
                R.raw.black,R.raw.white,R.raw.red,R.raw.green,
                R.raw.yellow,R.raw.blue,R.raw.pink,R.raw.grey,
                R.raw.brown,R.raw.orange,R.raw.purple
        };





        //Buttons
        backbtn = findViewById(R.id.backbtn);
        nextbtn = findViewById(R.id.nextbtn);
        skipbtn = findViewById(R.id.skipbtn);
        speakBtn = findViewById(R.id.speakerbtn);
        finishBtn = findViewById(R.id.finishbtn);
        //title app
        actTitle = findViewById(R.id.activity_title);

        //set text to act title
        actTitle.setText(getString(R.string.colors));

        //set invisible for finish button
        finishBtn.setVisibility(View.INVISIBLE);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Colors.this, R.raw.btn_klik);
                mp1.start();

                if (getitem(0) > 0){
                    mp.stop();

                    viewPager.setCurrentItem(getitem(-1),true);


                }

            }
        });


        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(Colors.this, audio[getitem(0)]);
                mp.start();
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Colors.this, R.raw.btn_klik);
                mp1.start();

                if (getitem(0) < 25){
                    mp.stop();
                    viewPager.setCurrentItem(getitem(1),true);

                }




            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Colors.this, R.raw.btn_klik);
                mp1.start();


                Intent i = new Intent(Colors.this,HomeScreen.class);
                startActivity(i);
                finish();


            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Colors.this, R.raw.btn_klik);
                mp1.start();

                Intent intent = new Intent(Colors.this,HomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });


        //init ui views
        viewPager = findViewById(R.id.viewPager);
        loadCards();

        //colors
        Integer[] temp_color = {
                getResources().getColor(R.color.black),
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.color7),
                getResources().getColor(R.color.color9),
                getResources().getColor(R.color.color25),
                getResources().getColor(R.color.color5),
                getResources().getColor(R.color.color23),
                getResources().getColor(R.color.gray),
                getResources().getColor(R.color.color12),
                getResources().getColor(R.color.color24),
                getResources().getColor(R.color.purple_500)

        };

        //audio
        mp = MediaPlayer.create(Colors.this, audio[0]);
        mp.start();

        colors = temp_color;
        backbtn.setVisibility(View.INVISIBLE);

        //set Viewpager change listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if( position < (myAdapter.getCount()-1) && position<(colors.length-1)){



                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1])
                    );




                }else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }

            }

            @Override
            public void onPageSelected(int position) {

                //audio
                mp = MediaPlayer.create(Colors.this, audio[position]);
                mp.start();



                if (position > 0){

                    backbtn.setVisibility(View.VISIBLE);

                }else {

                    backbtn.setVisibility(View.INVISIBLE);

                }

                if (position < 10){

                    nextbtn.setVisibility(View.VISIBLE);
                    finishBtn.setVisibility(View.INVISIBLE);

                }else {

                    nextbtn.setVisibility(View.INVISIBLE);
                    finishBtn.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void loadCards() {
        //init list
        modelArrayList = new ArrayList<>();
        //add items to arraylist
        modelArrayList.add(new MyModel("Black", R.drawable.ic_black));
        modelArrayList.add(new MyModel("White", R.drawable.ic_white));
        modelArrayList.add(new MyModel("Red", R.drawable.ic_red));
        modelArrayList.add(new MyModel("Green", R.drawable.ic_green));
        modelArrayList.add(new MyModel("yellow", R.drawable.ic_yellow));
        modelArrayList.add(new MyModel("Blue", R.drawable.ic_blue));
        modelArrayList.add(new MyModel("Pink", R.drawable.ic_pink));
        modelArrayList.add(new MyModel("Gray", R.drawable.ic_gray));
        modelArrayList.add(new MyModel("Brown", R.drawable.ic_brown));
        modelArrayList.add(new MyModel("Orange", R.drawable.ic_orange));
        modelArrayList.add(new MyModel("Purple", R.drawable.ic_purple));

        //setup adapter
        myAdapter = new MyAdapter(this,modelArrayList);
        //set adapter to viewpager
        viewPager.setAdapter(myAdapter);
        //set dafault padding from left/right
        viewPager.setPadding(100,0,100,0);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Colors.this,HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private int getitem(int i){

        return viewPager.getCurrentItem() + i;
    }
}