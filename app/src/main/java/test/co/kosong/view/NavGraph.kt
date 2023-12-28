package test.co.kosong.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import test.co.kosong.routes.Routes

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.InitialScreen.name) {
        composable(route = Routes.InitialScreen.name) {
            InitialScreen(navController = navController)
        }
        composable(route = Routes.Register.name) {
            RegisterScreen(navController = navController)
        }

        composable(route = Routes.Login.name) {
            LoginScreen(navController = navController)
        }

        composable(route = Routes.HomeAdmin.name) {
            HomeAdminScreen(navController = navController)
        }

        composable(route = Routes.HomeKasir.name) {
            HomeKasirScreen(navController = navController)
        }

        composable(route = Routes.ManagementUser.name) {
            ManagementUserScreen(navController = navController)
        }
    }
}