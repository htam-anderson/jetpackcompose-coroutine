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
import kotlin.system.measureTimeMillis

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

//        // Problem
//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//                val response1 = callAPI1()
//                val response2 = callAPI2()
//
//                Log.d(TAG, "Response for API1: $response1")
//                Log.d(TAG, "Response for API2: $response2")
//            }
//            Log.d(TAG, "Total time takes: $time ms.")
//        }

        // Approach 1: Terrible
//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//                var response1: String ? = null
//                var response2: String ? = null
//
//                val job1 = launch { response1 = callAPI1() }
//                val job2 = launch { response2 = callAPI2() }
//
//                job1.join()
//                job2.join()
//
//                Log.d(TAG, "Response for API1: $response1")
//                Log.d(TAG, "Response for API2: $response2")
//            }
//            Log.d(TAG, "Total time takes: $time ms.")
//        }

        // Approach 2: Async Await babe ^^
        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val response1 = async { callAPI1() }
                val response2 = async { callAPI2() }

                Log.d(TAG, "Response for API1: ${response1.await()}")
                Log.d(TAG, "Response for API2: ${response2.await()}")
            }
            Log.d(TAG, "Total time takes: $time ms.")
        }
    }

    private suspend fun callAPI1(): String {
        delay(3000L)
        return "Response of API 1"
    }

    private suspend fun callAPI2(): String {
        delay(3000L)
        return "Response of API 2"
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
