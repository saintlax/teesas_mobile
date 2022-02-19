package com.chikadibia.teesas.fragments

import android.content.Context
import com.chikadibia.teesas.dialogs.Dialogg
import com.chikadibia.teesas.interfaces.GetStringCallback
import com.chikadibia.teesas.interfaces.GetPreschoolCallback
import com.chikadibia.teesas.model.Preschool

class Test(var context: Context) {
    var dialog: Dialogg
    fun eat() {
        dialog.datePickerDialog(object : GetStringCallback {
            override fun done(value: String?) {}
        })
    }

    fun doo() {
        dialog.preSchoolDialog(null, object : GetPreschoolCallback {
            override fun done(preschool: Preschool?) {}
        })
    }

    init {
        dialog = Dialogg(context)
    }
}