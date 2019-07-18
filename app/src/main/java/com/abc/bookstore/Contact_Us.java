package com.abc.bookstore;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Contact_Us extends AppCompatActivity {
        TextView dev_id , dev_id2 , dev_contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);
        dev_id =findViewById(R.id.dev_id);
        dev_id2=findViewById(R.id.dev_id2);
        dev_contact = findViewById(R.id.dev_contact);

        dev_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + "9467894249"));
                startActivity(i);
            }
        });
        dev_id2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO );
                i.setData(Uri.parse("smsto:" + "8708018126"));
                startActivity(i);
            }
        });
        dev_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "" + "jajoosanjiv@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(emailIntent);
            }
        });
    }
}
