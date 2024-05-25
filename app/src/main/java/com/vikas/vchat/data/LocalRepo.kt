package com.vikas.vchat.data

import com.vikas.vchat.helper.DataStoreUtil

class LocalRepo (
    val dataStoreUtil: DataStoreUtil
) {
    suspend fun onLoggedIn() {
    dataStoreUtil.setData("isLoggedIn", true)
}
    suspend fun isLoggedIn(): Boolean {
    return dataStoreUtil.getData<Boolean>("isLoggedIn") ?: false
}
}
