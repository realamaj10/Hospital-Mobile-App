package com.rapidzz.hospital_management.models

import java.io.Serializable

data class StaffModel(
    var name: String? = "",
    var email: String? = "",
    var phone: String? = "",
    var address: String? = "",
    var cnic: String? = "",
    var description: String? = "",
    var gender: String? = "",
    var specialization: String? = ""
) : Serializable
