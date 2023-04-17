package app.basiclisting.view.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.basiclisting.R
import app.basiclisting.data.entities.Offer
import app.basiclisting.view.ui.OFFER_INFO
import app.basiclisting.view.viewmodel.OfferViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(navHostController: NavHostController, onHome: () -> Unit) {

    /// receive offer value pass from previous page
    val offer = navHostController.previousBackStackEntry?.savedStateHandle?.get<Offer>(OFFER_INFO)

    /// Detail screen main container
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {

        TopAppBarView("Offer Details", Icons.Filled.ArrowBack, true, onHome)
        offer?.let {
            DetailContent(it)
        }
    }
}

@Composable
fun DetailContent(offer: Offer, viewModel: OfferViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        offer.url?.let {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                LoadImage(it)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        /// name section
        Row {
            Label(name = "Name: ")
            Spacer(modifier = Modifier.width(10.dp))
            DetailValue(offer.name)
        }

        /// use for margin
        Spacer(modifier = Modifier.height(15.dp))

        /// Description section
        Row {
            Label(name = stringResource(R.string.desc))
            Spacer(modifier = Modifier.width(10.dp))
            DetailValue(offer.description)
        }

        Spacer(modifier = Modifier.height(15.dp))

        /// Terms section
        Row {
            Label(name = stringResource(R.string.terms))
            Spacer(modifier = Modifier.width(10.dp))
            DetailValue(offer.terms)
        }

        Spacer(modifier = Modifier.height(15.dp))

        /// Currency section
        Row {
            Label(name = "Currency\nValues: ")
            Spacer(modifier = Modifier.width(10.dp))
            DetailValue(offer.current_value)
        }

        val isNotFav = remember { mutableStateOf(offer.isFavourite) }

        // add button to mark fav/not fav
        Button(
            onClick = {
                isNotFav.value = isNotFav.value.not()
                scope.launch(Dispatchers.IO) {
                    /// update db state
                    viewModel.updateStatus(if (isNotFav.value) 1 else 0, offer.id)
                }

            },
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(id = R.color.white),
            )
        ) {
            ButtonText(name = if (isNotFav.value) stringResource(R.string.mark_un_fav) else stringResource(
                            R.string.mark_fav)
                        )
        }
    }
}

/// TextView for button
@Composable
private fun ButtonText(name: String) {
    Text(
        text = name,
        fontFamily = FontFamily(Font(R.font.bold)),
        fontSize = 14.sp,
        color = colorResource(id = R.color.white)
    )
}

/// Label TextView
@Composable
private fun Label(name: String) {
    Text(
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .widthIn(80.dp)
            .wrapContentHeight(),
        text = name,
        fontFamily = FontFamily(Font(R.font.bold)),
        fontSize = 14.sp,
        color = colorResource(id = R.color.bold_color)
    )
}


/// label value TextView
@Composable
private fun DetailValue(value: String) {
    Text(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        text = value,
        fontFamily = FontFamily(Font(R.font.regular)),
        fontSize = 14.sp,
        color = colorResource(id = R.color.bold_color)
    )
}

