package com.chikadibia.teesas.adapters

import android.content.Context
import com.chikadibia.teesas.model.Preschool
import android.widget.BaseAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.chikadibia.teesas.R
import android.widget.TextView

class PreschoolListViewAdapter(var context: Context, var array: List<Preschool>) : BaseAdapter() {
    override fun getCount(): Int {
        return array.size
    }

    override fun getItem(position: Int): Preschool {
        return array[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.row_for_custom_list_view, viewGroup, false)
        val preschool = array[i]
        val icon = row.findViewById<View>(R.id.menu_icon) as ImageView
        //icon.setImageResource(preschool.getIcon());R.drawable.ic_person
        //we might need to show a picture of the school
        icon.setImageResource(R.drawable.ic_person)
        val name = row.findViewById<View>(R.id.menu_name) as TextView
        name.text = preschool.name
        return row
    }
}