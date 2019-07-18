package com.abc.bookstore;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView user_name , user_email;
    Context context;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;
    ImageButton imageButton3 , imageButton1,imageButton2,imageButton4,imageButton5,imageButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        context = MainActivity.this;
        imageButton3 = findViewById(R.id.imagebutton3);
        imageButton1 = findViewById(R.id.imagebutton1);
        imageButton2 = findViewById(R.id.imagebutton2);
        imageButton4 = findViewById(R.id.imagebutton4);
        imageButton5 = findViewById(R.id.imagebutton5);
        imageButton6 = findViewById(R.id.imagebutton6);

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , Upload_Book.class);
                startActivity(i);
                Toast.makeText(context, "Upload Your Book ", Toast.LENGTH_SHORT).show();
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , About_Me.class);
                startActivity(i);
                Toast.makeText(context, "View Your Profile", Toast.LENGTH_SHORT).show();
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , Edit_Information.class);
                startActivity(i);
                Toast.makeText(context, "Update Your Profile", Toast.LENGTH_SHORT).show();
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , View_All.class);
                startActivity(i);
                Toast.makeText(context, "Search Book", Toast.LENGTH_SHORT).show();
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , view_your_books.class);
                startActivity(i);
                Toast.makeText(context, "Your Uploaded Books", Toast.LENGTH_SHORT).show();
            }
        });
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , Contact_Us.class);
                startActivity(i);
                Toast.makeText(context, "Message Or Email Or Call ", Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar(toolbar);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
       DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        user_name = headerview.findViewById(R.id.user_name);
        user_name.setText(LocalSharedPreferences.getname(this));
        user_email = headerview.findViewById(R.id.user_email);

        user_email.setText(LocalSharedPreferences.getemail(this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Click two times to close an activity", Toast.LENGTH_SHORT).show(); }
        mBackPressed = System.currentTimeMillis();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_About_me) {
            Intent i = new Intent(MainActivity.this , About_Me.class);
            startActivity(i);

        } else if (id == R.id.nav_upload) {
            Intent i = new Intent(MainActivity.this , Upload_Book.class);
            startActivity(i);

        } else if (id == R.id.nav_view_books) {
            Intent i = new Intent(MainActivity.this , View_All.class);
            startActivity(i);

        } else if (id == R.id.nav_your_books) {
            Intent i = new Intent(MainActivity.this , view_your_books.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_delete) {
            Intent i = new Intent(MainActivity.this , view_your_books.class);
            startActivity(i);

        }
        else if(id == R.id.nav_logout){
            AlertDialog.Builder a = new AlertDialog.Builder(this);
            a.setTitle("Alert..");
            a.setMessage("Are you sure you want to Logout");
            a.setCancelable(false);
            a.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            a.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(MainActivity.this , LOGIN.class);
                    startActivity(i);
                    finish();
                    LocalSharedPreferences.saveIsLogin(context , false);

                }

            });
            AlertDialog c = a.create();
            c.show();




        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
