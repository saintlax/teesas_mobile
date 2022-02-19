package com.chikadibia.teesas.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.chikadibia.teesas.R
import android.widget.DatePicker
import android.view.View
import com.chikadibia.teesas.model.Preschool
import com.chikadibia.teesas.interfaces.GetPreschoolCallback
import com.chikadibia.teesas.adapters.PreschoolListViewAdapter
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView

class Dialog(var context: Context?) {
    /*
    var alertDialog: AlertDialog.Builder? = null
    fun datePickerDialog(callback: () -> Unit) {
        alertDialog = AlertDialog.Builder(context)
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_date_picker, null) as View
        val datePicker = view.findViewById<View>(R.id.datePicker) as DatePicker
        alertDialog!!.setTitle("Birthday")
        alertDialog!!.setView(view)
            .setPositiveButton("Ok") { dialog, id ->
                val day = datePicker.dayOfMonth
                val month = datePicker.month
                val year = datePicker.year
                val birthDay = "$year/$month/$day"
                callback.done(birthDay)
            }
        alertDialog!!.setView(view)
        alertDialog!!.show()
    }

    fun preSchoolDialog(array: List<Preschool>?, callback: GetPreschoolCallback) {
        alertDialog = AlertDialog.Builder(context)
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val convertView = inflater.inflate(R.layout.dialog_custom_menu_listview, null) as View
        alertDialog!!.setView(convertView)
        alertDialog!!.setTitle("Choose a school type")
        val lv = convertView.findViewById<View>(R.id.lv) as ListView
        val adapter = PreschoolListViewAdapter(context!!, array!!)
        lv.adapter = adapter
        val ad = alertDialog!!.show()
        lv.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val preschool = array[position]
            callback.done(preschool)
            ad.dismiss()
        }
    }
    */
}