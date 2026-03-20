package com.maxkeenti.holamundo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
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
import android.provider.Settings
import android.os.Build
import androidx.compose.runtime.remember
import java.security.MessageDigest

fun deviceSignature(context: Context): String {

    val androidId = Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ANDROID_ID
    )

    val data = androidId + Build.MODEL + Build.MANUFACTURER

    val md = MessageDigest.getInstance("SHA-256")
    val hash = md.digest(data.toByteArray())

    return hash.joinToString("") { "%02x".format(it) }
}

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
    val context = LocalContext.current
    // Calculate signature using the helper function
    val signature = remember { deviceSignature(context) }

    Column {
        Text(text = "Welcome to my app")
        Text(text = "Device Signature: $signature")
        
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "What's your name?") },
        )

        Button(onClick = {
            Toast.makeText(context, "Hello $name!\nSignature: $signature", Toast.LENGTH_LONG).show()
        }){
            Text(text = "Greet")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterfaceCustom()
}
