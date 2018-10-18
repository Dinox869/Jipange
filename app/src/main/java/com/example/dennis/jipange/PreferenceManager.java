package com.example.dennis.jipange;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dennis on 3/8/2018.
 */

public class PreferenceManager
{
    private Context context;
    private SharedPreferences sharedPreference;

    public PreferenceManager(Context context)
    {
        this.context = context;
        getSharedPreference();
    }
    private void getSharedPreference()
    {
        sharedPreference = context.getSharedPreferences(context.getString(R.string.my_preference),context.MODE_PRIVATE);
    }
    public void writePreference()
    {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(context.getString(R.string.my_preference_key),"INIT_OK");
        editor.commit();
    }
    public boolean checkPreference()
    {
        boolean status = false;
        if(sharedPreference.getString(context.getString(R.string.my_preference_key),"null").equals("null"))
        {
          status = false;
        }
        else
        {
           status = true;
        }
        return status;
    }
    public void clearPreference()
    {
        sharedPreference.edit().clear().commit();
    }
}
