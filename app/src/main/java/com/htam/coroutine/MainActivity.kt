package com.htam.coroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.htam.coroutine.ui.theme.CoroutineTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoroutineTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }

        GlobalScope.launch {
            Log.d(TAG, "Start call APIs")

            val resApi1 = callAPI1()
            val resApi2 = callAPI2()

            // The time is sum up (3s + 4s = 7s) because is in the same coroutine scope
            Log.d(TAG, "Call API1: $resApi1")
            Log.d(TAG, "Call API2: $resApi2")
        }

    }

    private suspend fun callAPI1(): String {
        delay(3000L)
        return "This is the response of API-1"
    }

    private suspend fun callAPI2(): String {
        delay(4000L)
        return "This is the response of API-2"
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
