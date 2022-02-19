package com.chikadibia.teesas.fragments

import android.content.Context
import android.widget.AdapterView.OnItemClickListener
import com.chikadibia.teesas.fragments.DashboardFragment.OnGridItemClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.chikadibia.teesas.R
import android.widget.GridView
import com.chikadibia.teesas.adapters.GridMenuAdapter
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.chikadibia.teesas.model.User
import java.util.ArrayList

class DashboardFragment : Fragment(), OnItemClickListener {
    var menu_name: MutableList<String>? = null
    var menu_icon: MutableList<Int>? = null
    var onGridItemClickListener: OnGridItemClickListener? = null
    var user: User? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        menu_icon = ArrayList()
        menu_name = ArrayList()
        menu_icon?.add(R.drawable.layers)
        menu_name?.add(getString(R.string.menu_my_topics))
        menu_icon?.add(R.drawable.controller)
        menu_name?.add(getString(R.string.menu_take_quiz))
        menu_icon?.add(R.drawable.conference)
        menu_name?.add(getString(R.string.menu_my_friends))
        menu_icon?.add(R.drawable.ttrophy_blue)
        menu_name?.add(getString(R.string.menu_results))
        val gridview = view.findViewById<View>(R.id.gridView) as GridView
        val adapter = GridMenuAdapter(activity, menu_name, menu_icon)
        gridview.adapter = adapter
        gridview.onItemClickListener = this
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    interface OnGridItemClickListener {
        fun onItemClicked(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long)
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
        onGridItemClickListener!!.onItemClicked(adapterView, view, i, l)
    }
}