package com.example.domaci3.userScreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.R
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.ImageC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox
import com.example.domaci3.domain.ProductData
import com.example.domaci3.model.product.ProductListState
import com.example.domaci3.model.product.ProductListViewModel
import com.example.domaci3.ui.theme.darkblue

fun NavGraphBuilder.productList(
    route: String,
    arguments: List<NamedNavArgument>,
    navController: NavController
) = composable(route = route) {navBackStackEntry ->
    val type = navBackStackEntry.arguments?.getString("type")
    val tip = if (type == "cakes") "Torta" else "Kolač"

    val productListViewModel = viewModel<ProductListViewModel>()
    val state by productListViewModel.state.collectAsState()

    ProductListScreen(
        state = state,
        onItemClick = {
            navController.navigate(route = "product/${it.id}")
        },
        type = tip
    )

    TopBar(
        showMenuBar = true,
        navController as NavHostController
    )
}

@Composable
fun ProductListScreen(
    state: ProductListState,
    onItemClick: (ProductData) -> Unit,
    type: String?
) {

    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->

            ProductList(
                paddingValues = paddingValues,
                items = state.products.filter { it.type == type },
                onItemClick = onItemClick,
                type = type
            )
        },
    )
}

@Composable
private fun ProductList(
    items: List<ProductData>,
    paddingValues: PaddingValues,
    onItemClick: (ProductData) -> Unit,
    type: String?
) {
    val scrollState = rememberScrollState()
    val tip = if (type == "Torta") "Torte" else "Kolači"

    ColumnC(
        content = {

            TransparentBox()

            if (type != null) {
                TextCHeadline(paddingValues = paddingValues, text = tip)
            }

            items.forEach {
                Column {
                    key(it.id) {
                        ProductListItem(
                            data = it,
                            onClick = {
                                onItemClick(it)
                            },
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }
    )
}


@Composable
private fun ProductListItem(
    data: ProductData,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    val imageName = data.image
    val imageResId = remember { getImageResourceId(context, imageName) }

    val imageSize = 350.dp

    Box(
        modifier = Modifier
            .size(imageSize)
            .clip(RoundedCornerShape(20))
            .border(5.dp, darkblue, RoundedCornerShape(20))
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent), // Ensure the Card background is transparent
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xB0A2D2FF)),
                    text = data.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

fun getImageResourceId(context: Context, imageName: String): Int {
    return context.resources.getIdentifier(imageName, "drawable", context.packageName)
}