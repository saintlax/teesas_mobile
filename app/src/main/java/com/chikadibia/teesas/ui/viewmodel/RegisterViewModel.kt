package com.chikadibia.teesas.ui.viewmodel


import android.app.Application
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chikadibia.teesas.R
import com.chikadibia.teesas.interfaces.IntlPhoneInputListener
import com.chikadibia.teesas.model.Preschool
import com.chikadibia.teesas.model.RegisterData
import com.chikadibia.teesas.model.User
import com.chikadibia.teesas.network.BackEndApi
import com.chikadibia.teesas.network.WebServiceClient
import com.chikadibia.teesas.util.SingleLiveEvent
import com.chikadibia.teesas.util.Util
import com.google.gson.Gson
import net.rimoto.intlphoneinput.IntlPhoneInput
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterViewModel(application: Application) : AndroidViewModel(application),
    Callback<List<Preschool?>>,
    IntlPhoneInputListener {

    var progressDialog: SingleLiveEvent<Boolean>? = null
    var phone: ObservableField<String>? = null
    var btnSelected: ObservableBoolean? = null
    var pre_school: SingleLiveEvent<Boolean>? = null
    var primary_school: SingleLiveEvent<Boolean>? = null
    var preschoolList: MutableLiveData<List<Preschool?>>? = null
    var schoolType: ObservableField<String>? = null
    var preschoolMutableLiveData = MutableLiveData<Preschool>()
    var password: ObservableField<String>? = null
    var email: ObservableField<String>? = null
    var name: ObservableField<String>? = null
    var location: ObservableField<String>? = null
    var isDateClicked: SingleLiveEvent<Boolean>? = null
    var dateMutableLiveData = MutableLiveData<String>()
    var gender: ObservableField<String>? = null
    init {
        progressDialog = SingleLiveEvent<Boolean>()
        phone = ObservableField("")
        btnSelected = ObservableBoolean(false)
        pre_school = SingleLiveEvent<Boolean>()
        isDateClicked = SingleLiveEvent<Boolean>()
        primary_school = SingleLiveEvent<Boolean>()
        preschoolList = MutableLiveData<List<Preschool?>>()
        schoolType = ObservableField("Pre School")
        password = ObservableField("")
        email = ObservableField("")
        name = ObservableField("")
        location = ObservableField("")
        gender = ObservableField("")
    }

    fun validate(s: CharSequence){
        if(dateMutableLiveData?.value != null && preschoolMutableLiveData?.value != null)
            btnSelected?.set(!phone?.get()!!.equals("")
                    && !name?.get()!!.equals("")
                    // && s.toString().length >= 8
                    && Util.isEmailValid(email?.get()!!)
                    && !location?.get()!!.equals("")
                    && !gender?.get()!!.equals("")
                    && password?.get().toString().length >= 8
                        && !dateMutableLiveData?.value!!.equals("")
                    && !preschoolMutableLiveData?.value?.name!!.equals("")
            )
    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        validate(s)
    }

    fun onNameChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        validate(s)
    }

    fun onLocationChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        validate(s)
    }

    fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        validate(s)
    }

    fun openPreSchoolDialog(){
        pre_school?.value = true
    }

    fun openDateDialog(){
        isDateClicked?.value = true
    }

    fun setPreschoolMutableLiveData(preschool: Preschool) {
        preschoolMutableLiveData.value = preschool
        validate("")
    }

    fun setDateMutableLiveData(date: String) {
        dateMutableLiveData.value = date
        validate(date)
    }

    override fun done(view: View?, isValid: Boolean) {
        val phoneInputView = view as IntlPhoneInput?
        if(phoneInputView!!.isValid){
            phone = ObservableField(phoneInputView.text)
            validate(phoneInputView.text)
        }
    }
    fun getShoolList(view: View?) {
        val tv = view as TextView?
        progressDialog?.value = true
        schoolType = ObservableField(tv!!.text.toString())
        WebServiceClient.client.create(BackEndApi::class.java).GETSCHOOL(type = tv!!.text.toString())
            .enqueue(this)
    }
    fun genderClicked(view: View?){
        val radioButton = view as RadioButton?
        val txt = radioButton?.text.toString()
        gender = ObservableField(txt)
        validate(txt)
    }

    fun getPayload(): String{
        val user = User(name?.get()!!, phone?.get()!!, location?.get()!!,email?.get()!!)
        val preschool = preschoolMutableLiveData?.value
        val registerData = RegisterData()
        registerData.user = user
        registerData.preschool = preschool
        val gson = Gson()
        return gson.toJson(registerData)
    }

    fun register(){
        progressDialog?.value = true
        WebServiceClient.client.create(BackEndApi::class.java).REGISTER(getPayload())
            .enqueue(object: Callback<User?>{
                override fun onFailure(call: Call<User?>?, t: Throwable?) {
                    TODO("Not yet implemented")
                    progressDialog?.value = false
                }

                override fun onResponse(call: Call<User?>?, response: Response<User?>?) {
                    TODO("Not yet implemented")
                    progressDialog?.value = false
                    //navigate to MainActivity
                }
            })
    }


    override fun onResponse(call: Call<List<Preschool?>>?, response: Response<List<Preschool?>>?) {
        progressDialog?.value = false
        Log.e("RESPONSE", response?.body().toString())
        preschoolList?.value = response?.body()
        openPreSchoolDialog()
    }
    override fun onFailure(call: Call<List<Preschool?>>?, t: Throwable?) {
        //don't try this at home... This is for testing
        //if network fails... lets have some dummy..lol
        if(schoolType?.get()!!.equals("Pre School"))
            createPreSchoolDummy()
        else
            createPrimarySchoolDummy()
        progressDialog?.value = false
    }

    fun createPreSchoolDummy(){
        val array: MutableList<Preschool> = ArrayList()
        var preschool = Preschool()
        preschool._id = "kutyufhkjgj"
        preschool.id = 1
        preschool.name = "Reception"
        preschool.icon = R.drawable.ic_person
        array.add(preschool)
        preschool = Preschool()
        preschool._id = "gggggggg"
        preschool.id = 2
        preschool.name = "Cooking"
        preschool.icon = R.drawable.ic_phone
        array.add(preschool)
        preschoolList?.value = array
    }

    fun createPrimarySchoolDummy(){
        val array: MutableList<Preschool> = ArrayList()
        var preschool = Preschool()
        preschool._id = "kutyufhkjgj"
        preschool.id = 3
        preschool.name = "Grade 1"
        preschool.icon = R.drawable.ic_person
        array.add(preschool)
        preschool = Preschool()
        preschool._id = "gggggggg"
        preschool.id = 4
        preschool.name = "Grade 2"
        preschool.icon = R.drawable.ic_phone
        array.add(preschool)
        preschool = Preschool()
        preschool._id = "gggggggguiyty"
        preschool.id = 5
        preschool.name = "Grade 3"
        preschool.icon = R.drawable.ic_phone
        array.add(preschool)
        preschool = Preschool()
        preschool._id = "gggggggguiyty"
        preschool.id = 6
        preschool.name = "Grade 4"
        preschool.icon = R.drawable.ic_phone
        array.add(preschool)
        preschoolList?.value = array
    }


}