package com.chikadibia.teesas.ui.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.chikadibia.teesas.interfaces.IntlPhoneInputListener
import com.chikadibia.teesas.model.User
import com.chikadibia.teesas.network.BackEndApi
import com.chikadibia.teesas.network.WebServiceClient
import com.chikadibia.teesas.util.SingleLiveEvent
import net.rimoto.intlphoneinput.IntlPhoneInput
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForgotPasswordViewModel(application: Application) : AndroidViewModel(application),
    Callback<User>,
    IntlPhoneInputListener {

    var progressDialog: SingleLiveEvent<Boolean>? = null
    var phone: ObservableField<String>? = null
    var btnSelected: ObservableBoolean? = null
    init {
        progressDialog = SingleLiveEvent<Boolean>()
        phone = ObservableField("")
        btnSelected = ObservableBoolean(false)
    }

    override fun done(view: View?, isValid: Boolean) {
        val phoneInputView = view as IntlPhoneInput?
        if(phoneInputView!!.isValid){
            phone = ObservableField(phoneInputView.text)
        }
        btnSelected?.set(phoneInputView!!.isValid)
        Log.e("PHONE",phone?.get()!!)
    }
    fun forgotPassword() {
        progressDialog?.value = true
        WebServiceClient.client.create(BackEndApi::class.java).FORGOT(phone = phone?.get()!!)
            .enqueue(this)

    }
    override fun onResponse(call: Call<User>?, response: Response<User>?) {
        progressDialog?.value = false
//        userLogin?.value = response?.body()

    }
    override fun onFailure(call: Call<User>?, t: Throwable?) {
        progressDialog?.value = false

    }

}