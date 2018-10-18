package com.example.dennis.jipange;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

import java.util.ArrayList;

/**
 * Created by dennis on 1/30/2018.
 */

public class  graph extends AppCompatActivity {


    databasehelper mdatabasehelper = new databasehelper(this);
    BarChart chart;
    ArrayList<BarEntry> valueSet2;
    ArrayList<Integer> Imagelist;
    ArrayList<String> Menunames;
    ArrayList<String> Subs,nvalues;
    private BoomMenuButton bmb;
    Toolbar toolbar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        Imagelist = new ArrayList<>();
        Menunames =  new ArrayList<>();
        Subs = new ArrayList<>();
        BarChart chart  = (BarChart) findViewById(R.id.chart);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setMaxVisibleValueCount(50);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(true);
        chart.setDragEnabled(true);
        chart.setTouchEnabled(true);
        chart.setScaleEnabled(true);
        Cursor data = mdatabasehelper.getprice();
        setInitialData();
        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.Ham);
        int i;
        for ( i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(Imagelist.get(i))
                    .normalText(Menunames.get(i))
                    .shadowColor(Color.parseColor("#ee000000"))
                    .subNormalText(Subs.get(i))
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            if ( index == 0)
                            {

                            }
                            if ( index == 1)
                            {
                                finish();
                                Intent intent = new Intent(graph.this, foodgraph.class);
                                graph.this.startActivity(intent);
                            }
                            if ( index == 2)
                            {
                                finish();
                                Intent intent = new Intent(graph.this, drinkgraph.class);
                                graph.this.startActivity(intent);
                            }
                            if(index == 3)
                            {
                                finish();
                                Intent intent = new Intent(graph.this, allgraph.class);
                                graph.this.startActivity(intent);
                            }
                        }
                    });
            bmb.addBuilder(builder);
        }

        ArrayList<String> nvalues = new ArrayList<>();
        for(i = 0; i<data.getCount();i++)
        {
            data.moveToNext();
            nvalues.add(data.getString(0));
        }
        Cursor mdata = mdatabasehelper.getprice();
        ArrayList<Integer> values = new ArrayList<>();
        for(i = 0; i<mdata.getCount();i++)
        {
            mdata.moveToNext();
            values.add(mdata.getInt(1));
        }
        valueSet2 = new ArrayList<>();
        for(i = 0; i<data.getCount();i++)
        {
            data.moveToNext();
            BarEntry v2e1 = new BarEntry(i , values.get(i));
            valueSet2.add( v2e1);
        }
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, nvalues.toString());
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData dataSets = new BarData(barDataSet2);
        chart.setData(dataSets);
        dataSets.setBarWidth(1f);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        String[] categories = new String[nvalues.size()];
        categories = nvalues.toArray(categories);
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new MyXaxisnaming(categories));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }
    public class MyXaxisnaming implements IAxisValueFormatter
    {
        private String[] mvalues;
        public MyXaxisnaming(String[] values ){
            this.mvalues = values;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mvalues[(int)value];
        }
    }
    //for ham.
    private void setInitialData() {
        //icons in ham
        Imagelist.add(R.drawable.ic_directions_car_black_24dp);
        Imagelist.add(R.drawable.ic_restaurant_black_24dp);
        Imagelist.add(R.drawable.ic_free_breakfast_black_24dp);
        Imagelist.add(R.drawable.ic_insert_chart_black_24dp);
        //Title in ham
        Menunames.add("Graph for Transport.");
        Menunames.add("Graph for Food & Snacks.");
        Menunames.add("Graph for Drinks");
        Menunames.add("Graph for All fields");
        //Subtitle in ham
        Subs.add("Statistics graphs for transport.");
        Subs.add("Statistics graphs for food & snacks.");
        Subs.add("Statistics graphs for drinks.");
        Subs.add("Statistics graphs for all the fields.");

    }
    //method to implement Drink
    public void Drinkgraph()
    {
        BarChart chart  = (BarChart) findViewById(R.id.chart);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setMaxVisibleValueCount(50);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(true);

        Cursor data = mdatabasehelper.getDrinksprice();
        int i;
        ArrayList<String> nvalues = new ArrayList<>();
        for(i = 0; i<data.getCount();i++)
        {
            data.moveToNext();
            nvalues.add(data.getString(0));
        }
        Cursor mdata = mdatabasehelper.getDrinksprice();
        ArrayList<Integer> values = new ArrayList<>();
        for(i = 0; i<mdata.getCount();i++)
        {
            mdata.moveToNext();
            values.add(mdata.getInt(1));
        }
        valueSet2 = new ArrayList<>();
        for(i = 0; i<data.getCount();i++)
        {
            data.moveToNext();
            BarEntry v2e1 = new BarEntry(i , values.get(i));
            valueSet2.add( v2e1);
        }
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 1");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData dataSets = new BarData(barDataSet2);
        chart.setData(dataSets);
        dataSets.setBarWidth(1f);
        String[] categories = new String[nvalues.size()];
        categories = nvalues.toArray(categories);
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new MyXaxisnaming(categories));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

    }
    // method to implement bill
    public void Billgraph()
    {
        BarChart chart  = (BarChart) findViewById(R.id.chart);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setMaxVisibleValueCount(50);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(true);
        Cursor data = mdatabasehelper.getBillprice();
        int i;
        ArrayList<String> nvalues = new ArrayList<>();
        for(i = 0; i<data.getCount();i++)
        {
            data.moveToNext();
            nvalues.add(data.getString(0));
        }
        Cursor mdata = mdatabasehelper.getBillprice();
        ArrayList<Integer> values = new ArrayList<>();
        for(i = 0; i<mdata.getCount();i++)
        {
            mdata.moveToNext();
            values.add(mdata.getInt(1));
        }
        valueSet2 = new ArrayList<>();
        for(i = 0; i<data.getCount();i++)
        {
            data.moveToNext();
            BarEntry v2e1 = new BarEntry(i , values.get(i));
            valueSet2.add( v2e1);
        }
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 1");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData dataSets = new BarData(barDataSet2);
        chart.setData(dataSets);
        dataSets.setBarWidth(1f);
        String[] categories = new String[nvalues.size()];
        categories = nvalues.toArray(categories);
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new MyXaxisnaming(categories));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

    }
    //method to implement All fields
    public void Allfieldsgraph()
    {
        BarChart chart  = (BarChart) findViewById(R.id.chart);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setMaxVisibleValueCount(50);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(true);
        Cursor data = mdatabasehelper.getTotalPrice();
        int i;
        ArrayList<String> nvalues = new ArrayList<>();
        for(i = 0; i<data.getCount();i++)
        {
            data.moveToNext();
            nvalues.add(data.getString(0));
        }
        Cursor mdata = mdatabasehelper.getTotalPrice();
        ArrayList<Integer> values = new ArrayList<>();
        for(i = 0; i<mdata.getCount();i++)
        {
            mdata.moveToNext();
            values.add(mdata.getInt(1));
        }
        valueSet2 = new ArrayList<>();
        for(i = 0; i<data.getCount();i++)
        {
            data.moveToNext();
            BarEntry v2e1 = new BarEntry(i , values.get(i));
            valueSet2.add( v2e1);
        }
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 1");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData dataSets = new BarData(barDataSet2);
        chart.setData(dataSets);
        dataSets.setBarWidth(1f);
        String[] categories = new String[nvalues.size()];
        categories = nvalues.toArray(categories);
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new MyXaxisnaming(categories));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

    }


}