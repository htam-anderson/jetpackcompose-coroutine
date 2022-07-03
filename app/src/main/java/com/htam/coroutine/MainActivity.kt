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
            var name by remember { mutableStateOf("Android") }

            val scope = rememberCoroutineScope()
            LaunchedEffect("") {
                // Using Main Thread

                Log.d(TAG, "Using thread: ${Thread.currentThread().name}")
                // Using scope to run coroutine in IO Thread
                scope.launch(Dispatchers.IO) {
                    Log.d(TAG, "Using thread: ${Thread.currentThread().name}")
                    Log.d(TAG, "Start call APIs")
                    val resApi = callAPIName()

                    // Switch to Main Thread
                    withContext(Dispatchers.Main) {
                        Log.d(TAG, "Using thread: ${Thread.currentThread().name}")
                        name = resApi
                    }
                }
            }

//            GlobalScope.launch(Dispatchers.IO) {
//                Log.d(TAG, "Using thread: ${Thread.currentThread().name}")
//                Log.d(TAG, "Start call APIs")
//
//                val resApi = callAPIName()
//                withContext(Dispatchers.Main) {
//                    Log.d(TAG, "Using thread: ${Thread.currentThread().name}")
//                    Log.d(TAG, "Data: resApi")
//                    name = resApi
//                }
//            }

            CoroutineTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting(name)
                }
            }
        }
    }

    private suspend fun callAPIName(): String {
        delay(3000L)
        return "htam"
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
