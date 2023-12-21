package test.co.kosong.repository

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import test.co.kosong.model.User
import test.co.kosong.viewmodel.RegisterViewModel

class DatabaseRepositoryImpl : DatabaseRepository {
    @SuppressLint("SuspiciousIndentation")
    override fun addUserDetails(registerViewModel: RegisterViewModel, application: Application) {



        val dB: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbUsers: CollectionReference = dB.collection("Users")

        val users = User(userName = registerViewModel.userName, userAge = registerViewModel.userAge, userOccupation = registerViewModel.userOccupation, userPassword = registerViewModel.userPassword)

        dbUsers.add(users).addOnSuccessListener {
            Toast.makeText(application, "User added successfully!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Toast.makeText(application, "Exception: $e", Toast.LENGTH_SHORT).show()
        }
    }
}