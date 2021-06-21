package ucucite.cite.scrathflashcards;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {

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
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabets);

        audio = new int[] {
                R.raw.n0,R.raw.n1,R.raw.n2,R.raw.n3,
                R.raw.n4,R.raw.n5,R.raw.n6,R.raw.n7,
                R.raw.n8,R.raw.n9,R.raw.n10
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
        actTitle.setText(getString(R.string.numbers));

        //set invisible for finish button
        finishBtn.setVisibility(View.INVISIBLE);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Numbers.this, R.raw.btn_klik);
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
                mp = MediaPlayer.create(Numbers.this, audio[getitem(0)]);
                mp.start();
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mp1 = MediaPlayer.create(Numbers.this, R.raw.btn_klik);
                mp1.start();

                if (getitem(0) < 25) {
                    mp.stop();
                    viewPager.setCurrentItem(getitem(1), true);


                }



            }
        });

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Numbers.this, R.raw.btn_klik);
                mp1.start();


                Intent i = new Intent(Numbers.this,HomeScreen.class);
                startActivity(i);
                finish();


            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Numbers.this, R.raw.btn_klik);
                mp1.start();

                Intent intent = new Intent(Numbers.this,HomeScreen.class);
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
                getResources().getColor(R.color.color23),
                getResources().getColor(R.color.color6),
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color16),
                getResources().getColor(R.color.color6),
                getResources().getColor(R.color.color16),
                getResources().getColor(R.color.color9),
                getResources().getColor(R.color.color23),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color8),
                getResources().getColor(R.color.color23)

        };

        //audio
        mp = MediaPlayer.create(Numbers.this, audio[0]);
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
                mp = MediaPlayer.create(Numbers.this, audio[position]);
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
        modelArrayList.add(new MyModel("Zero", R.drawable.ic_zero));
        modelArrayList.add(new MyModel("One", R.drawable.ic_one));
        modelArrayList.add(new MyModel("Two", R.drawable.ic_two));
        modelArrayList.add(new MyModel("Three", R.drawable.ic_three));
        modelArrayList.add(new MyModel("Four", R.drawable.ic_four));
        modelArrayList.add(new MyModel("Five", R.drawable.ic_five));
        modelArrayList.add(new MyModel("Six", R.drawable.ic_six));
        modelArrayList.add(new MyModel("Seven", R.drawable.ic_seven));
        modelArrayList.add(new MyModel("Eight", R.drawable.ic_eight));
        modelArrayList.add(new MyModel("Nine", R.drawable.ic_nine));
        modelArrayList.add(new MyModel("Ten", R.drawable.ic_ten));

        //setup adapter
        myAdapter = new MyAdapter(this,modelArrayList);
        //set adapter to viewpager
        viewPager.setAdapter(myAdapter);
        //set dafault padding from left/right
        viewPager.setPadding(100,0,100,0);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Numbers.this,HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private int getitem(int i){

        return viewPager.getCurrentItem() + i;
    }
}