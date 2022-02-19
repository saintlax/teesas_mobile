package com.chikadibia.teesas.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chikadibia.teesas.R
import com.chikadibia.teesas.databinding.FragmentRegisterBinding
import com.chikadibia.teesas.dialogs.Dialogg
import com.chikadibia.teesas.interfaces.GetPreschoolCallback
import com.chikadibia.teesas.interfaces.GetStringCallback
import com.chikadibia.teesas.model.Preschool
import com.chikadibia.teesas.ui.viewmodel.RegisterViewModel
import com.chikadibia.teesas.util.CustomeProgressDialog

class RegisterFragment : Fragment() {

    var customeProgressDialog: CustomeProgressDialog? = null
    private lateinit var viewModel: RegisterViewModel
    var dialog: Dialogg? = null
    var contextt: Context? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentRegisterBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_register,container,false)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = this
        customeProgressDialog = CustomeProgressDialog(activity)
        initObservables(activity)
        contextt = activity
        dialog = Dialogg(contextt)
        return binding.root
    }
    private fun initObservables(context: Context?) {
        viewModel?.progressDialog?.observe(this, Observer {
            if (it!!) customeProgressDialog?.show() else customeProgressDialog?.dismiss()
        })
        viewModel?.pre_school?.observe(this, Observer {
            if (it!!) customeProgressDialog?.show() else customeProgressDialog?.dismiss()
        })

        viewModel?.preschoolList?.observe(viewLifecycleOwner, Observer { data ->
            schoolDialog(context, data)
        })

        viewModel?.isDateClicked?.observe(this, Observer {
//            dialog!!.datePickerDialog {
//                viewModel?.setDateMutableLiveData(it)
//            }
            dialog!!.datePickerDialog(object : GetStringCallback {
                override fun done(value: String?) {
                    viewModel?.setDateMutableLiveData(value!!)
                }
            })

        })

    }

    private fun schoolDialog(context: Context?,array: List<Preschool?>?) {
//        dialog!!.preSchoolDialog(array) { preschool ->
//            viewModel?.setPreschoolMutableLiveData(preschool)
//        }
        dialog!!.preSchoolDialog(array, object : GetPreschoolCallback {
            override fun done(preschool: Preschool?) {
                viewModel?.setPreschoolMutableLiveData(preschool!!)
            }
        })
    }


}