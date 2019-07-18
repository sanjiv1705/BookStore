package com.abc.bookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FORGOT_PASS extends AppCompatActivity {
    Button forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__pass);
        forgot = findViewById(R.id.pass);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FORGOT_PASS.this ,New_Password.class );
                startActivity(i);
                Toast.makeText(FORGOT_PASS.this, "Enter the New Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
