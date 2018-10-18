package com.example.dennis.jipange;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by dennis on 2/23/2018.
 */

public class saving_form extends AppCompatActivity {

    Button open;
    ImageButton add;
    Uri imageUri;
    EditText rates,periods,name,price;
    Spinner period,rate;
    databasehelper mdatabasehelper = new databasehelper(this);
    ImageView images;
    private static final String TAG = "Saving_form picture";
    private static final int  PICK_IMAGE = 100;
    String names;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saving_form);
        open = (Button)findViewById(R.id.open);
        name = (EditText)findViewById(R.id.name);
        price = (EditText)findViewById(R.id.amount);
        add = (ImageButton) findViewById(R.id.add);
        //image
        images = (ImageView)findViewById(R.id.photo);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.length()!= 0 && price.length()>0 )
                {
                    String NAME = name.getText().toString();
                    String PRICE = price.getText().toString();
                    set_get c = new set_get();
                    c.setname(NAME);
                    c.setprice(PRICE);
                    mdatabasehelper.insertsaving_saving_form(c);
                    saveImageInDB(imageUri);
                    name.setText("");
                    price.setText("");
                    Intent intent = new Intent(saving_form.this, populate_savings.class);
                    saving_form.this.startActivity(intent);
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery();
            }
        });
    }
    private Boolean showimage() {
        Cursor data = mdatabasehelper.retreiveImagesFromDB();
        byte[] bytes = new byte[0];
        ArrayList<String> NAMES = new ArrayList<>();
        int i;
        for(i = 0; i<data.getCount();i++)
        {
            data.moveToNext();
            bytes = data.getBlob(1);
        }
        for(i = 0; i<data.getCount();i++)
        {
            data.moveToNext();
            NAMES.add(data.getString(2));
        }

        try {
            images = (ImageView) findViewById(R.id.photo);
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

        }
    }
    //Save data to the images database
    public  Boolean saveImageInDB(Uri selectedImageUri) {
        String names;
        name = (EditText)findViewById(R.id.name);
        names = name.getText().toString();
        try {


            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);

            mdatabasehelper.insertdatas(inputData,names);
            Log.d(TAG,"Picture saved in the db");

            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            Log.d(TAG,"picture Error");
            return false;
        }

    }
}
