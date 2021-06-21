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

public class Alphabets extends AppCompatActivity {


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
                R.raw.a,R.raw.b,R.raw.c,R.raw.d,
                R.raw.e,R.raw.f,R.raw.g,R.raw.h,
                R.raw.i,R.raw.j,R.raw.k,R.raw.l,
                R.raw.m,R.raw.n,R.raw.o,R.raw.p,
                R.raw.q,R.raw.r,R.raw.s,R.raw.t,
                R.raw.u,R.raw.v,R.raw.w,R.raw.x,
                R.raw.y, R.raw.z
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
        actTitle.setText(getString(R.string.alphabets));

        //set invisible for finish button
        finishBtn.setVisibility(View.INVISIBLE);

         backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp1 = MediaPlayer.create(Alphabets.this, R.raw.btn_klik);
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
                 mp = MediaPlayer.create(Alphabets.this, audio[getitem(0)]);
                 mp.start();
             }
         });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp1 = MediaPlayer.create(Alphabets.this, R.raw.btn_klik);
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

                mp1 = MediaPlayer.create(Alphabets.this, R.raw.btn_klik);
                mp1.start();


                Intent i = new Intent(Alphabets.this,HomeScreen.class);
                startActivity(i);
                finish();


            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Alphabets.this, R.raw.btn_klik);
                mp1.start();

                Intent intent = new Intent(Alphabets.this,HomeScreen.class);
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
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5),
                getResources().getColor(R.color.color6),
                getResources().getColor(R.color.color7),
                getResources().getColor(R.color.color8),
                getResources().getColor(R.color.color9),
                getResources().getColor(R.color.color10),
                getResources().getColor(R.color.color11),
                getResources().getColor(R.color.color12),
                getResources().getColor(R.color.color13),
                getResources().getColor(R.color.color14),
                getResources().getColor(R.color.color15),
                getResources().getColor(R.color.color16),
                getResources().getColor(R.color.color17),
                getResources().getColor(R.color.color18),
                getResources().getColor(R.color.color19),
                getResources().getColor(R.color.color20),
                getResources().getColor(R.color.color21),
                getResources().getColor(R.color.color22),
                getResources().getColor(R.color.color23),
                getResources().getColor(R.color.color24),
                getResources().getColor(R.color.color25),
                getResources().getColor(R.color.color26)

        };
        //activity title


        //audio
        mp = MediaPlayer.create(Alphabets.this, audio[0]);
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
                mp = MediaPlayer.create(Alphabets.this, audio[position]);
                mp.start();



                if (position > 0){

                    backbtn.setVisibility(View.VISIBLE);

                }else {

                    backbtn.setVisibility(View.INVISIBLE);

                }

                if (position < 25){

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
        modelArrayList.add(new MyModel("A-a", R.drawable.ic_a));
        modelArrayList.add(new MyModel("B-b", R.drawable.ic_b));
        modelArrayList.add(new MyModel("C-c", R.drawable.ic_c));
        modelArrayList.add(new MyModel("D-d", R.drawable.ic_d));
        modelArrayList.add(new MyModel("E-e", R.drawable.ic_e));
        modelArrayList.add(new MyModel("F-f", R.drawable.ic_f));
        modelArrayList.add(new MyModel("G-g", R.drawable.ic_g));
        modelArrayList.add(new MyModel("H-h", R.drawable.ic_h));
        modelArrayList.add(new MyModel("I-i", R.drawable.ic_i));
        modelArrayList.add(new MyModel("J-j", R.drawable.ic_j));
        modelArrayList.add(new MyModel("K-k", R.drawable.ic_k));
        modelArrayList.add(new MyModel("L-l", R.drawable.ic_l));
        modelArrayList.add(new MyModel("M-m", R.drawable.ic_m));
        modelArrayList.add(new MyModel("N-n", R.drawable.ic_n));
        modelArrayList.add(new MyModel("O-o", R.drawable.ic_o));
        modelArrayList.add(new MyModel("P-p", R.drawable.ic_p));
        modelArrayList.add(new MyModel("Q-q", R.drawable.ic_q));
        modelArrayList.add(new MyModel("R-r", R.drawable.ic_r));
        modelArrayList.add(new MyModel("S-s", R.drawable.ic_s));
        modelArrayList.add(new MyModel("T-t", R.drawable.ic_t));
        modelArrayList.add(new MyModel("U-u", R.drawable.ic_u));
        modelArrayList.add(new MyModel("V-v", R.drawable.ic_v));
        modelArrayList.add(new MyModel("W-w", R.drawable.ic_w));
        modelArrayList.add(new MyModel("X-x", R.drawable.ic_x));
        modelArrayList.add(new MyModel("Y-y", R.drawable.ic_y));
        modelArrayList.add(new MyModel("Z-z", R.drawable.ic_z));

        //setup adapter
        myAdapter = new MyAdapter(this,modelArrayList);
        //set adapter to viewpager
        viewPager.setAdapter(myAdapter);
        //set dafault padding from left/right
        viewPager.setPadding(100,0,100,0);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Alphabets.this,HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private int getitem(int i){

        return viewPager.getCurrentItem() + i;
    }

}