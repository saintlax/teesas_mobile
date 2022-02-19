package com.chikadibia.teesas.interfaces

import com.chikadibia.teesas.model.Preschool

interface GetPreschoolCallback {
    fun done(preschool: Preschool?)
}