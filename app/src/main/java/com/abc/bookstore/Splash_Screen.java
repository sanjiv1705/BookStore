package com.abc.bookstore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class Splash_Screen extends AppCompatActivity {
    ImageView imageView;
    Animation animation_1 , animation_2 , animation_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        imageView = findViewById(R.id.imageView);
        animation_1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.zoom_in);
        animation_2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.zoom_out);
        animation_3 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);
        checkAndGo();
        imageView.startAnimation(animation_2);

        animation_2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation_1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation_3);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
    public void checkAndGo() {
        if (isOnline()) {
            int SPLASH_TIME_OUT = 4000;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (LocalSharedPreferences.getIsLogin(Splash_Screen.this)) {
                        Intent i = new Intent(Splash_Screen.this, MainActivity.class);
                        startActivity(i);

                    } else {
                        Intent i = new Intent(Splash_Screen.this, LOGIN.class);
                        startActivity(i);
                    }
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
        else {

            Toast.makeText(this, "Oh no!  No Internet found. Check your connection and try again", Toast.LENGTH_LONG).show();
            AlertDialog alertDialog = new AlertDialog.Builder(Splash_Screen.this).create();
            alertDialog.setTitle("Info");
            alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
            //  alertDialog.setIcon(R.drawable.profile);
            alertDialog.setCanceledOnTouchOutside(false);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "TRY AGAIN", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Checking your Internet Connection", Toast.LENGTH_LONG).show();
                    //   mProgressDialog = Utills.showProgressDialog(SplashScreen.this);
                    checkAndGo();
                }
            });

            alertDialog.show();
        }
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}


