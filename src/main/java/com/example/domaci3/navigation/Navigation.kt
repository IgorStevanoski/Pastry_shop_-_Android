package com.example.domaci3.navigation

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domaci3.R
import com.example.domaci3.userScreen.account
import com.example.domaci3.userScreen.basket
import com.example.domaci3.userScreen.contact
import com.example.domaci3.userScreen.messages
import com.example.domaci3.userScreen.productDetails
import com.example.domaci3.userScreen.productList
import com.example.domaci3.userScreen.products
import com.example.domaci3.userScreen.promotions
import com.example.domaci3.welcome.login
import com.example.domaci3.welcome.register
import com.example.domaci3.welcome.welcome

object menuFunctions{

    fun onImageClick(navController: NavHostController){
        navController.navigate(route = "promotions")
    }
    fun onProductsClick(navController: NavHostController) {
        navController.navigate(route = "products")
    }
    fun onPromotionsClick(navController: NavHostController){
        navController.navigate(route = "promotions")
    }
    fun onContactClick(navController: NavHostController){
        navController.navigate(route = "contact")
    }
    fun onBasketClick(navController: NavHostController){
        navController.navigate(route = "basket")
    }
    fun onAccountClick(navController: NavHostController){
        navController.navigate(route = "account")
    }
    fun onMessagesClick(navController: NavHostController){
        navController.navigate(route = "messages")
    }
    fun onLogoutClick(navController: NavHostController){
        navController.navigate(route = "welcome")
    }

}

@Composable
fun Navigation(
    context: ComponentActivity //zbog Toast-a
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
    ){

        welcome(
            route = "welcome",
            onLoginClick = {
                navController.navigate(route = "login")
            },
            onRegisterClick = {
                navController.navigate(route = "register")
            },
            navController = navController
        )

        login(
            route = "login",
            onLoginClick = {
                navController.navigate(route = "promotions")
            },
            onRegisterClick = {
                navController.navigate(route = "register")
            },
            navController = navController
        )

        register(
            route = "register",
            onLoginClick = {
                navController.navigate(route = "login")
            },
            onRegisterClick = {
                val message = "Korisnik uspešno registrovan."
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, message, duration)
                toast.show()

                navController.navigate(route = "login")
            },
            navController = navController
        )

        promotions(
            route = "promotions",
            navController
        )

        products(
            route = "products",
            navController,
            onCakesClick = {
                navController.navigate(route = "productList/cakes")
            },
            onCupCakesClick = {
                navController.navigate(route = "productList/cupcakes")
            },
        )

        contact(
            route = "contact",
            navController
        )

        basket(
            route = "basket",
            navController,
            context
        )

        account(
            route = "account",
            navController
        )

        messages(
            route = "messages",
            navController
        )

        productList(
            route = "productList/{type}",
            arguments = listOf(
                navArgument(name = "dataId") {
                    this.type = NavType.StringType
                    this.nullable = true
                }
            ),
            navController
        )

        productDetails(
            route = "product/{dataId}",
            arguments = listOf(
                navArgument(name = "dataId") {
                    this.type = NavType.StringType
                    this.nullable = true
                }
            ),
            navController = navController,
            context = context
        )

    }


}