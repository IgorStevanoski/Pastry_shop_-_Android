package com.example.domaci3.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.domaci3.R
import com.example.domaci3.navigation.menuFunctions

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopBar(
    showMenuBar: Boolean = false,
    navHostController: NavHostController
) {

    val imageHeight = 150.dp
    val barHeight = 50.dp

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .height(imageHeight),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                if (showMenuBar)
                    MenuBar(
                        navHostController = navHostController,
                        barHeight = barHeight
                    )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(barHeight)
                        .background(colorResource(id = R.color.darkblue))
                )

                if (showMenuBar) {
                    Image(
                        painter = painterResource(id = R.drawable.logobar),
                        contentDescription = stringResource(id = R.string.welcome),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(imageHeight)
                            .background(Color.Transparent)
                            .align(Alignment.Center)
                            .clickable { menuFunctions.onPromotionsClick(navHostController) }
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.logobar),
                        contentDescription = stringResource(id = R.string.welcome),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(imageHeight)
                            .background(Color.Transparent)
                            .align(Alignment.Center)
                            .clickable { menuFunctions.onLogoutClick(navHostController) }
                    )
                }

            }
        }

    )

}


//@Preview
//@Composable
//fun PreviewTopBar() {
//    TopBar(
//    )
//}