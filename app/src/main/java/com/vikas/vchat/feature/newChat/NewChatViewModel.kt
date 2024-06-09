package com.vikas.vchat.feature.newChat

import com.streamliners.base.BaseViewModel
import com.streamliners.base.ext.execute
import com.streamliners.base.taskState.load
import com.streamliners.base.taskState.taskStateOf
import com.vikas.vchat.data.LocalRepo
import com.vikas.vchat.data.UserRepo
import com.vikas.vchat.domain.User

class NewChatViewModel(
    private val userRepo: UserRepo,
    private val localRepo: LocalRepo
):BaseViewModel() {

val userListTask = taskStateOf<List<User>>()

    fun start() {
        execute {
            val user = localRepo.getLoggedInUser()
            userListTask.load {
                userRepo.getAllUser().filter { it.id != user.id }
            }
        }
    }
}