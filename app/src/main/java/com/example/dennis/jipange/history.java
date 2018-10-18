package com.example.dennis.jipange;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;

/**
 * Created by dennis on 1/30/2018.
 */

public class history extends AppCompatActivity {
    ArrayList<Integer> Imagelist;
    ArrayList<String> Menunames;
    ArrayList<String> Subs;
    custom_populatehist adapter = null;
    ArrayList<histolist> popi;
    TextView total;
    databasehelper mdatabasehelper = new databasehelper(this);
    private static final String TAG = "History";
    public static final String URL_SAVE_NAME = "https://jipange.000webhostapp.com/androids/trying.php";
    public static final int NAME_SYNCED_WITH_SERVER = 1;
    public static final int NAME_NOT_SYNCED_WITH_SERVER = 0;
    public static final String DATA_SAVED_BROADCAST = "datasaved to mysql";
    private BroadcastReceiver broadcastReceiver;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        registerReceiver(new NetworkStateChecker(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        Imagelist = new ArrayList<>();
        Menunames = new ArrayList<>();
        total = (TextView) findViewById(R.id.total);
        Subs = new ArrayList<>();


        setInitialData();
        populate();
        //the broadcast receiver to update sync status
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                //loading the names again
                populate();
            }
        };

        //registering the broadcast receiver to update sync status
        registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST));
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(Imagelist.get(i))
                    .normalText(Menunames.get(i))
                    .subNormalText(Subs.get(i))
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            if (index == 0) {
                                Intent intent = new Intent(history.this, date.class);
                                history.this.startActivity(intent);

                            }
                            if (index == 1) {
                                Intent intent = new Intent(history.this, field.class);
                                history.this.startActivity(intent);

                            }
                            if (index == 2) {
                                Intent intent = new Intent(history.this, datefield.class);
                                history.this.startActivity(intent);

                            }
                        }
                    });
            bmb.addBuilder(builder);
        }
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    private void populate() {
        total.setText("");
        ListView list = (ListView) findViewById(R.id.History_local);
        popi = new ArrayList<>();
        adapter = new custom_populatehist(this, R.layout.priceslist, popi);
        list.setAdapter(adapter);
        Cursor data = mdatabasehelper.getdata();
        popi.clear();
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String name = data.getString(1);
            String price = data.getString(4);
            int status = data.getInt(5);
            popi.add(new histolist(name, price, id, status));
        }
        adapter.notifyDataSetChanged();
        Cursor datas = mdatabasehelper.getTotal();
        while (datas.moveToNext()) {
            String price = datas.getString(0);
            total.setText("Total price: " + price);
        }
        if (popi.size() == 0) {
            total.setText("No records found .");
        }
    }
    private void setInitialData() {

        Imagelist.add(R.drawable.ic_access_time_black_24dp);
        Imagelist.add(R.drawable.ic_view_carousel_black_24dp);
        Imagelist.add(R.drawable.ic_all_inclusive_black_24dp);

        Menunames.add("Sort by DATE.");
        Menunames.add("Sort by FIELDs");
        Menunames.add("Sort by DATE and FIELDs");

        Subs.add("Arrange data based on date");
        Subs.add("Arrange data based on the various fields/units");
        Subs.add("Arrange data based on date and fields");

    }

}
