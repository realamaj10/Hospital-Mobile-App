package com.rapidzz.hospital_management.utils

import com.rapidzz.hospital_management.R
import com.rapidzz.hospital_management.models.HomeModel

object AppConstant {
    fun getDasBoard(): ArrayList<HomeModel> {
        val list = ArrayList<HomeModel>()
        list.add(HomeModel("Doctors", R.drawable.ic_doc))
        list.add(HomeModel("Patients", R.drawable.ic_patient))
        list.add(HomeModel("Other Staff", R.drawable.ic_staff))
        list.add(HomeModel("Laboratory", R.drawable.ic_lab))
        return list
    }
}