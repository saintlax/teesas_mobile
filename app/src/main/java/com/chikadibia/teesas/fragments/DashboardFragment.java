package com.chikadibia.teesas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.chikadibia.teesas.R;
import com.chikadibia.teesas.adapters.GridMenuAdapter;
import com.chikadibia.teesas.model.User;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements  GridView.OnItemClickListener {

    List<String> menu_name;
    List<Integer> menu_icon;
    Context context;
    OnGridItemClickListener onGridItemClickListener;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        context = getActivity();

        menu_icon = new ArrayList<Integer>();
        menu_name = new ArrayList<String>();


        menu_icon.add(R.drawable.layers);
        menu_name.add(getString(R.string.menu_my_topics));

        menu_icon.add(R.drawable.controller);
        menu_name.add(getString(R.string.menu_take_quiz));

        menu_icon.add(R.drawable.conference);
        menu_name.add(getString(R.string.menu_my_friends));

        menu_icon.add(R.drawable.ttrophy_blue);
        menu_name.add(getString(R.string.menu_results));


        GridView gridview = (GridView)view.findViewById(R.id.gridView);
        GridMenuAdapter adapter = new GridMenuAdapter(getActivity(),menu_name,menu_icon);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        onGridItemClickListener = (OnGridItemClickListener)context;
    }

    public interface OnGridItemClickListener{
        void onItemClicked(AdapterView<?> adapterView, View view, int i, long l);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        onGridItemClickListener.onItemClicked(adapterView,view, i, l);
    }

}
