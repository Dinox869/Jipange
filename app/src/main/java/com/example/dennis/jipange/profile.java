package com.example.dennis.jipange;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nightonke.boommenu.BoomMenuButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by dennis on 1/30/2018.
 */

public class profile extends Activity {
    ImageView images;
    databasehelper mdatabasehelper = new databasehelper(this);
    BoomMenuButton bmb;

    private static final int  PICK_IMAGE = 100;
    Uri imageUri;
    PieChart pieChart;
    TextView total,name;
    EditText names,email;
    ImageButton add,adds;
    Button back;
    private static final String TAG = "DB";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        //method to retrieve image from database
        showimage();

        add = (ImageButton)findViewById(R.id.add);
        names = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        adds = (ImageButton)findViewById(R.id.adds);
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery();
            }
        });
        pieChart = (PieChart)findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(60f);
        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_get c =  new set_get();
                String NAME =  names.getText().toString();
                String EMAIL = email.getText().toString();
                c.setname(NAME);
                c.setfield(EMAIL);
                mdatabasehelper.insertprofile(c);
                Toast.makeText(profile.this, "Name and Email saved successfully,", Toast.LENGTH_SHORT).show();
            }
        });
        details();
        Cursor zdata = mdatabasehelper.getTotalPrice();
        int i = 0;
        //gets Strings from db.
        ArrayList<String> nvalues = new ArrayList<>();
        for(i = 0; i<zdata.getCount();i++)
        {
            zdata.moveToNext();
            nvalues.add(zdata.getString(0));
        }
        Cursor mdata = mdatabasehelper.getTotalPrice();
        //gets integer values from db.
        ArrayList<Integer> values = new ArrayList<>();
        for(i = 0; i<mdata.getCount();i++)
        {
            mdata.moveToNext();
            values.add(mdata.getInt(1));
        }
        // ArrayList<PieEntry> yvalues = new ArrayList<>();
        ArrayList<PieEntry> yvalues = new ArrayList<>();
        for(i = 0; i<zdata.getCount();i++)
        {
           zdata.moveToNext();
            PieEntry v2e1 = new  PieEntry( values.get(i),nvalues.get(i));
            yvalues.add( v2e1);
        }

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);
        PieDataSet dataSet = new PieDataSet(yvalues,"Fields");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLUE);
        pieChart.setData(data);
        images = (ImageView) findViewById(R.id.profile_image);
        total = (TextView)findViewById(R.id.total);
        Cursor totalvalue = mdatabasehelper.getTotal();
        ArrayList<Integer> Demo = new ArrayList<>();
        for(i = 0;i<totalvalue.getCount();i++)
        {
            totalvalue.moveToNext();
            Demo.add(totalvalue.getInt(0));
        }
        String TOTAL = Demo.get(0).toString();
        total.setText(TOTAL);
    }

    private Boolean showimage() {
        try {

            images = (ImageView) findViewById(R.id.profile_image);
            byte[] bytes = mdatabasehelper.retreiveImageFromDB();

            // Show Image from DB in ImageView
           images.setImageBitmap(Utils.getImage(bytes));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
            return false;
        }
    }
    private void opengallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }
    @Override
    public void onActivityResult(int requestcode,int resultcode,Intent data)
    {
    super.onActivityResult(requestcode,resultcode,data);
        if(resultcode == RESULT_OK && requestcode == PICK_IMAGE)
        {
            imageUri = data.getData();
            images.setImageURI(imageUri);
            saveImageInDB(imageUri);
        }
    }
    public  Boolean saveImageInDB(Uri selectedImageUri) {

        try {
            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
            mdatabasehelper.insertdata(inputData);
            Log.d(TAG,"Picture saved in the db");

            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            Log.d(TAG,"picture Error");
            return false;
        }
    }
    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    public void details()
    {
        Cursor datas = mdatabasehelper.getprofile();
        while(datas.moveToNext())
        {
            String namez = datas.getString(1);
            names.setText(namez);
            String emailz = datas.getString(2);
            email.setText(emailz);
        }
    }
     public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}