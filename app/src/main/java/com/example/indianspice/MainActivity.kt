package com.example.indianspice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.indianspice.ui.IndianSpiceApp
import com.example.indianspice.ui.theme.IndianSpiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            IndianSpiceTheme {
                Surface { IndianSpiceApp() }
            }
        }
    }
}