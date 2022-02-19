package com.chikadibia.teesas.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chikadibia.teesas.R
import com.chikadibia.teesas.databinding.FragmentLoginBinding
import com.chikadibia.teesas.ui.viewmodel.LoginViewModel
import com.chikadibia.teesas.util.Constants
import com.chikadibia.teesas.util.CustomeProgressDialog
import com.chikadibia.teesas.util.Util

class LoginFragment : Fragment() {
    var customeProgressDialog: CustomeProgressDialog? = null
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
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

        viewModel?.forgotPassword?.observe(this, Observer {
            if (context != null) {
                Util.authenticationFragment(
                    context, Constants.FRAGMENT_TO_DIRECTION, ForgotPasswordFragment(), null
                )
            }
        })

        viewModel?.register?.observe(this, Observer {
            if (context != null) {
                Util.authenticationFragment(
                    context, Constants.FRAGMENT_TO_DIRECTION, RegisterFragment(), null
                )
            }
        })

        viewModel?.userLogin?.observe(viewLifecycleOwner, Observer { user ->
            Toast.makeText(activity, "welcome, ${user?.name}", Toast.LENGTH_LONG).show()
            //navigate to Home Activity
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context);
    }

}