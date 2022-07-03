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

        // GlobalScope is stay until the application die
        GlobalScope.launch {
            // this is just only pause the current coroutine, it not block the thread
            // different with sleep from thread
            delay(5000L)
            Log.d(TAG, "Coroutine from thread ${Thread.currentThread().name}")
            // this coroutine will be cancel when the main thread die
        }
        Log.d(TAG, "Hello from thread ${Thread.currentThread().name}")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
