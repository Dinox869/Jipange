package com.example.dennis.jipange;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by dennis on 1/30/2018.
 */

public class saf extends AppCompatActivity {

    CardView loans,savings;
    Cursor data;

    databasehelper mdatabasehelper = new databasehelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saf);
        loans = (CardView) findViewById(R.id.loans);
        savings = (CardView)findViewById(R.id.savings);


        loans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Safaricom API needed.
                Toast.makeText(saf.this, "Kindly wait for API ", Toast.LENGTH_SHORT).show();
            }
        });
        savings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = mdatabasehelper.getsavings();
                final int i = data.getCount();
                if( i == 0 )
                {
                Intent intent = new Intent(saf.this, saving_form.class);
                saf.this.startActivity(intent);
                }
                if (i > 0)
                {
                    Intent intent = new Intent(saf.this, populate_savings.class);
                    saf.this.startActivity(intent);
                }
            }
        });

}
}