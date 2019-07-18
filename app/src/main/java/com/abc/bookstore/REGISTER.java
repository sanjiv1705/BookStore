package com.abc.bookstore;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class REGISTER extends AppCompatActivity {
        Button register ;
        EditText name , mob , id , pass ,em , addres , col;
        private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.register);
        name = findViewById(R.id.name);
        em = findViewById(R.id.email);
        mob = findViewById(R.id.mob);
        id = findViewById(R.id.id);
        col = findViewById(R.id.collage);
        addres = findViewById(R.id.address);
        pass = findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("This Process may take some time");
        progressDialog.setProgress(0);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().length() == 0) {
                    name.setError("Field must be fill");
                } else if (mob.getText().toString().length() == 0) {
                    mob.setError("Field must be fill");
                } else if (id.getText().toString().length() == 0) {
                    id.setError("Field must be fill");
                } else if (pass.getText().toString().length() == 0) {
                    pass.setError("Set Your Password");
                } else {


                CallRegisterAPI();
                progressDialog.show();
            }}
        });
    }



    private void CallRegisterAPI() {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.wanted.today/wanted_demo/register_user.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(REGISTER.this, "" + response, Toast.LENGTH_SHORT).show();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getString("success").equals("1"))
                        { Toast.makeText(REGISTER.this, "your account registered", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                            finish();
                        startActivity(getIntent());
                        Intent i = new Intent(REGISTER.this ,LOGIN.class);
                        startActivity(i);
                            Toast.makeText(REGISTER.this, "Login with the registered Account", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(REGISTER.this, "Try Again", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyerror) {
                    Toast.makeText(REGISTER.this, "Try Again" + volleyerror, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", name.getText().toString());
                    params.put("email", em.getText().toString());
                    params.put("password", pass.getText().toString());
                    params.put("mobile", mob.getText().toString());
                    params.put("address", addres.getText().toString());
                    params.put("college", col.getText().toString());
                    Log.d("hello", params.toString());
                    return params;
                }
            };
            requestQueue.add(stringRequest);


        }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder a = new AlertDialog.Builder(REGISTER.this);
        a.setTitle("Alert !");
        a.setMessage("The details will not be saved");
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
       AlertDialog  b = a.create();
         b.show();
    }
}
