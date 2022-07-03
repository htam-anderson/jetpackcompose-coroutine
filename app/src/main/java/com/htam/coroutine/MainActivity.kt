package com.htam.coroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.htam.coroutine.ui.theme.CoroutineTheme
import kotlinx.coroutines.*

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

//        // Not block the Main Thread
//        GlobalScope.launch {
//            delay(3000L)
//        }

        // Block the Main Thread
        Log.d(TAG,"Before runBlocking")
        runBlocking {
            launch(Dispatchers.IO) {
                Log.d(TAG, "Running Thread: ${Thread.currentThread().name}")
                delay(3000L)
                Log.d(TAG,"Finished IO 1")
            }
            launch(Dispatchers.IO) {
                Log.d(TAG, "Running Thread: ${Thread.currentThread().name}")
                delay(3000L)
                Log.d(TAG,"Finished IO 2")
            }

            Log.d(TAG,"Start Block the Main Threat")
            delay(5000L) // Same with Thread.sleep(5000L)
            Log.d(TAG,"End Block the Main Threat")
        }
        Log.d(TAG,"After runBlocking")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
