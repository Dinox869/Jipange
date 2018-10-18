package com.example.dennis.jipange;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dennis on 2/6/2018.
 */

public class date extends AppCompatActivity {
    databasehelper mdatabasehelper;
    Button btn2,back;
    custom_populatehist adapter = null;
    ArrayList<histolist> popi;
    TextView fromm,message;
    private static final String TAG = "Date getData() worked";
    int year_x,month_x,day_x;
    static final int DIALOG_IDS = 0 ;
    ListView listview;
    @TargetApi(Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);
        btn2 = (Button)findViewById(R.id.FROM);
        mdatabasehelper = new databasehelper(this);
        fromm = (TextView)findViewById(R.id.from);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        listview = (ListView)findViewById(R.id.Date_listview);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        message = (TextView)findViewById(R.id.message);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
    }

    private void populate()
    {

        listview = (ListView)findViewById(R.id.Date_listview);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        listview.setAdapter(adapter);
        Cursor data = mdatabasehelper.getdate();
        popi.clear();
        while(data.moveToNext())
        {
            int id = data.getInt(0);
            String name = data.getString(1);
            String price = data.getString(4);
            int status = data.getInt(5);
            popi.add(new histolist(name,price,id,status));

        }
        adapter.notifyDataSetChanged();
        if(popi.size() == 0)
        {
           message.setText("No records found.");
        }
    }

    public void showDialogOnButtonClick()
    {
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_IDS);
            }
        });
    }
    protected Dialog onCreateDialog(int id)
    {   //to
        if (id == DIALOG_IDS){
            return new DatePickerDialog(this,dpickerListener, year_x,month_x,day_x);}
        return null;
    }
    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;
            if(day_x <=9 && month_x <=9) {
                String dates = "" + year_x + "-0" + month_x + "-0" + day_x + "";
                fromm.setText(dates);
                mdatabasehelper.setdate(dates);
            }
            else if (day_x>9 && month_x >9)
            {
                String dates = "" + year_x + "-" + month_x + "-" + day_x + "";
                fromm.setText(dates);
                mdatabasehelper.setdate(dates);

            }
            else if(day_x>9 && month_x <=9)
            {
                String dates = "" + year_x + "-0" + month_x + "-" + day_x + "";
                fromm.setText(dates);
                mdatabasehelper.setdate(dates);
            }
            else if (day_x<=9 && month_x >9)
            {
                String dates = "" + year_x + "-" + month_x + "-0" + day_x + "";
                fromm.setText(dates);
                mdatabasehelper.setdate(dates);

            }
            message.setText("");
            populate();

        }

    };
}