package com.example.dennis.jipange;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView transportcard, foodcard, addcard, drinkscard, billcard, electronicscard, clothescard, shoppingcard;
    ArrayList<Integer> Imagelist;
    ArrayList<String> Menunames;
    private final static String TAG = "Mainsave";
    public static final String URL_SAVE_ONLINE = "https://jipange.000webhostapp.com/androids/get.php?email=";
    databasehelper mdatabasehelper = new databasehelper(this);
    Toolbar toolbar;
    Toolbar tool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setSubtitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setTitle("JIPANGE.");
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Keeping track of your money.");
        Imagelist = new ArrayList<>();
        Menunames = new ArrayList<>();
        setInitialData();
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        transportcard = (CardView) findViewById(R.id.transport);
        foodcard = (CardView) findViewById(R.id.food);
        addcard = (CardView) findViewById(R.id.add);
        drinkscard = (CardView) findViewById(R.id.drinks);
        billcard = (CardView) findViewById(R.id.bills);
        electronicscard = (CardView) findViewById(R.id.electonics);
        clothescard = (CardView) findViewById(R.id.clothes);
        shoppingcard = (CardView) findViewById(R.id.shopping);

        transportcard.setOnClickListener(this);
        foodcard.setOnClickListener(this);
        addcard.setOnClickListener(this);
        drinkscard.setOnClickListener(this);
        billcard.setOnClickListener(this);
        electronicscard.setOnClickListener(this);
        clothescard.setOnClickListener(this);
        shoppingcard.setOnClickListener(this);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_4);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_4);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(Imagelist.get(i))
                    .normalText(Menunames.get(i))
                    .buttonCornerRadius(Util.dp2px(20))
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            // ****Change to switch from if.
                            note();
                            if (index == 0)//graph
                            {
                                Intent intent = new Intent(MainActivity.this, graph.class);
                                MainActivity.this.startActivity(intent);
                            }
                            if (index == 1) //history
                            {
                                Intent intent = new Intent(MainActivity.this, history.class);
                                MainActivity.this.startActivity(intent);
                            }
                            if (index == 2)//profile
                            {
                                Intent intent = new Intent(MainActivity.this, profile.class);
                                MainActivity.this.startActivity(intent);

                            }
                            if (index == 3)//contact_us
                            {
                                Intent intent = new Intent(MainActivity.this, contact.class);
                                MainActivity.this.startActivity(intent);

                            }
                            if (index == 4)//log_out
                            {
                                ActivityCompat.finishAffinity(MainActivity.this);
                            }
                            if (index == 5)//retreive data(Back up)
                            {
                                //check if there's data on history to retrieve data...
                                Cursor data = mdatabasehelper.getdata();
                                if (data.getCount() <= 0) {
                                    //gets data from mysql via volley
                                    getting();
                                } else {
                                    pushing();
                                }

                            }
                        }
                    });
            bmb.addBuilder(builder);
        }
    }
    private void note() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 20);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 20);
        Intent intent = new Intent(getApplicationContext(), notification.class);
        PendingIntent pendingintent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmmanager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmmanager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingintent);


    }

    private void setInitialData() {
        //images for the boom
        Imagelist.add(R.drawable.ic_graphic_eq_black_24dp);
        Imagelist.add(R.drawable.ic_history_black_24dp);
        Imagelist.add(R.drawable.ic_person_black_24dp);
        Imagelist.add(R.drawable.ic_contacts_black_24dp);
        Imagelist.add(R.drawable.ic_arrow_back_black_24dp);
        Imagelist.add(R.drawable.ic_save_black_24dp);

        //Title for the boom
        Menunames.add("Graphs");
        Menunames.add("History");
        Menunames.add("Profile");
        Menunames.add("Feedback");
        Menunames.add("Log out");
        Menunames.add("Backup");
    }

    //actions taken
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.transport:
                i = new Intent(this, Transport.class);
                startActivity(i);
                break;
            case R.id.food:
                i = new Intent(this, food.class);
                startActivity(i);
                break;
            case R.id.bills:
                i = new Intent(this, bills.class);
                startActivity(i);
                break;
            case R.id.electonics:
                i = new Intent(this, electronics.class);
                startActivity(i);
                break;
            case R.id.shopping:
                i = new Intent(this, shopping.class);
                startActivity(i);
                break;
            case R.id.drinks:
                i = new Intent(this, drinks.class);
                startActivity(i);
                break;
            case R.id.add:
                i = new Intent(this, add.class);
                startActivity(i);
                break;
            case R.id.clothes:
                i = new Intent(this, clothes.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    public void pushing() {
        new AlertDialog.Builder(this)
                .setTitle("Permission")
                .setMessage("Allow the system to backup your data online.")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Check for profile details.
                        Cursor data = mdatabasehelper.getprofile();
                        if (data.getCount() <= 0) {
                            Toast.makeText(MainActivity.this, "Fill the Profile details.", Toast.LENGTH_LONG).show();
                        } else {
                            String ONLINE = "1";
                            set_get c = new set_get();
                            c.setname(ONLINE);
                            mdatabasehelper.insertonline(c);
                            done();
                        }
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nothing here!!!
                    }
                })
                .show();
    }

    private void done() {
        new AlertDialog.Builder(this)
                .setTitle("Activated")
                .setMessage("The system will now automatically update your records online.If you unfortunately delete the app, you can retrieve your records by simply filling your profile details.")
                .setPositiveButton("OK, got it.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nothing is done...
                    }
                })
                .show();


    }

    public void getting() {
        new AlertDialog.Builder(this)
                .setTitle("Permission")
                .setMessage("Allow the system to retrieve your data(records) online.This only applies to those who saved their records online.")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Check for profile details.
                        Cursor data = mdatabasehelper.getprofile();

                        if (data.getCount() <= 0) {
                            Toast.makeText(MainActivity.this, "Fill the Profile details.", Toast.LENGTH_LONG).show();
                        } else {
                            String ONLINE = "1";
                            set_get c = new set_get();
                            c.setname(ONLINE);
                            mdatabasehelper.insertonline(c);
                            Cursor datas = mdatabasehelper.getprofile();
                            String EMAIL;
                            while (datas.moveToNext()) {
                                String email = datas.getString(2);
                                EMAIL = email;
                                getdatas(EMAIL);
                                //  getdat();
                            }

                            done();
                        }
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nothing happens
                    }
                })
                .show();
    }
    //volley to retrieve data from server db.
    public void getdatas(final String EMAIL) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching data,please wait ...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_SAVE_ONLINE+EMAIL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                String Name = product.getString("Name");
                                String Price = product.getString("Price");
                                String Field = product.getString("Field");
                                Integer Status = product.getInt("Status");
                                mdatabasehelper.insertsaving_HISTORYZ(Name, Price, Field, Status);
                                // do sth
                            }} catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Records have been restored successfully.", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error in fetching data");
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "This is not working.", Toast.LENGTH_SHORT).show();
            }
        });
           //creating a request queue
                RequestQueue requestQueue = Volley.newRequestQueue(this);

                //adding the string request to request queue
                requestQueue.add(stringRequest);


      //  VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


}
