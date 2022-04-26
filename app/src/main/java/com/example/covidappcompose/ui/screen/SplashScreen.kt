package com.example.covidappcompose.ui.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.covidappcompose.R
import com.example.covidappcompose.ui.screen.mainscreen.MainActivity
import com.example.covidappcompose.ui.theme.CovidAppComposeTheme
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CovidAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.splash_bg),
                        contentDescription = ""
                    )
                }
            }
        }

        Timer().schedule(3000) {
            goToMainScreen()
        }
    }

    private fun goToMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}