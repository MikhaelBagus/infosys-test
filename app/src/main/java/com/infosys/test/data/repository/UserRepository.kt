package com.infosys.test.data.repository

import com.infosys.test.data.ApiService
import com.infosys.test.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getUsers(): List<User> = api.getUsers()
}
