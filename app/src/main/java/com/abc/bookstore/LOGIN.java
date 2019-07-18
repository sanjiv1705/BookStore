package com.abc.bookstore;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LOGIN extends AppCompatActivity {
    Button b ;
    TextView t1 , t2;
    EditText log , pass;
    private static ProgressDialog progressDialog;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b = findViewById(R.id.login);
        t1 = findViewById(R.id.newly);
        t2 = findViewById(R.id.forgot);
        log = findViewById(R.id.log);
        pass = findViewById(R.id.Pass);
        context = LOGIN.this;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        if(LocalSharedPreferences.getIsLogin(context)==false) {

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if ((log.getText().toString().length() == 0))
                        log.setError("Field must be fill");
                    else if ((pass.getText().toString().length() == 0))
                        pass.setError("Enter your Password");

                    else {
                        Callapi();
                        progressDialog.show();

                    }
                }
            });
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(LOGIN.this, "Loading the Page", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LOGIN.this, REGISTER.class);
                    startActivity(i);
                }
            });
            t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(LOGIN.this, "Enter your Registered number", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LOGIN.this, FORGOT_PASS.class);
                    startActivity(i);
                }
            });

        }
        else
        {
            Intent i= new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void Callapi() {
       RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest s = new StringRequest(Request.Method.POST, "https://wanted.today/wanted_demo/login.php", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                Toast.makeText(LOGIN.this, "" +response, Toast.LENGTH_SHORT).show();
                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("success").equals("1")){
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        progressDialog.dismiss();
                        LocalSharedPreferences.saveIsLogin(LOGIN.this ,true);

                        String user_type = jsonObject1.getString("name");
                        LocalSharedPreferences.savename(context, user_type);
                        Toast.makeText(LOGIN.this, "" + LocalSharedPreferences.getname(context), Toast.LENGTH_SHORT).show();

                        String email = jsonObject1.getString("email");
                        LocalSharedPreferences.saveemail(context , email);
                        Toast.makeText( LOGIN.this, ""+LocalSharedPreferences.getemail(context), Toast.LENGTH_SHORT).show();

                        String user_id = jsonObject1.getString("id");
                        LocalSharedPreferences.saveUserid(context , user_id);
                        Toast.makeText(LOGIN.this, ""+LocalSharedPreferences.getUserid(context), Toast.LENGTH_SHORT).show();

                                     Intent intent = new Intent(LOGIN.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LOGIN.this, "Please Check user name & password", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LOGIN.this, ""+error, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){

        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<>();
            params.put("email",log.getText().toString());
            params.put("password",pass.getText().toString());
            return params;
        }
    };
       requestQueue.add(s);

}}

