package ucucite.cite.scrathflashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    private long backPressedTime;
    private  Toast backToast;
    MediaPlayer mp1,mp;
    Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mp = MediaPlayer.create(HomeScreen.this, R.raw.bg_music);
        mp.setLooping(true);
        mp.start();

        View alpha = findViewById(R.id.card_let_container);
        View numbers = findViewById(R.id.card_num_container);
        View colors = findViewById(R.id.card_col_container);
        View animals = findViewById(R.id.card_anim_container);
        exit = findViewById(R.id.exit);

        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp1 = MediaPlayer.create(HomeScreen.this, R.raw.select);
                mp1.start();                Intent intent = new Intent(HomeScreen.this,Alphabets.class);
                startActivity(intent);
                finish();

                mp.stop();
            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(HomeScreen.this, R.raw.select);
                mp1.start();

                Intent intent = new Intent(HomeScreen.this,Numbers.class);
                startActivity(intent);
                finish();
                mp.stop();

            }
        });

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(HomeScreen.this, R.raw.select);
                mp1.start();
                Intent intent = new Intent(HomeScreen.this,Colors.class);
                startActivity(intent);
                finish();
                mp.stop();
            }
        });

        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(HomeScreen.this, R.raw.select);
                mp1.start();
                Intent intent = new Intent(HomeScreen.this,Animals.class);
                startActivity(intent);
                finish();
                mp.stop();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1 = MediaPlayer.create(HomeScreen.this, R.raw.select);
                mp1.start();
                mp.stop();
                finish();
            }
        });







    }
    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            mp.stop();

            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press Back Again to Exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

   @Override
    protected void onUserLeaveHint()
    {
        mp.pause();
        super.onUserLeaveHint();

    }
    @Override
    protected void onResume() {
        if(mp != null && !mp.isPlaying())
            mp.start();

        super.onResume();
    }

}