package com.example.dennis.jipange;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

/**
 * Created by dennis on 2/27/2018.
 */

public class populate_savings extends AppCompatActivity {

    ListView list;
    ArrayList<Populate> popi;
    custom_populate adapter = null;
    BoomMenuButton bmb;
    databasehelper mdatabasehelper = new databasehelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.populate_savings);
       populate();
        int i;
        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_1);
        for ( i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(android.R.drawable.ic_input_add)
                    .normalText("Add account")
                    .subNormalText("Create new account.")
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            if ( index == 0)
                            {
                                Intent intent = new Intent(populate_savings.this, saving_form.class);
                                populate_savings.this.startActivity(intent);
                            }
                        }
                    });
            bmb.addBuilder(builder);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(populate_savings.this, saving_form.class);
                populate_savings.this.startActivity(intent);
            }
        });
    }

    private void populate() {


        list = (ListView)findViewById(R.id.listview);
        popi = new ArrayList<>();
        adapter = new custom_populate(this, R.layout.listview, popi);
        list.setAdapter(adapter);
        Cursor data = mdatabasehelper.retreiveImagesFromDB();
        popi.clear();
        while(data.moveToNext())
        {
            int id = data.getInt(0);
            String name = data.getString(2);
            byte[] image = data.getBlob(1);
            popi.add(new Populate(name,image,id));

        }
        adapter.notifyDataSetChanged();

    }

}
