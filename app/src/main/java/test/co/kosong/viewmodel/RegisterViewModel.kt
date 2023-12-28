package test.co.kosong.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

import dagger.hilt.android.lifecycle.HiltViewModel
import test.co.kosong.model.User
import test.co.kosong.module.NetworkModule
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val application: Application): AndroidViewModel(application = application) {

    val repository = NetworkModule.providesDatabaseRepositoryImpl()

    var userName by  mutableStateOf("")

    var userAge by mutableStateOf("")

    var userOccupation by mutableStateOf("")

    var userPassword by  mutableStateOf("")

    fun addUserDetails() {
        val dB: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbUsers: CollectionReference = dB.collection("Users")

        val users = User(userName = userName, userAge = userAge, userOccupation = userOccupation, userPassword = userPassword, userRole = 0)

        dbUsers.add(users).addOnSuccessListener {
            Toast.makeText(application, "User added successfully!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Toast.makeText(application, "Exception: $e", Toast.LENGTH_SHORT).show()
        }
    }

}