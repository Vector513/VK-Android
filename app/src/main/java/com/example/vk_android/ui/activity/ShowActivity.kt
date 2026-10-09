package com.example.vk_android.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.vk_android.ui.theme.VKAndroidTheme

class ShowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val text = intent.getStringExtra(EXTRA_TEXT).orEmpty()

        setContent {
            VKAndroidTheme {
                ShowScreen(text = text)
            }
        }
    }

    companion object {
        const val EXTRA_TEXT = "com.example.vk_android.ui.activity.EXTRA_TEXT"
    }
}

@Composable
private fun ShowScreen(text: String) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(text = text, modifier = Modifier.padding(innerPadding))
    }
}
