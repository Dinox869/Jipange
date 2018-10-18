package com.example.dennis.jipange;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dennis on 3/8/2018.
 */

public class Mpageradapter extends PagerAdapter {


    private int[] layout;
    private LayoutInflater layoutInflater;
    private Context context;

    public Mpageradapter(int[] layout,Context context)
    {
        this.layout = layout;
        this.context = context;
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return layout.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(layout[position],container,false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        View view = (View)object;
        container.removeView(view);

    }
}
