package com.example.dennis.jipange;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by dennis on 3/8/2018.
 */

public class welcome extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mpager;
    private int[] layout = {R.layout.first_slide,R.layout.slide2,R.layout.slide3,R.layout.slide4};
    private Mpageradapter mpageradapter;
    private LinearLayout Dots_layout;
    private ImageView[] dots;
     Button next,skip;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(new PreferenceManager(this).checkPreference())
        {
            loadHome();
        }
        if(Build.VERSION.SDK_INT>= 19)
        {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.welcome);
        mpager = (ViewPager)findViewById(R.id.viewpager);
        mpageradapter = new Mpageradapter(layout,this);
        mpager.setAdapter(mpageradapter);
        Dots_layout = (LinearLayout)findViewById(R.id.dotslayout);
        next = (Button)findViewById(R.id.next);
        skip = (Button)findViewById(R.id.skip);
        next.setOnClickListener(this);
        skip.setOnClickListener(this);
        createDots(0);
        mpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {
                createDots(position);
                if(position == layout.length-1)
                {
                    next.setText("Start");
                    skip.setVisibility(View.INVISIBLE);
                }
                else
                {
                    next.setText("Next");
                    skip.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void createDots(int current_position)
    {
        if(Dots_layout!=null)
            Dots_layout.removeAllViews();
            dots = new ImageView[layout.length];


        for(int i = 0; i< layout.length; i ++)
        {
            dots[i] = new ImageView(this);
            if(i==current_position)
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));
            }
            else
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.inactive_dots));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);
            Dots_layout.addView(dots[i],params);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.next:
                loadnextslide();
                break;
            case R.id.skip:
                loadHome();
                new PreferenceManager(this).writePreference();
                break;

        }

    }

    private void loadHome()
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
    private void loadnextslide()
    {
        int next_slide = mpager.getCurrentItem()+1;
        if(next_slide<layout.length)
        {
            mpager.setCurrentItem(next_slide);
        }
        else
        {
            loadHome();
            new PreferenceManager(this).writePreference();
        }


    }


}