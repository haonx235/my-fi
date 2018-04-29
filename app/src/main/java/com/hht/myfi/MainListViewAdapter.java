package com.hht.myfi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by streetguard on 27-Dec-17.
 */

class MainListViewAdapter extends ArrayAdapter<String>
{
    Context context;
    int[] imgs;
    String myTitles[];
    String myDescription[];

    MainListViewAdapter(Context c, String[] titles, int[] imgs, String[] descriptions)
    {
        super(c, R.layout.row, R.id.tvwTitle, titles);
        this.context = c;
        this.imgs = imgs;
        this.myTitles = titles;
        this.myDescription = descriptions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);
        ImageView images = (ImageView) row.findViewById(R.id.icon);
        TextView myTitle = (TextView) row.findViewById(R.id.tvwTitle);
        TextView myDes = (TextView) row.findViewById(R.id.tvwDescription);
        images.setImageResource(imgs[position]);
        myTitle.setText(myTitles[position]);
        myDes.setText(myDescription[position]);
        return row;
    }
}