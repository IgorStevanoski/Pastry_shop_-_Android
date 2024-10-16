package com.example.domaci3.welcome

import UserViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.R
import com.example.domaci3.components.ButtonC
import com.example.domaci3.components.ClickableTextC
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TextFieldC
import com.example.domaci3.components.TextSmallC
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox
import com.example.domaci3.domain.UserData
import com.example.domaci3.model.user.UserState
import rs.edu.raf.raf_vezbe3compose.passwords.repository.ProductRepository
import rs.edu.raf.raf_vezbe3compose.passwords.repository.UserRepository

fun NavGraphBuilder.register(
    route: String,
    navController: NavHostController,
    onLoginClick: (Int) -> Unit,
    onRegisterClick: () -> Unit,
) = composable(
    route = route
) {

    RegisterScreen(
        onLoginClick = onLoginClick,
        onRegisterClick = onRegisterClick
    )

    TopBar(navHostController = navController)
}

@Composable
fun RegisterScreen(
    onLoginClick: (Int) -> Unit = {},
    onRegisterClick: () -> Unit = {},
) {

    var firstname by remember { mutableStateOf(TextFieldValue("")) }
    var lastname by remember { mutableStateOf(TextFieldValue("")) }
    var phone by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            ColumnC(
                content = {

                    TransparentBox()

                    TextCHeadline(
                        paddingValues = paddingValues,
                        text = stringResource(id = R.string.register))

                    TextSmallC(paddingValues = paddingValues, text = stringResource(id = R.string.name))
                    TextFieldC(
                        value = firstname,
                        onValueChange = { newText ->
                            firstname = newText
                        }
                    )

                    TextSmallC(paddingValues = paddingValues, text = stringResource(id = R.string.lastname))
                    TextFieldC(
                        value = lastname,
                        onValueChange = { newText ->
                            lastname = newText
                        }
                    )

                    TextSmallC(paddingValues = paddingValues, text = stringResource(id = R.string.phone))
                    TextFieldC(
                        value = phone,
                        onValueChange = { newText ->
                            phone = newText
                        })

                    TextSmallC(paddingValues = paddingValues, text = stringResource(id = R.string.address))
                    TextFieldC(
                        value = address,
                        onValueChange = { newText ->
                            address = newText
                        })

                    TextSmallC(paddingValues = paddingValues, text = stringResource(id = R.string.username))
                    TextFieldC(
                        value = username,
                        onValueChange = { newText ->
                            username = newText
                        })

                    TextSmallC(paddingValues = paddingValues, text = stringResource(id = R.string.password))
                    TextFieldC(
                        value = password,
                        onValueChange = { newText ->
                            password = newText
                        })

                    ButtonC(
                        onClick = {
                            register(
                                onRegisterClick = { onRegisterClick() },
                                firstname = firstname.text,
                                lastname = lastname.text,
                                phone = phone.text,
                                address = address.text,
                                username = username.text,
                                password = password.text,
                            )
                        },
                        text = stringResource(id = R.string.enter),
                        paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding() * 2, 0.dp, 0.dp)
                    )

                    ClickableTextC(
                        onClick = onLoginClick,
                        paddingValues = paddingValues,
                        text = stringResource(id = R.string.haveacc)
                    )
                }
            )
        },
    )
}

fun register(
    onRegisterClick: () -> Unit = {},
    firstname: String,
    lastname: String,
    phone: String,
    address: String,
    username: String,
    password: String,
) {
    val existingUser = UserRepository.getUserByUsername(username)

    if (existingUser == null) {
        UserRepository.updateOrInsertUser(
            username = username,
            data = UserData(
                firstname = firstname,
                lastname = lastname,
                phoneNumber = phone,
                address = address,
                username = username,
                password = password
            )
        )
        onRegisterClick()
    }
}

@Preview
@Composable
fun RegisterLogin() {
    RegisterScreen()
}