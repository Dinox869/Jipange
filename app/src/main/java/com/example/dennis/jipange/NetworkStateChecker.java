package com.example.dennis.jipange;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
 * Created by dennis on 3/24/2018.
 */

public class NetworkStateChecker extends BroadcastReceiver {
    String emailz;
    private Context context;
    private databasehelper mdatabasehelper ;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        mdatabasehelper = new databasehelper(context);

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        //if there is a network
        if (activeNetwork != null) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                //checking whether online has been approved
                Cursor data =  mdatabasehelper.getOnline();
                if(data.getCount()<=0 )
                {
                    //Nothing is done!!!!
                }
                else
                    {
                        Cursor datz = mdatabasehelper.getprofile();


                    //getting all the unsynced names
                    Cursor cursor = mdatabasehelper.getUnsyncedNames();
                    if (cursor.moveToFirst()) {
                        while(datz.moveToNext())
                            emailz = datz.getString(2);

                        do {
                            //calling the method to save the unsynced name to MySQL
                            saveName(
                                    cursor.getInt(0),
                                    cursor.getString(1),
                                    cursor.getString(4),
                                    cursor.getString(2),emailz

                            );
                        } while (cursor.moveToNext());
                    }
                }
            }
        }
    }
    /*
   * method taking two arguments
   * name that is to be saved and id of the name from SQLite
   * if the name is successfully sent
   * we will update the status as synced in SQLite
   * */
    private void saveName(final int id, final String name , final String price, final String field , final String email) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, history.URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //updating the status in sqlite
                                mdatabasehelper.updateNameStatus(id, history.NAME_SYNCED_WITH_SERVER);

                                //sending the broadcast to refresh the list
                                context.sendBroadcast(new Intent(history.DATA_SAVED_BROADCAST));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("price", price);
                params.put("field", field);
                params.put("email", email);
                return params;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
