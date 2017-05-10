package com.masaref.bahez.masaref.MasarefCustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.masaref.bahez.masaref.R;

/**
 * Created by Bahez on 4/19/2017.
 */
public class CustomSpinnerAdapter extends BaseAdapter {

    private final Context context;
    private final String[] itemname;
    private final Integer[] imgid;
    LayoutInflater inflater;


    public CustomSpinnerAdapter(Context  applicationContext, String[] itemname, Integer[] imgid){
        this.context = applicationContext;
        this.imgid = imgid;
        this.itemname = itemname;
        inflater = (LayoutInflater.from(applicationContext));

    }



    @Override
    public int getCount() {
        return itemname.length;
    }

    @Override
    public Object getItem(int position) {
        return itemname[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = inflater.inflate(R.layout.custom_list_view, null);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.custom_list_item_text_view);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.custom_list_item_icon);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        return rowView;
    }
}
