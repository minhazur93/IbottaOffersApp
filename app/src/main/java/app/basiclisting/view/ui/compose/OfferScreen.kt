package app.basiclisting.view.ui.compose

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.basiclisting.data.entities.Offer
import app.basiclisting.view.ui.DETAIL
import app.basiclisting.view.ui.MainActivity
import app.basiclisting.view.ui.OFFER_INFO
import app.basiclisting.view.ui.PREF_LOAD
import app.basiclisting.view.viewmodel.OfferViewModel
import kotlinx.coroutines.launch
import java.io.IOException

/// this is our home page
@Composable
fun OfferScreen(
    navHostController: NavHostController,
    viewModel: OfferViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val preference = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    val homePageList = remember { mutableStateListOf<Offer>() }
    val offerState = viewModel.offers.observeAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            viewModel.getAllOffers()
        }
    }

    LaunchedEffect(key1 = offerState.value, block = {
        homePageList.apply {
            clear()
            addAll(offerState.value ?: arrayListOf())
        }
    })

    if (homePageList.isEmpty()) {
        ShowLoader()
        LaunchedEffect(key1 = Unit) {

            /// use preference as a check to avoid repeatition of insertion of data
            if (preference.getBoolean(PREF_LOAD, false).not()) {
                preference.edit().putBoolean(PREF_LOAD, true).commit()
                viewModel.saveOffersInfo(getJsonDataFromAsset(context))
            }
        }
    } else {
        OfferPageContent(ArrayList(homePageList)) { offer ->
            navHostController.currentBackStackEntry?.savedStateHandle?.set(OFFER_INFO, offer)
            navHostController.navigate(DETAIL)
        }
    }

}


@Composable
fun ShowLoader() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

fun getJsonDataFromAsset(context: Context): String? {
    return try {
        context.assets.open(MainActivity.fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        null
    }
}