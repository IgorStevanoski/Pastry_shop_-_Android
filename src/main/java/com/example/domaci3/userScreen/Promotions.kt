package com.example.domaci3.userScreen

import UserViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.R
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox
import com.example.domaci3.domain.ProductData
import com.example.domaci3.model.product.ProductListViewModel
import com.example.domaci3.ui.theme.darkblue
import com.example.domaci3.ui.theme.lightblue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rs.edu.raf.raf_vezbe3compose.passwords.repository.UserRepository

fun NavGraphBuilder.promotions(
    route: String,
    navController: NavHostController
) = composable(
    route = route
) {

    PromotionsScreen(
        onClick = {
            navController.navigate(route = "product/${it.id}")
        }
    )

    TopBar(
        showMenuBar = true,
        navController
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PromotionsScreen(
    onClick: (ProductData) -> Unit,
) {
    val boxSize = 400.dp
    val promotionType = "Promocija"

    val productListViewModel = viewModel<ProductListViewModel>()
    val state by productListViewModel.state.collectAsState()

    val userViewModel = viewModel<UserViewModel>()
    val userState by userViewModel.state.collectAsState()
    val logged = UserRepository.logged()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val promotions = state.products.filter { it.type == promotionType }
    val pagerState = rememberPagerState(pageCount = {
        promotions.size
    })

    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            ColumnC(
                content = {

                    TransparentBox()

                    TextCHeadline(paddingValues = paddingValues, text = stringResource(id = R.string.promotions))

                    Box(
                        modifier = Modifier
                            .size(boxSize)
                    ) {

                        HorizontalPager(
                            state = pagerState,
                        ) { page ->
                            if (promotions.size > 0) {
                                val imageName = promotions[page].image
                                val imageResId = remember { getImageResourceId(context, imageName) }

                                Image(
                                    painter = painterResource(id = imageResId),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            onClick(promotions[page])
                                        }
                                        .clip(RoundedCornerShape(20))
                                        .border(5.dp, darkblue, RoundedCornerShape(20)),
                                )
                            }
                        }

                        LaunchedEffect(Unit) {
                            while (true) {
                                delay(2000) // Change page every 2 seconds
                                coroutineScope.launch {
                                    val nextPage =
                                        (pagerState.currentPage + 1) % pagerState.pageCount
                                    pagerState.animateScrollToPage(nextPage)
                                }
                            }
                        }

                        Row(
                            Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(paddingValues)
                                .offset(0.dp, 20.dp)
                            ,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(pagerState.pageCount) { iteration ->
                                val color =
                                    if (pagerState.currentPage == iteration) darkblue else lightblue
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                        .size(30.dp)
                                        .clickable {
                                            coroutineScope.launch {
                                                pagerState.animateScrollToPage(iteration) // Page index 1 is the second page
                                            }
                                        }
                                        .border(5.dp, darkblue, CircleShape)
                                )
                            }
                        }

                    }
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