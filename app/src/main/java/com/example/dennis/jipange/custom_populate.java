package com.example.dennis.jipange;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dennis on 3/2/2018.
 */

public class custom_populate extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Populate> list;

    public custom_populate(Context context, int layout, ArrayList<Populate> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder
    {
        ImageView imageView;
        TextView Name;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View row = view;
        ViewHolder holder = new ViewHolder();
        if(row == null)

        {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout ,null);
            holder.Name = (TextView)row.findViewById(R.id.name);
            holder.imageView = (ImageView)row.findViewById(R.id.photo);
            row.setTag(holder);

        }

        else

        {

         holder = (ViewHolder) row.getTag();

        }

        Populate pop = list.get(position);
        holder.Name.setText(pop.getName());

        byte[] images = pop.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(images, 0 , images.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;

    }
}
