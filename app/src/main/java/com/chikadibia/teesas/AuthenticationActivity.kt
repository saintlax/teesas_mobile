package com.chikadibia.teesas

import android.content.Context
import com.chikadibia.teesas.util.Util.Companion.authenticationFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chikadibia.teesas.fragments.ForgotPasswordFragment
import com.chikadibia.teesas.fragments.LoginFragment
import com.chikadibia.teesas.fragments.RegisterFragment
import com.chikadibia.teesas.util.Constants

class AuthenticationActivity : AppCompatActivity() {

    var context: Context? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authentication_layout)
        context = this@AuthenticationActivity
        authenticationFragment(this, Constants.FRAGMENT_TO_DIRECTION, LoginFragment(), null)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment is LoginFragment) { //||
            super.onBackPressed()
        } else if(currentFragment is ForgotPasswordFragment || currentFragment is RegisterFragment){
            authenticationFragment(
                this,
                Constants.FRAGMENT_FROM_DIRECTION,
                LoginFragment(),
                null
            )
        } else{
            super.onBackPressed()
        }
    }
}