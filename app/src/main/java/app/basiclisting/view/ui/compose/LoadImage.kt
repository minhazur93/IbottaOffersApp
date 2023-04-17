package app.basiclisting.view.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import coil.compose.rememberAsyncImagePainter

/// Main image loading component to fetch image against url
@Composable
fun BoxScope.LoadImage(url: String) {
    val isDone = remember { mutableStateOf(false) }
    Image(
        painter = rememberAsyncImagePainter(url,
            onError = {
                isDone.value = true
            }, onSuccess = {
                isDone.value = true
            }, onLoading = {
            }),
        contentDescription = "",
        modifier = Modifier
            .align(Alignment.Center)
            .fillMaxSize()
            .height(dimensionResource(id = com.intuit.sdp.R.dimen._80sdp))
    )

    if (isDone.value.not()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }

}
