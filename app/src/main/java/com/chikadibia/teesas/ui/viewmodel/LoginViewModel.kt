package com.chikadibia.teesas.ui.viewmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.chikadibia.teesas.interfaces.IntlPhoneInputListener
import com.chikadibia.teesas.model.User
import com.chikadibia.teesas.network.BackEndApi
import com.chikadibia.teesas.network.WebServiceClient
import com.chikadibia.teesas.util.SingleLiveEvent
import com.chikadibia.teesas.util.Util
import net.rimoto.intlphoneinput.IntlPhoneInput
import com.chikadibia.teesas.fragments.LoginFragment
import com.chikadibia.teesas.util.Constants
import java.security.AccessController.getContext


class LoginViewModel(application: Application) : AndroidViewModel(application), Callback<User>,
    IntlPhoneInputListener {

    var btnSelected: ObservableBoolean? = null
    var password: ObservableField<String>? = null
    var progressDialog: SingleLiveEvent<Boolean>? = null
    var userLogin: MutableLiveData<User>? = null
    var phone: ObservableField<String>? = null
    var forgotPassword: SingleLiveEvent<Boolean>? = null
    var register: SingleLiveEvent<Boolean>? = null
    init {
        btnSelected = ObservableBoolean(false)
        progressDialog = SingleLiveEvent<Boolean>()
        password = ObservableField("")
        userLogin = MutableLiveData<User>()
        phone = ObservableField("")
        forgotPassword = SingleLiveEvent<Boolean>()
        register = SingleLiveEvent<Boolean>()
    }

    fun forgotPassword(){
        forgotPassword?.value = true
    }

    fun register(){
        register?.value = true
    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(!phone?.get()!!.equals("") && s.toString().length >= 8)
    }

    fun login() {
        progressDialog?.value = true
        WebServiceClient.client.create(BackEndApi::class.java).LOGIN(phone = phone?.get()!!
            , password = password?.get()!!)
            .enqueue(this)

    }

    override fun onResponse(call: Call<User>?, response: Response<User>?) {
        progressDialog?.value = false
        userLogin?.value = response?.body()

    }

    override fun onFailure(call: Call<User>?, t: Throwable?) {
        progressDialog?.value = false

    }

    override fun done(view: View?, isValid: Boolean) {
        val phoneInputView = view as IntlPhoneInput?
        if(phoneInputView!!.isValid){
            phone = ObservableField(phoneInputView.text)
        }
        btnSelected?.set(phoneInputView!!.isValid && password?.get()!!.length >= 8)
    }

}