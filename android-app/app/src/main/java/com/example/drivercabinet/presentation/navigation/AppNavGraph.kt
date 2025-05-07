import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.drivercabinet.presentation.ui.ConfirmScreen
import com.example.drivercabinet.presentation.ui.LoginScreen
import com.example.drivercabinet.presentation.ui.MainScreen
import com.example.drivercabinet.presentation.ui.RegisterScreen
import com.example.drivercabinet.presentation.viewmodel.AuthViewModel

@Composable
fun AppNavGraph(viewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                viewModel = viewModel,
                onNavigateToConfirm = { email ->
                    navController.navigate("confirm/$email")
                }
            )
        }
        composable(
            route = "confirm/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            ConfirmScreen(
                viewModel = viewModel,
                email = email,
                onSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("main") {
            MainScreen(navController = navController)
        }
    }
}
