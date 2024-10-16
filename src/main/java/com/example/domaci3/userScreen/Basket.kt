package com.example.domaci3.userScreen

import android.annotation.SuppressLint
import android.os.Message
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.R
import com.example.domaci3.components.ButtonC
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.ImageC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TextMediumC
import com.example.domaci3.components.TextSmallC
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox
import com.example.domaci3.domain.BasketData
import com.example.domaci3.domain.MessageData
import com.example.domaci3.ui.theme.darkbrown
import rs.edu.raf.raf_vezbe3compose.passwords.repository.UserRepository
import java.text.SimpleDateFormat
import java.util.Date

fun NavGraphBuilder.basket(
    route: String,
    navController: NavHostController,
    context: ComponentActivity
) = composable(
    route = route
) {

    BasketScreen(
        context
    )

    TopBar(
        showMenuBar = true,
        navController
    )
}

@SuppressLint("SimpleDateFormat")
@Composable
fun BasketScreen(
    context: ComponentActivity
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val imageHeight = 200.dp
    val context = LocalContext.current

    val logged = UserRepository.logged()
    val user = UserRepository.getUserByUsername(logged)

    val basket = mutableListOf<BasketData>()
    if (user != null) {
        basket.addAll(user.basket)
    }

    var price = 0
    for (b in basket) {
        price += b.price
    }
    var totalPrice by remember { mutableIntStateOf(price) }

    val message = stringResource(id = R.string.basket_message)

    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            ColumnC(
                content = {

                    TransparentBox()

                    TextCHeadline(paddingValues = paddingValues, text = stringResource(id = R.string.basket))

                    basket.forEach() {
                        Column(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            content = {

                                Row(
                                    modifier = Modifier
                                        .background(Color.Transparent)
                                        .fillMaxWidth(),
                                    content = {
                                        Column(
                                            modifier = Modifier
                                                .background(Color.Transparent)
                                                .width(screenWidth / 2),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center,
                                            content = {
                                                val imageName = it.image
                                                val imageResId =
                                                    remember {
                                                        getImageResourceId(
                                                            context,
                                                            imageName
                                                        )
                                                    }

                                                ImageC(
                                                    painter = painterResource(id = imageResId),
                                                    imageSize = imageHeight,
                                                    paddingValues = PaddingValues(
                                                        0.dp,
                                                        0.dp,
                                                        0.dp,
                                                        paddingValues.calculateBottomPadding()
                                                    )
                                                )

//                                                Image(
//                                                    painter = painterResource(id = imageResId),
//                                                    contentDescription = stringResource(id = R.string.welcome),
//                                                    contentScale = ContentScale.Fit,
//                                                    modifier = Modifier
//                                                        .size(imageHeight)
//                                                        .background(Color.Transparent)
//                                                        .padding(
//                                                            0.dp,
//                                                            0.dp,
//                                                            0.dp,
//                                                            paddingValues.calculateBottomPadding()
//                                                        ),
//                                                )

                                            })
                                        Column(
                                            modifier = Modifier
                                                .background(Color.Transparent)
                                                .width(screenWidth / 2),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            content = {
                                                TextSmallC(
                                                        paddingValues = paddingValues,
                                                        text = it.productName,
                                                )
                                                TextSmallC(
                                                        paddingValues = paddingValues,
                                                        text = "" + it.price + ".0 din",
                                                )
                                                TextSmallC(
                                                        paddingValues = paddingValues,
                                                        text = stringResource(id = R.string.product_count) + it.count,
                                                )
                                            })

                                    }
                                )

                                ButtonC(onClick = {
                                    basket.remove(it)
                                    UserRepository.updateUserBasket(logged, basket)
                                    totalPrice -= it.price
                                },
                                    text = stringResource(id = R.string.basket_remove),
                                    paddingValues = paddingValues)

                                Divider(
                                    color = darkbrown,
                                    thickness = 5.dp,
                                    modifier = Modifier
                                        .width(350.dp)
                                        .padding(paddingValues)
                                )

//                                Button(onClick = {
//                                    basket.remove(it)
//                                    UserRepository.updateUserBasket(logged, basket)
//                                    totalPrice -= it.price
//                                }) {
//                                    Text(
//                                        modifier = Modifier
//                                            .padding(paddingValues),
//                                        text = stringResource(id = R.string.basket_remove),
//                                        style = MaterialTheme.typography.bodySmall
//                                    )
//                                }

                            })

                    }

                    if (totalPrice > 0) {


                        TextMediumC(paddingValues = PaddingValues(0.dp,0.dp,0.dp,0.dp), text = stringResource(id = R.string.basket_totalPrice))

                        TextSmallC(paddingValues = PaddingValues(0.dp,0.dp,0.dp,0.dp), text = "$totalPrice.0 din",)

                        ButtonC(onClick = {
                            val sdf = SimpleDateFormat("HH:mm dd.MM.yyyy.")
                            val currentDateAndTime = sdf.format(Date())

                            basket.clear()
                            UserRepository.updateUserBasket(logged, basket)
                            UserRepository.updateUserMessage(
                                logged,
                                MessageData(
                                    price = totalPrice,
                                    date = currentDateAndTime,
                                    status = "Čeka se",
                                ))
                            totalPrice = 0


                            val duration = Toast.LENGTH_SHORT
                            val toast = Toast.makeText(context, message, duration)
                            toast.show()
                        },
                            text = stringResource(id = R.string.basket_order),
                            paddingValues = paddingValues)

                    } else {
                        TextSmallC(paddingValues = paddingValues, text = stringResource(id = R.string.basket_empty))
                    }

                    TransparentBox()
                }
            )
        },
    )
}
