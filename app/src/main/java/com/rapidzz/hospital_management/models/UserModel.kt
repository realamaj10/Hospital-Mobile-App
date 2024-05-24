package com.rapidzz.hospital_management.models

import java.io.Serializable

data class UserModel(
    var name: String? = "",
    var email: String? = "",
    var password: String? = ""
) : Serializable