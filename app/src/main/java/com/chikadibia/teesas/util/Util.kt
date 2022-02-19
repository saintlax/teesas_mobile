package com.chikadibia.teesas.util

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.chikadibia.teesas.AuthenticationActivity
import com.chikadibia.teesas.R
import java.util.regex.Pattern

class Util {

    companion object {
        fun isEmailValid(email: String): Boolean {
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }

        fun authenticationFragment(context: Context, direction: Int, fragment: Fragment, bundle: Bundle?) {
            if (bundle != null) {
                fragment.arguments = bundle
            }
            val fragmentTransaction =
                (context as FragmentActivity).supportFragmentManager.beginTransaction()
            if (direction == Constants.FRAGMENT_TO_DIRECTION) fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            if (direction == Constants.FRAGMENT_FROM_DIRECTION) fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_left,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_left
            )
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }
    }


}