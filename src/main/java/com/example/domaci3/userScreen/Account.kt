package com.example.domaci3.userScreen

import UserViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.R
import com.example.domaci3.components.ButtonC
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TextFieldC
import com.example.domaci3.components.TextMediumC
import com.example.domaci3.components.TextSmallC
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox
import com.example.domaci3.domain.CommentData
import com.example.domaci3.domain.ProductData
import com.example.domaci3.domain.UserData
import rs.edu.raf.raf_vezbe3compose.passwords.repository.UserRepository

fun NavGraphBuilder.account(
    route: String,
    navController: NavHostController
) = composable(
    route = route
) {

    AccountScreen(
    )

    TopBar(
        showMenuBar = true,
        navController
    )
}

@Composable
fun AccountScreen(
) {
    val logged = UserRepository.logged()

    val user = UserRepository.getUserByUsername(logged)

    var firstname by remember { mutableStateOf(TextFieldValue(user?.firstname ?: "")) }
    var lastname by remember { mutableStateOf(TextFieldValue(user?.lastname ?: "")) }
    var phone by remember { mutableStateOf(TextFieldValue(user?.phoneNumber ?: "")) }
    var address by remember { mutableStateOf(TextFieldValue(user?.address ?: "")) }
    var username by remember { mutableStateOf(TextFieldValue(user?.username ?: "")) }
    var password by remember { mutableStateOf(TextFieldValue(user?.password ?: "")) }

    val list = listOf(username, firstname, lastname, phone, address, password)
//    var listTextField = mutableListOf(username, firstname, lastname, phone, address, password)
    val listRes = listOf(
        R.string.username,
        R.string.name,
        R.string.lastname,
        R.string.phone,
        R.string.address,
        R.string.password
    )

    var showInput by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            ColumnC(
                content = {

                    TransparentBox()

                    TextCHeadline(
                        paddingValues = paddingValues,
                        text = stringResource(id = R.string.account)
                    )

                    list.forEachIndexed { index, elem ->

                        TextMediumC(
                            paddingValues = PaddingValues(
                                0.dp,
                                paddingValues.calculateTopPadding(),
                                0.dp,
                                0.dp
                            ),
                            text = stringResource(id = listRes[index])
                        )

                        if (user != null) {
                            if (!showInput) {

                                TextSmallC(
                                    paddingValues = PaddingValues(
                                        0.dp,
                                        0.dp,
                                        0.dp,
                                        0.dp
                                    ),
                                    text = elem.text
                                )

                            } else {
                                TextFieldC(
                                    value = when (index) {
                                        0 -> username
                                        1 -> firstname
                                        2 -> lastname
                                        3 -> phone
                                        4 -> address
                                        5 -> password
                                        else -> username
                                    },
                                    onValueChange = { newText ->
                                        when (index) {
                                            0 -> username = newText
                                            1 -> firstname = newText
                                            2 -> lastname = newText
                                            3 -> phone = newText
                                            4 -> address = newText
                                            5 -> password = newText
                                            else -> username = newText
                                        }
                                    },
                                    lines = 1
                                )
                            }
                        } else {

                            TextMediumC(
                                paddingValues = PaddingValues(
                                    0.dp,
                                    paddingValues.calculateTopPadding(),
                                    0.dp,
                                    0.dp
                                ),
                                text = "/"
                            )
                        }
//                            })
//                        }
                    }

                    // username, firstname, lastname, phone, address, password
                    ButtonC(
                        onClick = {
                            if (showInput) {
                                updateUser(
                                    logged = logged,
                                    username = username.text,
                                    firstname = firstname.text,
                                    lastname = lastname.text,
                                    phone = phone.text,
                                    address = address.text,
                                    password = password.text,
//                                username = listTextField[0].text,
//                                firstname = listTextField[1].text,
//                                lastname = listTextField[2].text,
//                                phone = listTextField[3].text,
//                                address = listTextField[4].text,
//                                password = listTextField[5].text,
                                )
                            }
                            showInput = !showInput
                        },
                        text = stringResource(id = R.string.account_modify),
                        paddingValues = paddingValues
                    )

                    TransparentBox()
                }
            )
        },
    )
}

fun updateUser(
    logged: String,
    username: String,
    firstname: String,
    lastname: String,
    phone: String,
    address: String,
    password: String,
) {
    val existingUser = UserRepository.getUserByUsername(logged)

    if (existingUser != null) {
        UserRepository.updateOrInsertUser(
            username = logged,
            data = UserData(
                firstname = firstname,
                lastname = lastname,
                phoneNumber = phone,
                address = address,
                username = username,
                password = password
            )
        )
    }
}
