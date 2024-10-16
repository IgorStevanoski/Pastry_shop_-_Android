package com.example.domaci3.userScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.R
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.ImageC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TextMediumC
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox

fun NavGraphBuilder.contact(
    route: String,
    navController: NavHostController
) = composable(
    route = route
) {

    ContactScreen(
    )

    TopBar(
        showMenuBar = true,
        navController
    )
}

@Composable
fun ContactScreen(
) {
    val imageHeight = 350.dp
    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            ColumnC(
                content = {

                    TransparentBox()

                    TextCHeadline(text = stringResource(id = R.string.contact))

                    TextMediumC(
                        PaddingValues(0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp),
                        text = stringResource(id = R.string.contact_address) + "\n" + stringResource(id = R.string.contact_address_val)
                    )

                    TextMediumC(PaddingValues(
                        0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp),
                        text = stringResource(id = R.string.contact_phone) + "\n" + stringResource(id = R.string.contact_phone_val))

                    TextMediumC(PaddingValues(
                        0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp),
                        text = stringResource(id = R.string.contact_time) + "\n" + stringResource(id = R.string.contact_time_week))

                    TextMediumC(PaddingValues(
                        0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp),
                        text = stringResource(id = R.string.contact_location))

                    ImageC(painter = painterResource(id = R.drawable.lokacija))

                    TransparentBox()
                }
            )
        },
    )
}

//@Preview
//@Composable
//fun Preview Screen () {
//    Screen()
//}