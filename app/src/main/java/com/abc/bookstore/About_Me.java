package com.abc.bookstore;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class About_Me extends AppCompatActivity {
    TextView et_name, et_email, et_mob, et_collage, et_address;
    Button btn_edit;
    Context context;
    private static ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__me);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_mob = findViewById(R.id.et_mob);
        et_collage = findViewById(R.id.et_collage);
        et_address = findViewById(R.id.et_address);
        btn_edit = findViewById(R.id.btn_edit);
        context = About_Me.this;
        progressDialog = new ProgressDialog(this);

        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(About_Me.this , Edit_Information.class );
                startActivity(i);

            }
        });
        progressDialog.show();
        CallApiInfo();
    }

    private void CallApiInfo() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.wanted.today/wanted_demo/view.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(About_Me.this, ""+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray= jsonObject.getJSONArray("data");
                    for (int i =0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String  nameee = jsonObject1.getString("name");
                        Toast.makeText(context, ""+nameee, Toast.LENGTH_SHORT).show();

                        et_name.setText(jsonObject1.getString("name"));
                        et_email.setText(jsonObject1.getString("email"));

                        et_mob.setText(jsonObject1.getString("mobile"));
                        et_collage.setText(jsonObject1.getString("college"));
                        et_address.setText(jsonObject1.getString("address"));



                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(About_Me.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
              Map<String , String> params = new HashMap<>();
              params.put("user_id" , LocalSharedPreferences.getUserid(context));

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
//    @Override
//    public void onBackPressed() {
//        Intent i = new Intent(About_Me.this , MainActivity.class);
//        startActivity(i);
//
//    }


}
