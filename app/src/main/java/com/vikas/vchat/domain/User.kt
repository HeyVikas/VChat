package com.vikas.vchat.domain

data class User(
    val id : String? = null,
    val name: String,
    val email: String,
    val bio : String,
    val gender: Gender
){
    constructor(): this(null, "", "", "", Gender.Male)
}
