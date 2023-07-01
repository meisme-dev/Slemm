package com.slemmapp.slemm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.slemmapp.slemm.components.SlemmBar
import com.slemmapp.slemm.ui.theme.SlemmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlemmTheme {
                MainContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainContent() {
    Scaffold(topBar = {
        SlemmBar()
    }) { paddingValues ->
        run {
            paddingValues
        }
    }
}