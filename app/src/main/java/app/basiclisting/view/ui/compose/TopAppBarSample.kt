package app.basiclisting.view.ui.compose

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import app.basiclisting.R

/// Toolbar to show header at top of page
@Composable
fun TopAppBarView(title: String, vector: ImageVector, shouldShowBack: Boolean, onBack: () -> Unit) {
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text(text = title, color = colorResource(id = R.color.white))
        },
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            IconButton(onClick = {
                if (shouldShowBack) {
                    onBack()
                }
            }) {
                Icon(vector, null)
            }
        })

}