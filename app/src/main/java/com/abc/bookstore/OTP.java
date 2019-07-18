package com.abc.bookstore;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OTP extends AppCompatActivity {
    Button otp;
    EditText OTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        otp = findViewById(R.id.otp);
        OTP = findViewById(R.id.OTP);
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(OTP.getText().toString().length()==0)
                {OTP.setError("Enter the 4 digit OTP");}
                else {

                    Toast.makeText(OTP.this, "Welcome to the Home page", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(OTP.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder a = new AlertDialog.Builder(OTP.this);
        a.setTitle("Enter OTP");
        a.setMessage("Process will be cancelled and no info saved");
        a.setCancelable(false);
        a.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        a.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog c = a.create();
        c.show();
    }
}
