package com.abc.bookstore;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Edit_Information extends AppCompatActivity {
    EditText et_name1 , et_email1 , et_mob1 ,et_collage1 ,et_address1 , et_password1;
    private ProgressDialog progressDialog;
    Button edit;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__information);
        et_name1 = findViewById(R.id.et_name1);
        et_email1 = findViewById(R.id.et_email1);
        et_password1 = findViewById(R.id.et_password1);
        et_mob1 = findViewById(R.id.et_mob1);
        context = Edit_Information.this;
        edit = findViewById(R.id.btn_edit1);
        et_collage1 = findViewById(R.id.et_collage1);
        et_address1 = findViewById(R.id.et_address1);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
edit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        EditApi();
        progressDialog.show();


    }


});
CallApi();
progressDialog.show();

    }

    private void CallApi() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.wanted.today/wanted_demo/view.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText( Edit_Information.this, ""+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray= jsonObject.getJSONArray("data");
                    for (int i =0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String  nameee = jsonObject1.getString("name");
                        Toast.makeText(context, ""+nameee, Toast.LENGTH_SHORT).show();

                        et_name1.setText(jsonObject1.getString("name"));
                        et_email1.setText(jsonObject1.getString("email"));
                        et_mob1.setText(jsonObject1.getString("mobile"));
                        et_collage1.setText(jsonObject1.getString("college"));
                        et_address1.setText(jsonObject1.getString("address"));
                        et_password1.setText(jsonObject1.getString("password"));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Edit_Information.this, ""+error, Toast.LENGTH_SHORT).show();

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




    private void EditApi() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.wanted.today/wanted_demo/update_user.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                { JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.getString("success").equals("1"))
                    {
                        Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
//                        Intent i = new Intent(Edit_Information.this , About_Me.class);
//                        startActivity(i);
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show();


            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("name" , et_name1.getText().toString());
                params.put("user_id" , LocalSharedPreferences.getUserid(context));
                params.put("email" , LocalSharedPreferences.getemail(context));
                params.put("password",et_password1.getText().toString());
                params.put("mobile",et_mob1.getText().toString());
                params.put("college",et_collage1.getText().toString());
                params.put("address",et_address1.getText().toString());



                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
//
//    @Override
//    public void onBackPressed() {
//        Intent i = new Intent(Edit_Information.this , About_Me.class);
//        startActivity(i);
//
//    }
}
