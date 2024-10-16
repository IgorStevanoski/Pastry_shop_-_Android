package com.example.domaci3.userScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.R
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.ImageC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox

fun NavGraphBuilder.products(
    route: String,
    navController: NavHostController,
    onCakesClick: () -> Unit,
    onCupCakesClick: () -> Unit,
) = composable(
    route = route
) {

    ProductsScreen(
        onCakesClick = onCakesClick,
        onCupCakesClick = onCupCakesClick
    )

    TopBar(
        showMenuBar = true,
        navController
    )
}

@Composable
fun ProductsScreen(
    onCakesClick: () -> Unit = {},
    onCupCakesClick: () -> Unit = {},
) {

    val imageHeight = 350.dp

    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            ColumnC(
                content = {

                    TransparentBox()

                    TextCHeadline(paddingValues = paddingValues, text = stringResource(id = R.string.cakes))

                    ImageC(
                        painter = painterResource(id = R.drawable.torte),
                        onClick = { onCakesClick() },
                        imageSize = imageHeight
                    )

                    TextCHeadline(paddingValues = paddingValues, text = stringResource(id = R.string.cupcakes))

                    ImageC(
                        painter = painterResource(id = R.drawable.kolaci),
                        onClick = { onCupCakesClick() },
                        imageSize = imageHeight
                    )

                    TransparentBox()

                }
            )
        },
    )
}

//@Preview
//@Composable
//fun PreviewPromotionsScreen () {
//    PromotionsScreen()
//}