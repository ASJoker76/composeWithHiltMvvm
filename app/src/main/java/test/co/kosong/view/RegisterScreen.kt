package test.co.kosong.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import test.co.kosong.R
import test.co.kosong.routes.Routes
import test.co.kosong.viewmodel.HomeViewModel
import test.co.kosong.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavHostController, registerViewModel: RegisterViewModel = hiltViewModel()) {

    val homeViewModel: HomeViewModel = hiltViewModel()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        //Image(modifier = Modifier.fillMaxSize(), painter = painterResource(id = R.drawable.register), contentDescription = "Register")
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AddNewUserOutlinedText(value = registerViewModel.userName, onValueChanged = { registerViewModel.userName = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next), label = {
                Text(
                    text = "Name"
                )
            })
            Spacer(modifier = Modifier.height(height = 7.dp))
            AddNewUserOutlinedText(value = registerViewModel.userAge, onValueChanged = { registerViewModel.userAge = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next), label = {
                Text(
                    text = "Age"
                )
            })
            Spacer(modifier = Modifier.height(height = 7.dp))
            AddNewUserOutlinedText(value = registerViewModel.userOccupation, onValueChanged = { registerViewModel.userOccupation = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done), label = {
                Text(
                    text = "Occupation"
                )
            })
            Spacer(modifier = Modifier.height(height = 7.dp))
            AddNewUserOutlinedText(
                value = registerViewModel.userPassword,
                onValueChanged = { registerViewModel.userPassword = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                label = {
                    Text(
                        text = "Password"
                    )
                }
            )
            Spacer(modifier = Modifier.height(height = 21.dp))
            Button(onClick = {
                registerViewModel.addUserDetails()
                homeViewModel.getUserDetails()
                navController.navigate(Routes.HomeKasir.name)
            }) {
                Text(text = "Add")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewUserOutlinedText(
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    label: @Composable (() -> Unit)?
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        keyboardOptions = keyboardOptions,
        label = label
    )
}