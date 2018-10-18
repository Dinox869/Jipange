package com.example.dennis.jipange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dennis on 3/9/2018.
 */

public class custom_populatehist extends BaseAdapter {
        private Context context;
        private int layout;
        private ArrayList<histolist> list;

        public custom_populatehist(Context context, int layout, ArrayList<histolist> list) {
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
            TextView Price;
            TextView Name;
            ImageView imageViewStatus;


        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View row = view;
           ViewHolder holder = new ViewHolder();
            if(row == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(layout ,null);
                holder.Price = (TextView)row.findViewById(R.id.numbers);
                holder.Name = (TextView)row.findViewById(R.id.statement);
                holder.imageViewStatus =  (ImageView)row.findViewById(R.id.imageViewStatus);
                row.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) row.getTag();
            }

            histolist pop = list.get(position);
            holder.Price.setText(pop.getPrice());
            holder.Name.setText(pop.getName());
            if (pop.getStatus() == 0 )
                holder.imageViewStatus.setBackgroundResource(R.drawable.stopwatch);
            else
               holder.imageViewStatus.setBackgroundResource(R.drawable.success);


            return row;
        }
    }

