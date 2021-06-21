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

public class Animals extends AppCompatActivity {

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
                R.raw.camel,R.raw.cat,R.raw.chicken,R.raw.cow,
                R.raw.deer,R.raw.dog,R.raw.elephant,R.raw.giraffe,
                R.raw.kangaroo,R.raw.lion,R.raw.monkey,R.raw.panda,
                R.raw.pig,R.raw.mouse,R.raw.tiger,R.raw.zebra
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
        actTitle.setText(getString(R.string.animals));

        //set invisible for finish button
        finishBtn.setVisibility(View.INVISIBLE);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Animals.this, R.raw.btn_klik);
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
                mp = MediaPlayer.create(Animals.this, audio[getitem(0)]);
                mp.start();
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Animals.this, R.raw.btn_klik);
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
                mp1 = MediaPlayer.create(Animals.this, R.raw.btn_klik);
                mp1.start();


                Intent i = new Intent(Animals.this,HomeScreen.class);
                startActivity(i);
                finish();


            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(Animals.this, R.raw.btn_klik);
                mp1.start();

                Intent intent = new Intent(Animals.this,HomeScreen.class);
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
                getResources().getColor(R.color.color25),
                getResources().getColor(R.color.color24),
                getResources().getColor(R.color.color24),
                getResources().getColor(R.color.color12),
                getResources().getColor(R.color.color12),
                getResources().getColor(R.color.color12),
                getResources().getColor(R.color.gray),
                getResources().getColor(R.color.color24),
                getResources().getColor(R.color.color17),
                getResources().getColor(R.color.color17),
                getResources().getColor(R.color.color17),
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.color23),
                getResources().getColor(R.color.gray),
                getResources().getColor(R.color.color17),
                getResources().getColor(R.color.gray)

        };

        //audio
        mp = MediaPlayer.create(Animals.this, audio[0]);
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
                mp = MediaPlayer.create(Animals.this, audio[position]);
                mp.start();


                if (position > 0){

                    backbtn.setVisibility(View.VISIBLE);

                }else {

                    backbtn.setVisibility(View.INVISIBLE);

                }

                if (position < 15){

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
        modelArrayList.add(new MyModel("Camel", R.drawable.ic_camel));
        modelArrayList.add(new MyModel("Cat", R.drawable.ic_cat));
        modelArrayList.add(new MyModel("Chicken", R.drawable.ic_chicken));
        modelArrayList.add(new MyModel("Cow", R.drawable.ic_cow));
        modelArrayList.add(new MyModel("Deer", R.drawable.ic_deer));
        modelArrayList.add(new MyModel("Dog", R.drawable.ic_dog));
        modelArrayList.add(new MyModel("Elephant", R.drawable.ic_elephant));
        modelArrayList.add(new MyModel("Giraffe", R.drawable.ic_giraffe));
        modelArrayList.add(new MyModel("Kangaroo", R.drawable.ic_kangaroo));
        modelArrayList.add(new MyModel("Lion", R.drawable.ic_lion));
        modelArrayList.add(new MyModel("Monkey", R.drawable.ic_monkey));
        modelArrayList.add(new MyModel("Panda", R.drawable.ic_panda));
        modelArrayList.add(new MyModel("Pig", R.drawable.ic_pig));
        modelArrayList.add(new MyModel("Mouse", R.drawable.ic_mouse));
        modelArrayList.add(new MyModel("Tiger", R.drawable.ic_tiger));
        modelArrayList.add(new MyModel("Zebra", R.drawable.ic_zebra));

        //setup adapter
        myAdapter = new MyAdapter(this,modelArrayList);
        //set adapter to viewpager
        viewPager.setAdapter(myAdapter);
        //set dafault padding from left/right
        viewPager.setPadding(100,0,100,0);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Animals.this,HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private int getitem(int i){

        return viewPager.getCurrentItem() + i;
    }
}