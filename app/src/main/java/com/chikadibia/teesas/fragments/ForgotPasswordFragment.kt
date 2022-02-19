package com.chikadibia.teesas.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chikadibia.teesas.R
import com.chikadibia.teesas.databinding.FragmentForgotPasswordBinding
import com.chikadibia.teesas.ui.viewmodel.ForgotPasswordViewModel
import com.chikadibia.teesas.util.CustomeProgressDialog

class ForgotPasswordFragment : Fragment() {

    var customeProgressDialog: CustomeProgressDialog? = null
    private lateinit var viewModel: ForgotPasswordViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentForgotPasswordBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_forgot_password,container,false)
        viewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel::class.java)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = this
        customeProgressDialog = CustomeProgressDialog(activity)
        initObservables(activity)
        return binding.root
    }
    private fun initObservables(context: Context?) {
        viewModel?.progressDialog?.observe(this, Observer {
            if (it!!) customeProgressDialog?.show() else customeProgressDialog?.dismiss()
        })

//
//        viewModel?.userLogin?.observe(viewLifecycleOwner, Observer { user ->
//            Toast.makeText(activity, "welcome, ${user?.last_name}", Toast.LENGTH_LONG).show()
//        })
    }


}