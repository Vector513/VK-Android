package com.example.vk_android.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import com.example.vk_android.R
import com.example.vk_android.ui.activity.ShowActivity.Companion.EXTRA_TEXT
import com.example.vk_android.ui.theme.VKAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKAndroidTheme {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    val context = LocalContext.current
    var inputText by rememberSaveable { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            TextField(
                value = inputText,
                onValueChange = { newValue ->
                    inputText = newValue
                },
                modifier = Modifier.padding(innerPadding),
            )
            Button(
                onClick = {
                    val intent = Intent(context, ShowActivity::class.java).apply {
                        putExtra(EXTRA_TEXT, inputText)
                    }
                    context.startActivity(intent)
                },
            ) {
                Text(text = stringResource(R.string.main_activity_button_text))
            }

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = "tel:$inputText".toUri()
                    }
                    context.startActivity(intent)
                },
            ) {
                Text(text = stringResource(R.string.main_activity_call_friend_button_text))
            }

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, inputText)
                    }
                    context.startActivity(intent)
                },
            ) {
                Text(text = stringResource(R.string.main_activity_share_button_text))
            }

        }

    }
}
