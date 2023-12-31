package test.co.kosong

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import test.co.kosong.ui.theme.KosongTheme
import test.co.kosong.viewmodel.MainVM


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainVM by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            KosongTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()

                // Set up the navigation components
                NavHost(
                    navController = navController,
                    startDestination = "Dashboard"
                ) {
                    composable("Dashboard") {
                        Dashboard(navController = navController, viewModel)
                    }
                    composable("Profile") {
                        Profile(navController = navController)
                    }
                    // Add more screens here
                }

//                    Column {
//                        MyToolbar()
//                        //Greeting("Android")
//                        ListView(viewModel)
//                        Footer(navigate)
//                    }
            }
        }
    }
}

@Composable
fun Dashboard(navController: NavHostController, viewModel: MainVM) {
    Column() {
        MyToolbar()
        Footer(navController)
        ListView(viewModel)
    }
}

@Composable
fun Profile(navController: NavHostController) {
    Column {
        Greeting("Android")
    }
}

@Composable
fun ListView(viewModel: MainVM) {
    val holidays by remember { viewModel.holidays }
    val error by remember { viewModel.error }

    Log.d("ERROR 00", "$error")

    // Show data
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(holidays.size) { index ->
            Text("Keterangan: ${holidays[index].keterangan}")
            Text("Tanggal: ${holidays[index].tanggal}")
            Text("Status: ${if (holidays[index].is_cuti) "Libur" else "Kerja"}")
            Divider() // Untuk memisahkan item
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyToolbar() {
    TopAppBar(title = { Text("My App") }, actions = {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "My image"
        )
    })
}

@Composable
fun Footer(navigate: NavHostController) {
    BottomAppBar {
        Button(onClick = {
            navigate.navigate("Profile")
        }) {

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KosongTheme {
        Greeting("Android")
    }
}