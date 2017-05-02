package com.masaref.bahez.masaref.MasarefCustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.masaref.bahez.masaref.R;

/**
 * Created by Bahez on 4/17/2017.
 */
public class CustomListAdapter extends ArrayAdapter<String>{

    private final Context context;
    private final String[] itemname;
    private final Integer[] imgid;
    LayoutInflater inflater;

    public CustomListAdapter(Context  applicationContext, String[] itemname, Integer[] imgid) {
        super(applicationContext, R.layout.custom_list_view, itemname);
        // TODO Auto-generated constructor stub

        this.context=applicationContext;
        this.itemname=itemname;
        this.imgid=imgid;
        inflater = (LayoutInflater.from(applicationContext));
    }

    public View getView(int position, View view, ViewGroup parent) {

        View rowView = inflater.inflate(R.layout.custom_list_view, null);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.custom_list_item_text_view);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.custom_list_item_icon);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        return rowView;

    }
}
