package com.example.domaci3.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.domaci3.navigation.menuFunctions
import com.example.domaci3.ui.theme.Darkblue
import com.example.domaci3.ui.theme.Lightblue
import com.example.domaci3.ui.theme.darkblue
import com.example.domaci3.ui.theme.lightblue
import com.example.domaci3.welcome.WelcomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBar(
    navHostController: NavHostController,
    barHeight: Dp
) {
    var showMenu by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Darkblue)  // Apply border here
            .height(barHeight * 2)
    ) {

        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize(),
            horizontalAlignment = Alignment.End,
            content = {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Darkblue)
                        .height(barHeight)
                        .background(colorResource(id = com.example.domaci3.R.color.darkblue))
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Darkblue)
                        .height(barHeight)
                        .background(colorResource(id = com.example.domaci3.R.color.lightblue)),
                    content = {

                        IconButton(
                            modifier = Modifier.align(alignment = Alignment.BottomEnd),
                            onClick = { showMenu = true }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = ""
                            )
                        }
                    }
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.TopEnd)
                        .border(2.dp, Color.Red),
                    content = {
                        DropdownMenu(
                            modifier = Modifier.background(darkblue),
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                modifier = Modifier
                                    .background(darkblue),
                                onClick = {
                                    menuFunctions.onPromotionsClick(navHostController); showMenu =
                                    false
                                },
                                text = { Text(text = "Promocije", style = MaterialTheme.typography.labelSmall) },
                            )

                            Divider(color = lightblue, thickness = 1.dp)

                            DropdownMenuItem(
                                modifier = Modifier
                                    .background(darkblue),
                                onClick = {
                                    menuFunctions.onProductsClick(navHostController); showMenu =
                                    false
                                },
                                text = { Text(text = "Proizvodi", style = MaterialTheme.typography.labelSmall) }
                            )

                            Divider(color = lightblue, thickness = 1.dp)

                            DropdownMenuItem(
                                modifier = Modifier
                                    .background(darkblue),
                                onClick = {
                                    menuFunctions.onContactClick(navHostController); showMenu =
                                    false
                                },
                                text = { Text(text = "Kontakt", style = MaterialTheme.typography.labelSmall) }
                            )

                            Divider(color = lightblue, thickness = 1.dp)

                            DropdownMenuItem(
                                modifier = Modifier
                                    .background(darkblue),
                                onClick = {
                                    menuFunctions.onBasketClick(navHostController); showMenu = false
                                },
                                text = { Text(text = "Korpa", style = MaterialTheme.typography.labelSmall) }
                            )

                            Divider(color = lightblue, thickness = 1.dp)

                            DropdownMenuItem(
                                modifier = Modifier
                                    .background(darkblue),
                                onClick = {
                                    menuFunctions.onMessagesClick(navHostController); showMenu =
                                    false
                                },
                                text = { Text(text = "Obaveštenja", style = MaterialTheme.typography.labelSmall) }
                            )

                            Divider(color = lightblue, thickness = 1.dp)

                            DropdownMenuItem(
                                modifier = Modifier
                                    .background(darkblue),
                                onClick = {
                                    menuFunctions.onAccountClick(navHostController); showMenu =
                                    false
                                },
                                text = { Text(text = "Nalog", style = MaterialTheme.typography.labelSmall) }
                            )

                            Divider(color = lightblue, thickness = 1.dp)

                            DropdownMenuItem(
                                modifier = Modifier
                                    .background(darkblue),
                                onClick = {
                                    menuFunctions.onLogoutClick(navHostController); showMenu = false
                                },
                                text = { Text(text = "Odjava", style = MaterialTheme.typography.labelSmall) }
                            )
                        }
                    }
                )
            }
        )
    }
}

//@Preview
//@Composable
//fun PreviewMenuBar () {
//    MenuBar()
//}

//        TopAppBar(
//            colors = TopAppBarDefaults.topAppBarColors(
//                containerColor = Lightblue,
//                titleContentColor = MaterialTheme.colorScheme.primary,
//            ),
//            actions = {
//                IconButton(onClick = { showMenu = true }) {
//                    Icon(
//                        imageVector = Icons.Filled.Menu,
//                        contentDescription = "Localized description"
//                    )
//                }
//                DropdownMenu(
//                    expanded = showMenu,
//                    onDismissRequest = { showMenu = false }
//                ) {
//                    DropdownMenuItem(
//                        onClick = {
//                            menuFunctions.onPromotionsClick(navHostController); showMenu = false
//                        },
//                        text = { Text("Promocije") }
//                    )
//                    DropdownMenuItem(
//                        onClick = {
//                            menuFunctions.onProductsClick(navHostController); showMenu = false
//                        },
//                        text = { Text("Proizvodi") }
//                    )
//                    DropdownMenuItem(
//                        onClick = {
//                            menuFunctions.onContactClick(navHostController); showMenu = false
//                        },
//                        text = { Text("Kontakt") }
//                    )
//                    DropdownMenuItem(
//                        onClick = {
//                            menuFunctions.onBasketClick(navHostController); showMenu = false
//                        },
//                        text = { Text("Korpa") }
//                    )
//                    DropdownMenuItem(
//                        onClick = {
//                            menuFunctions.onAccountClick(navHostController); showMenu = false
//                        },
//                        text = { Text("Nalog") }
//                    )
//                    DropdownMenuItem(
//                        onClick = {
//                            menuFunctions.onMessagesClick(navHostController); showMenu = false
//                        },
//                        text = { Text("Obaveštenja") }
//                    )
//                    DropdownMenuItem(
//                        onClick = {
//                            menuFunctions.onLogoutClick(navHostController); showMenu = false
//                        },
//                        text = { Text("Odjava") }
//                    )
//                }
//            },
//
//            title = {
//                Text(
//                    "",
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )
//            },
//        )