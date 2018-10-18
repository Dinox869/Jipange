package com.example.dennis.jipange;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dennis on 1/27/2018.
 */

public class clothes extends Activity {
    databasehelper mdatabasehelper = new databasehelper(this);
    EditText Name,Price;
    Button Save,back;
    public static final String URL_SAVE_NAME = "https://jipange.000webhostapp.com/androids/trying.php";
    public static final int NAME_SYNCED_WITH_SERVER = 1;
    public static final int NAME_NOT_SYNCED_WITH_SERVER = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clothes);
        Name = (EditText )findViewById(R.id.cloth);
        Price = (EditText)findViewById(R.id.cloth2);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        Price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        Save = (Button)findViewById(R.id.cloth3);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Name.length()!=0 && Price.length() > 0 )
                {
                  String NAME = Name.getText().toString();
                    String PRICE = Price.getText().toString();
                    set_get c = new set_get();
                    c.setname(NAME);
                    c.setprice(PRICE);
                    mdatabasehelper.insertsavingscloth(c);
                    String History_food ;
                    String Field = "Clothes";
                    History_food = "Clothes : "+NAME+"."+".";
                    c.sethistory(History_food);
                    c.setfield(Field);
                    //checking online table
                    Cursor dataz = mdatabasehelper.getOnline();
                    if(dataz.getCount() <=  0 )
                    {
                        saveName(PRICE ,Field , History_food,NAME_NOT_SYNCED_WITH_SERVER);
                    }
                    else
                    {
                        Cursor datz = mdatabasehelper.getprofile();
                        while(datz.moveToNext()) {

                            String emailz = datz.getString(2);
                            populate(PRICE, Field, History_food, emailz);

                        }
                    }
                    Name.setText("");
                    Price.setText("");
                    Toast.makeText(clothes.this, "Data Stored", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(clothes.this, "Kindly fill all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
}
    public void populate(final String Price , String FIELD , String History, final String EMAIL)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        final String History_food = History;// NAME
        final String PRICE = Price;//PRICE
        final String Field = FIELD;//FIELD
        set_get c = new set_get();
        c.sethistory(History_food);//name
        c.setfield(Field);//field
        c.setprice(PRICE);//price

        progressDialog.setMessage("Saving online ...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //if there is a success
                                //storing the name to sqlite with status synced
                                saveName(PRICE ,Field , History_food, NAME_SYNCED_WITH_SERVER);

                            } else {
                                //if there is some error
                                //saving the name to sqlite with status unsynced
                                saveName(PRICE ,Field , History_food,NAME_NOT_SYNCED_WITH_SERVER);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //on error storing the name to sqlite with status unsynced
                        saveName(PRICE ,Field , History_food,NAME_NOT_SYNCED_WITH_SERVER);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", History_food);
                params.put("price", PRICE);
                params.put("field", Field);
                params.put("email", EMAIL);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void saveName( String PRICE , String Field ,String  History_food, Integer Status)
    {

        mdatabasehelper.insertsaving_HISTORYs(PRICE,Field,History_food, Status);
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
