package com.chikadibia.teesas.dialogs;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import com.chikadibia.teesas.R;
import com.chikadibia.teesas.adapters.SchoolAdapter;
import com.chikadibia.teesas.interfaces.GetPreschoolCallback;
import com.chikadibia.teesas.interfaces.GetStringCallback;
import com.chikadibia.teesas.model.Preschool;

import java.util.List;

public class Dialogg {
    Context context;
    AlertDialog.Builder alertDialog;
    public Dialogg(Context context){
        this.context = context;
    }

    public void datePickerDialog(GetStringCallback callback){
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Birthday");
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_date_picker, null);
        final DatePicker datePicker = view.findViewById(R.id.datePicker);
        alertDialog.setView(view).setPositiveButton("Ok", (dialog, which) -> {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();
            String birthDay = year+"-"+month+"-"+day;
            callback.done(birthDay);
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    public void preSchoolDialog(final List<Preschool> data, GetPreschoolCallback callback){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_custom_menu_listview, null);
        alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Choose School");
        ListView listView = (ListView)view.findViewById(R.id.lv);
        SchoolAdapter adapter = new SchoolAdapter(context, data);
        listView.setAdapter(adapter);
        alertDialog.setView(view);
        final AlertDialog ad = alertDialog.show();
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Preschool preschool = data.get(i);
            callback.done(preschool);
            ad.dismiss();
        });
    }
}
