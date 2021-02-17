package com.example.workshopcompose.finished

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workshopcompose.R
import com.example.workshopcompose.ui.theme.WorkshopComposeTheme
import kotlinx.coroutines.launch

class MainActivityFinished : AppCompatActivity() {
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
private fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            topBar = {
                TopBar()
            },
            bodyContent = {
                BodyContent(
                    snackbarHostState
                )
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            ErrorSnackbar(
                snackbarHostState = snackbarHostState
            )
        }
    }
}

@Composable
private fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Sign In",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Arrow back"
                )
            }
        },
        // We need to balance the navigation icon, so we add a spacer.
        actions = {
            Spacer(modifier = Modifier.preferredWidth(68.dp))
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}

@Composable
private fun BodyContent(
    snackbarHostState: SnackbarHostState
) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        val emailState = remember { mutableStateOf(String()) }
        val passwordState = remember { mutableStateOf(String()) }
        val scope = rememberCoroutineScope()


        Email(
            label = "Email",
            state = emailState
        )

        Spacer(modifier = Modifier.preferredHeight(16.dp))

        Password(
            label = "Password",
            state = passwordState
        )
        Spacer(modifier = Modifier.preferredHeight(16.dp))
        Button(
            onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Sign in attempt for: ${emailState.value}"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            enabled = emailState.value.isNotBlank() && passwordState.value.isNotBlank()
        ) {
            Text(
                text = "Sign In"
            )
        }
    }
}

@Composable
private fun Email(
    label: String,
    state: MutableState<String>
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.body2
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.body2,
    )
}

@Composable
private fun Password(
    label: String,
    state: MutableState<String>
) {
    val showPassword = remember { mutableStateOf(false) }
    OutlinedTextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.body2,
        label = {
            Providers(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2
                )
            }
        },
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        painter = painterResource(R.drawable.visibility_on),
                        contentDescription = "Hide password"
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        painter = painterResource(R.drawable.visibility_off),
                        contentDescription = "Show password"
                    )
                }
            }
        },
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
    )
}

@Composable
private fun ErrorSnackbar(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(16.dp),
                text = {
                    Text(
                        text = data.message,
                        style = MaterialTheme.typography.body2
                    )
                }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom)
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    WorkshopComposeTheme {
        MyApp()
    }
}