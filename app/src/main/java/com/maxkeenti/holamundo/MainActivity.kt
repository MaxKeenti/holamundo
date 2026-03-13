package com.maxkeenti.holamundo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterfaceCustom()
        }
    }
}

@Composable
fun InterfaceCustom() {
    var name by rememberSaveable{mutableStateOf("")}

    Text(text = "Welcome to my app")
    TextField(
        value = name,
        onValueChange = { name = it },
        label = { Text(text = "What's your name?") },
    )

    val context = LocalContext.current
    Button(onClick = {
        Toast.makeText(context, "Hello $name!", Toast.LENGTH_SHORT).show()
    }){
        Text(text = "Greet")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterfaceCustom()
}