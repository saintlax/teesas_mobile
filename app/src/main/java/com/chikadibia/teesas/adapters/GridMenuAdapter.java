package com.chikadibia.teesas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chikadibia.teesas.R;

import java.util.List;

public class GridMenuAdapter extends BaseAdapter {
    Context context;
    List<String> menu_name;
    List<Integer> menu_icon;
    LayoutInflater inflater;
    public GridMenuAdapter(Context con, List<String> menuname, List<Integer> menuicon){
        context = con;
        menu_name = menuname;
        menu_icon = menuicon;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return menu_icon.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= inflater.inflate(R.layout.row_home_menu, null);
        TextView txttitle = (TextView)convertView.findViewById(R.id.textTitle);
        ImageView imglogo = (ImageView)convertView.findViewById(R.id.imageLogo);
        txttitle.setText(menu_name.get(position));

        txttitle.setTextColor(context.getResources().getColor(R.color.black));
        imglogo.setImageResource(menu_icon.get(position));
        return convertView;
    }
}