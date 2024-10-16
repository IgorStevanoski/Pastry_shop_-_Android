package rs.edu.raf.raf_vezbe3compose.passwords.repository

import com.example.domaci3.domain.CommentData
import com.example.domaci3.domain.ProductData
import com.example.domaci3.domain.UserData

val UserSampleData = listOf(
    UserData(
        username = "a",
        password = "a",
        firstname = "Igor",
        lastname = "Stevanoski",
        phoneNumber = "061 23 456 78",
        address = "adresa",
        basket = listOf(),
        messages = listOf(),
    ),
    UserData(
        username = "A",
        password = "A",
        firstname = "Agor",
        lastname = "AStevanoski",
        phoneNumber = "060 23 456 78",
        address = "Adresa",
        basket = listOf(),
        messages = listOf(),
    ),
    UserData(
        username = "",
        password = "",
        firstname = "gor",
        lastname = "tevanoski",
        phoneNumber = "60 23 456 78",
        address = "dresa",
        basket = listOf(

        ),
        messages = listOf(),
    ),
)
