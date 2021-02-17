package com.example.workshopcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
                TopBar()
            },
            bodyContent = {
                BodyContent()
            }
        )

    }
}

@Composable
fun TopBar() {
    // define your composable TopBar. Look for the TopAppBar component
}

@Composable
fun BodyContent() {
    // Example
    Column(modifier = Modifier.fillMaxWidth()) {
        val emailState = remember { mutableStateOf(String()) }
        OutlinedTextField(
            label = {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.body2
                )
            },
            value = emailState.value,
            onValueChange = {
                emailState.value = it
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
    /*
    Use States to get/set values from the input fields
    check documentation: https://developer.android.com/codelabs/jetpack-compose-state#5
    for other components: https://foso.github.io/Jetpack-Compose-Playground
    *
    * */
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkshopComposeTheme {
        MyApp()
    }
}