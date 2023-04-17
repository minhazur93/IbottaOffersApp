package app.basiclisting.view.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.basiclisting.R
import app.basiclisting.data.entities.Offer


@Composable
fun OfferPageContent(offersList: List<Offer>, onClick: (Offer) -> Unit) {
    Column {
        TopAppBarView("Offers", Icons.Filled.Home, false) {}
        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._19sdp)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)),
            content = {
                itemsIndexed(offersList) { _, item ->
                    OfferCell(item, onClick)
                }
            })
    }
}


@Composable
fun OfferCell(offer: Offer, onClick: (Offer) -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .clickable {
                    onClick(offer)
                }
        ) {
            BackgroundRectangle(offer)
            Currency(offer.current_value)
            Name(offer.name)
        }

        if (offer.isFavourite) {
            Image(
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.TopStart),
                painter = painterResource(id = R.drawable.fav_icon),
                contentDescription = ""
            )
        }
    }

}

@Composable
fun Name(name: String) {
    Text(
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(top = dimensionResource(id = com.intuit.sdp.R.dimen._2sdp)),
        text = name,
        fontFamily = FontFamily(Font(R.font.regular)),
        fontSize = 12.sp,
        color = colorResource(id = R.color.bold_color),
        maxLines = 1
    )
}

@Composable
fun Currency(value: String) {
    Text(
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(top = dimensionResource(id = com.intuit.sdp.R.dimen._5sdp)),
        text = value,
        fontFamily = FontFamily(Font(R.font.bold)),
        fontSize = 12.sp,
        color = colorResource(id = R.color.bold_color)
    )
}

@Composable
fun BackgroundRectangle(offer: Offer) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.light_gray),
                shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._3sdp))
            )
            .heightIn(dimensionResource(id = com.intuit.sdp.R.dimen._80sdp))
            .wrapContentHeight()
            .padding(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp))
    ) {
        LoadImage(url = offer.url ?: "")
    }
}


/// this method is not in used. it just to preview layout at the time of writing code
@Preview(showBackground = true)
@Composable
fun OfferCellPreview() {
    OfferCell(
        offer = Offer(
            "12",
            "asdasd",
            "Scotch-Brite® Scrub Dots Non-Scratch Scrub Sponges",
            "Any variety - 2 ct. pack or larger",
            "Rebate valid on Scotch-Brite® Scrub Dots Non-Scratch Scrub Sponges for any variety, 2 ct. pack or larger.",
            "0.75 Cash Back", false
        )
    ) {}
}