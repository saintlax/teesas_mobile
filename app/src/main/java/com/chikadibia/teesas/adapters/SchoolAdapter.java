package com.chikadibia.teesas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chikadibia.teesas.R;
import com.chikadibia.teesas.model.Preschool;

import java.util.List;

public class SchoolAdapter extends BaseAdapter {
    Context context;
    List<Preschool> preschools;
    public SchoolAdapter(Context context, List<Preschool> preschools){
        this.context = context;
        this.preschools = preschools;
    }
    @Override
    public int getCount() {
        return preschools.size();
    }

    @Override
    public Preschool getItem(int i) {
        return preschools.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_for_custom_list_view,viewGroup,false);
        Preschool preschool = preschools.get(i);
        ImageView icon = row.findViewById(R.id.menu_icon);
        icon.setImageResource(R.drawable.ic_person);
        TextView name = row.findViewById(R.id.menu_name);
        name.setText(preschool.getName());
        return row;
    }
}
