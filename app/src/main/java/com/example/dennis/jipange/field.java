package com.example.dennis.jipange;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dennis on 2/6/2018.
 */

public class field extends AppCompatActivity {
    private static  final  String TAG = "Field_History";
    ListView listview;
    Spinner spin;
    TextView message;
    Button back;
    custom_populatehist adapter = null;
    ArrayList<histolist> popi;
    databasehelper mdatabasehelper = new databasehelper(this);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field);
        listview =  (ListView)findViewById(R.id.fields);
        spin = (Spinner)findViewById(R.id.FIELD);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        message = (TextView)findViewById(R.id.message);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(field.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.FIELD));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myadapter);
        String CATEGORY = spin.getSelectedItem().toString();
        set_get c =  new set_get();
        c.setfields(CATEGORY);
        Log.d(TAG,"my__account:Displaying data");
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                int item = spin.getSelectedItemPosition();
                listview.setAdapter(null);
                message.setText("");
               if (item == 0)
               {

                 populate();
               }else if (item == 1)
               {
                populates();
               }else if(item == 2)
               {
                   populateDrinks();
               }
               else if( item == 3)
               {
                   populateSHOPPING();
               }
               else if (item == 5)
               {
                   populateCLOTHES();
               }
               else if (item == 4)
               {
                   populateBILL();
               }
               else if (item == 6)
               {
                   populateElec();
               }else if (item == 7)
               {
                   populateother();
               }


            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });


    }
    //food
    public void populates()
    {
        listview =  (ListView)findViewById(R.id.fields);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        listview.setAdapter(adapter);
        Cursor data = mdatabasehelper.getfieldes();
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

    //Transport
    public void populate()
    {

        listview =  (ListView)findViewById(R.id.fields);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        listview.setAdapter(adapter);
        Cursor data = mdatabasehelper.getfield();
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
            message.setText("No records found on this date.");
        }

    }
    //Drink
    public void populateDrinks()
    {
        listview =  (ListView)findViewById(R.id.fields);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        listview.setAdapter(adapter);
        Cursor data = mdatabasehelper.getfieldss();
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
            message.setText("No records found on this date.");
        }

    }
    //Shopping
    public void populateSHOPPING()
    {
        listview =  (ListView)findViewById(R.id.fields);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        listview.setAdapter(adapter);
        Cursor data = mdatabasehelper.getfieldz();
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
            message.setText("No records found on this date.");
        }

    }
    //clothes
    public void populateCLOTHES()
    {
        listview =  (ListView)findViewById(R.id.fields);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        listview.setAdapter(adapter);
        Cursor data = mdatabasehelper.getCLOTHES();
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
            message.setText("No records found on this date.");
        }

    }
    //Bills
    public void populateBILL()
    {
        listview =  (ListView)findViewById(R.id.fields);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        listview.setAdapter(adapter);
        Cursor data = mdatabasehelper.getBILLS();
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
            message.setText("No records found on this date.");
        }


    }
    //other
    public void populateother()
    {

        listview =  (ListView)findViewById(R.id.fields);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        listview.setAdapter(adapter);
        Cursor data = mdatabasehelper.getOTHERS();
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
            message.setText("No records found on this date.");
        }

    }
    //Electronics
    public void populateElec()
    {
        listview =  (ListView)findViewById(R.id.fields);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        listview.setAdapter(adapter);
        Cursor data = mdatabasehelper.getelecs();
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
            message.setText("No records found on this date.");
        }

    }
}
