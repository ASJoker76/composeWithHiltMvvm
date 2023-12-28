package test.co.kosong.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import test.co.kosong.R
import test.co.kosong.routes.Routes
import test.co.kosong.utils.C
import test.co.kosong.viewmodel.HomeViewModel
import test.co.kosong.viewmodel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel(), navController: NavHostController) {

    val homeViewModel: HomeViewModel = hiltViewModel()

    var userName  by rememberSaveable { mutableStateOf("") }
    var userPassword  by rememberSaveable { mutableStateOf("") }

    val loginState by loginViewModel.loginState.collectAsState() // Mendapatkan loginState dari LoginViewModel
    val coroutineScope = rememberCoroutineScope()

    // Menampilkan pesan kesalahan jika login gagal
    if (loginState.isError.isNotBlank()) {
        AlertDialog(
            onDismissRequest = {
                loginViewModel.resetLoginState() // Reset state untuk menampilkan pesan kesalahan kembali di masa mendatang
            },
            title = {
                Text(text = "Error")
            },
            text = {
                Text(text = loginState.isError)
            },
            confirmButton = {
                Button(
                    onClick = {
                        loginViewModel.resetLoginState()
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }

    if (loginState.isSuccess.isNotBlank()) {
        if(loginState.isStatus == C.ROLE.KASIR){
            navController.navigate(Routes.HomeKasir.name)
        } else {
            navController.navigate(Routes.HomeAdmin.name)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        //Image(modifier = Modifier.fillMaxSize(), painter = painterResource(id = R.drawable.register), contentDescription = "Register")
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RegisterOutlinedText(value = userName, onValueChanged = { userName = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next), label = {
                Text(
                    text = "User Name"
                )
            })
            Spacer(modifier = Modifier.height(height = 7.dp))
            RegisterOutlinedText(value = userPassword, onValueChanged = { userPassword = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next), label = {
                Text(
                    text = "Password"
                )
            }, applyVisualTransformation = true)
            Spacer(modifier = Modifier.height(height = 21.dp))
            Button(onClick = {
                coroutineScope.launch {
                    loginViewModel.loginWithUserNameAndPassword(userName, userPassword)
                }
            }) {
                Text(text = "Login")
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterOutlinedText(
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    label: @Composable (() -> Unit)?,
    applyVisualTransformation: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        keyboardOptions = keyboardOptions,
        label = label,
        visualTransformation = if (applyVisualTransformation) PasswordVisualTransformation() else VisualTransformation.None
    )
}

// To mask the password entry using the * character, use the following function:
private class PasswordVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString("*".repeat(text.text.length)),

            /**
             * [OffsetMapping.Identity] is a predefined [OffsetMapping] that can be used for the
             * transformation that does not change the character count.
             */

            /**
             * [OffsetMapping.Identity] is a predefined [OffsetMapping] that can be used for the
             * transformation that does not change the character count.
             */
            OffsetMapping.Identity
        )
    }
}