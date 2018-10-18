package com.example.dennis.jipange;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dennis on 3/2/2018.
 */

public class datefield extends AppCompatActivity {
    Spinner field;
    TextView date;
    custom_populatehist adapter = null;
    ArrayList<histolist> popi;
    databasehelper mdatabasehelper =  new databasehelper(this);
    Button btn2,back;
    TextView fromm,message;
    ImageButton search;
    private static final String TAG = "Date getData() worked";
    int year_x,month_x,day_x;
    static final int DIALOG_IDS = 0 ;
    ListView listview;
    String dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_field);
        fromm = (TextView)findViewById(R.id.from);
        btn2 = (Button)findViewById(R.id.FROM);
        field = (Spinner)findViewById(R.id.field);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        message = (TextView)findViewById(R.id.message);
        search = (ImageButton)findViewById(R.id.search);
        listview = (ListView)findViewById(R.id.Date_listview);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(datefield.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.FIELD));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        field.setAdapter(myadapter);
        final Calendar cal = Calendar.getInstance();
         year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fields = field.getSelectedItem().toString();
                String days = fromm.getText().toString();
                mdatabasehelper.setsfield( fields);
                if(days.length()>0 && fields.length()>0 )
                {
                    mdatabasehelper.getdatefield();
                    populate();
                }else
                {
                    Toast.makeText(datefield.this, "Select a date and field.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void populate()
    {
        message.setText("");
        listview = (ListView)findViewById(R.id.Date_listview);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        listview.setAdapter(adapter);
        Cursor data = mdatabasehelper.getdatefield();
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
            message.setText("No records found .");
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
    {
        //to
        if (id == DIALOG_IDS){
            return new DatePickerDialog(this,dpickerListener, year_x,month_x,day_x);}
        return null;
    }
    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            year_x = year;
            day_x = dayOfMonth;
            month_x = month + 1;
            //if
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

        }

    };


}