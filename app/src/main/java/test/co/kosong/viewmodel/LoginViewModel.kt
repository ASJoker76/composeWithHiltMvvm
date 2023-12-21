package test.co.kosong.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import test.co.kosong.model.AllState
import test.co.kosong.repository.AuthRepository
import test.co.kosong.utils.Resource
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val application: Application, private val repository: AuthRepository) : AndroidViewModel(application = application) {

    private var _loginState = MutableStateFlow<AllState>(value = AllState())
    val loginState: StateFlow<AllState> = _loginState.asStateFlow()

    fun resetLoginState() {
        _loginState.value = AllState() // Mengatur ulang keadaan LoginState menjadi kosong
    }

    fun loginWithUserNameAndPassword(userName: String, userPassword: String) {
        viewModelScope.launch {
            val user = repository.getUserByUserName(userName)
            if (user != null && user.userPassword == userPassword) {
                // Kredensial valid
                _loginState.value = _loginState.value.copy(isSuccess = "Login Successful!")
            } else {
                // Kredensial tidak valid
                _loginState.value = _loginState.value.copy(isError = "Data not found")
            }
        }
    }
}