package com.example.domaci3.userScreen

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.MainActivity
import com.example.domaci3.R
import com.example.domaci3.components.ButtonC
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.ImageC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TextFieldC
import com.example.domaci3.components.TextMediumC
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox
import com.example.domaci3.domain.BasketData
import com.example.domaci3.domain.CommentData
import com.example.domaci3.domain.ProductData
import rs.edu.raf.raf_vezbe3compose.passwords.repository.ProductRepository
import rs.edu.raf.raf_vezbe3compose.passwords.repository.UserRepository
import java.util.UUID


private val topBarContainerColor = Color.LightGray.copy(alpha = 0.5f)

fun NavGraphBuilder.productDetails(
    route: String,
    arguments: List<NamedNavArgument>,
    navController: NavController,
    context: ComponentActivity
) = composable(
    route = route,
    arguments = arguments,
) { navBackStackEntry ->
    val dataId = navBackStackEntry.arguments?.getString("dataId")

    val data = if (dataId != null) {
        ProductRepository.getProductById(id = dataId)
    } else {
        ProductData(
            id = UUID.randomUUID().toString(),
            type = "Torta",
            name = "Torta 1",
            description = "Najukusnija torta ikad.",
            price = 1000,
            composition = "mleko, so",
            image = "/images/torta1.png",
            comments = listOf(CommentData(username = "neko", text = "Odlično!"))
        )
    }

    if (data != null) {
        ProductScreen(
            data = data,
            onUpdate = {
                ProductRepository.updateOrInsertProduct(
                    id = it.id,
                    data = it,
                )
//                navController.popBackStack()
            },
            onBasketAdd = {
                val logged = UserRepository.logged()

                UserRepository.updateUserBasketItem(
                    username = logged,
                    data = it
                )

                val message = "Uspešno dodato."
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, message, duration)
                toast.show()
            }
        )
    }

    TopBar(
        showMenuBar = true,
        navController as NavHostController
    )
}

@Composable
fun ProductScreen(
    data: ProductData,
    onUpdate: (ProductData) -> Unit,
    onBasketAdd: (basketData: BasketData) -> Unit,
) {
    val imageHeight = 350.dp
    var count by remember { mutableStateOf(1) }

    val context = LocalContext.current
    val imageName = data.image
    val imageResId = remember { getImageResourceId(context, imageName) }

    var comments = mutableListOf<CommentData>()
    var comment by remember { mutableStateOf(TextFieldValue("")) }

    for (c in data.comments) {
        comments.add(c)
    }

    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            ColumnC{

                TransparentBox()

                ImageC(
                    painter = painterResource(id = imageResId),
                    paddingValues = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp))

                TextMediumC(
                    paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding() / 2, 0.dp, 0.dp),
                    text = stringResource(id = R.string.product_name) + " " + data.name
                )

                TextMediumC(
                    paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding() / 2, 0.dp, 0.dp),
                    text = stringResource(id = R.string.product_description) + " " + data.description
                )

                TextMediumC(
                    paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding() / 2, 0.dp, 0.dp),
                    text = stringResource(id = R.string.product_price) + " " + data.price
                )

                TextMediumC(
                    paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding() / 2, 0.dp, 0.dp),
                    stringResource(id = R.string.product_composition) + " " + data.composition
                )

                if ( data.type != "Promocija" ){
                    Row {

                        TextMediumC(
                            paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding() / 2, 0.dp, 0.dp),
                            stringResource(id = R.string.product_count) + " " + count
                        )

                        ButtonC(
                            onClick = { if (count > 1) count-- },
                            text = stringResource(id = R.string.product_dec),
                            paddingValues = paddingValues,
                            buttonWidth = 65.dp)

                        ButtonC(
                            onClick = { count++ },
                            text = stringResource(id = R.string.product_inc),
                            paddingValues = paddingValues,
                            buttonWidth = 65.dp)
                    }
                }

                ButtonC(
                    onClick = {
                        val basketData = BasketData(
                            productName = data.name,
                            price = data.price * count,
                            count = count,
                            image = data.image
                        )

                        onBasketAdd(basketData)
                        count = 1
                    },
                    text = stringResource(id = R.string.product_add),
                    paddingValues = paddingValues)

                TransparentBox(height = 80.dp)

                if ( data.type != "Promocija" ){

                    TextCHeadline(
                        paddingValues = paddingValues,
                        stringResource(id = R.string.product_comments)
                    )

                    comments.forEach {
                        Column {
                            key(it.username) {

                                TextMediumC(paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding() / 2, 0.dp, 0.dp),
                                    it.username + ": " + it.text)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    TextMediumC(
                        paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp),
                        stringResource(id = R.string.product_add_comment)
                    )

                    TextFieldC(value = comment, lines = 3, isPassword = true) {
                        comment = it
                    }

                    ButtonC(onClick = {
                        if (comment.text.length > 0) {

                            val user = UserRepository.getUserByUsername(UserRepository.logged())

//                            comments.add(CommentData(UserRepository.logged(), comment.text))
                            if (user != null) {
                                comments.add(CommentData(user.firstname + " " + user.lastname, comment.text))
                            }

                            val editedData = ProductData(
                                id = data.id,
                                type = data.type,
                                name = data.name,
                                description = data.description,
                                price = data.price,
                                composition = data.composition,
                                image = data.image,
                                comments = comments,
                            )

                            onUpdate(editedData)
                            comment = TextFieldValue("")
                        }},
                        text = stringResource(id = R.string.enter),
                        paddingValues = paddingValues)

                    TransparentBox()
                }
            }
        },
    )
}
