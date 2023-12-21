package test.co.kosong.repository

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import test.co.kosong.model.User
import test.co.kosong.utils.Resource

interface AuthRepository {

    suspend fun getUserByUserName(userName: String): User?


}