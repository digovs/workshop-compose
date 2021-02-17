package com.example.workshopcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.workshopcompose.ui.theme.WorkshopComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkshopComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                // define your composable TopBar. Look for the TopAppBar component
            },
            bodyContent = {
                /*
                Create the body of the Form here
                Use Column combined with TextField or OutlinedTextField and Spacer as a separator

                Use States to get/set values from the input fields
                check documentation: https://developer.android.com/codelabs/jetpack-compose-state#5
                Example:
                val emailState = remember { mutableStateOf(String()) }
                OutlinedTextField(
                    value = emailState.value,
                    onValueChange = {
                        emailState.value = it
                    }
                )
                *
                * */
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkshopComposeTheme {
        MyApp()
    }
}