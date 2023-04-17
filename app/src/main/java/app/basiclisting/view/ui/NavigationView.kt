package app.basiclisting.view.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.basiclisting.view.ui.compose.DetailScreen
import app.basiclisting.view.ui.compose.OfferScreen

const val HOME = "home"
const val DETAIL = "detail"
const val OFFER_INFO = "offer"
const val PREF_LOAD = "load"

/// the main navigation graph which is used for jetpack compose navigation

@Composable
fun NavigationView() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) {
            OfferScreen(navController)
        }
        composable(DETAIL) {
            DetailScreen(
                onHome = { navController.popBackStack() }, navHostController = navController
            )
        }
    }
}