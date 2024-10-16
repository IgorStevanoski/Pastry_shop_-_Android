package com.example.domaci3

import UserViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domaci3.components.TopBar
import com.example.domaci3.model.product.ProductListViewModel
import com.example.domaci3.navigation.Navigation
import com.example.domaci3.ui.theme.Domaci3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            Domaci3Theme {

                val userViewModel = viewModel<UserViewModel>()
                userViewModel.fetchUsers()
                val productListViewModel = viewModel<ProductListViewModel>()
                productListViewModel.fetchProducts()

                val image = ImageBitmap.imageResource(R.drawable.pattern2)
                val brush = remember(image) { ShaderBrush(ImageShader(image, TileMode.Repeated, TileMode.Repeated)) }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush)
                ){
                    Navigation(this@MainActivity)
                }
            }
        }
    }
}

// u build gradle kts
//    val nav_version = "2.7.7"
//    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
//    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
//    implementation("androidx.navigation:navigation-compose:$nav_version")