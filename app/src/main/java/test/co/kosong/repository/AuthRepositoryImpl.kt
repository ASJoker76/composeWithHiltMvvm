package test.co.kosong.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import test.co.kosong.model.User
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun getUserByUserName(userName: String): User? {
        return try {
            val snapshot = firestore.collection("Users").whereEqualTo("userName", userName).get().await()
            val user = snapshot.documents.firstOrNull()?.toObject(User::class.java)
            user
        } catch (e: Exception) {
            null
        }
    }
}