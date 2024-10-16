package com.example.domaci3.userScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.R
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TextMediumC
import com.example.domaci3.components.TextSmallC
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox
import com.example.domaci3.domain.BasketData
import com.example.domaci3.ui.theme.darkbrown
import rs.edu.raf.raf_vezbe3compose.passwords.repository.UserRepository

fun NavGraphBuilder.messages(
    route: String,
    navController: NavHostController
) = composable(
    route = route
) {

    MessagesScreen(
    )

    TopBar(
        showMenuBar = true,
        navController
    )
}

@Composable
fun MessagesScreen(
) {
    val logged = UserRepository.logged()
    val user = UserRepository.getUserByUsername(logged)
    val messages = user?.messages?.asReversed()

    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            ColumnC(
                content = {

                    TransparentBox()

                    TextCHeadline(paddingValues = paddingValues, text = stringResource(id = R.string.messages))

                    messages?.forEach() {
                        Column(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            content = {

                                TextMediumC(
                                    paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp),
                                    text = stringResource(id = R.string.messages_order))
                                TextSmallC(
                                    paddingValues = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
                                    text = "" + it.messageId)

                                TextMediumC(
                                    paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp),
                                    text = stringResource(id = R.string.messages_price))
                                TextSmallC(
                                    paddingValues = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
                                    text = "" + it.price + ".0 din")

                                TextMediumC(
                                    paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp),
                                    text = stringResource(id = R.string.messages_date))
                                TextSmallC(
                                    paddingValues = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
                                    text = it.date)

                                TextMediumC(
                                    paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp),
                                    text = stringResource(id = R.string.messages_status))
                                TextSmallC(
                                    paddingValues = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
                                    text = it.status)

                                Divider(
                                    color = darkbrown,
                                    thickness = 5.dp,
                                    modifier = Modifier
                                        .width(350.dp)
                                        .padding(paddingValues)
                                )
                            })
                    }

                    if (messages != null) {
                        if (messages.isEmpty()){
                            TextMediumC(
                                paddingValues = paddingValues,
                                text = stringResource(id = R.string.messages_empty))
                        }
                    }

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