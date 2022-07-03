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

        val job = GlobalScope.launch(Dispatchers.Default) {
//            repeat(5) {
//                Log.d(TAG, "Coroutine is continuing...")
//                delay(1000L)
//            }
            Log.d(TAG, "Start run long calculation")
//            withTimeout(3000L) {
//                // ....
//            }
            for(i in 30..40) {
//                if (isActive) {
//                    Log.d(TAG, "result of $i : ${fib(i)}")
//                }
                Log.d(TAG, "result of $i : ${fib(i)}")
            }
            Log.d(TAG, "End run long calculation")
        }

        runBlocking {
//            job.join() // Waiting for the job to finish
            delay(2000L)
            job.cancel() // Cancel the job
            Log.d(TAG, "Cancel JOB!!!")
        }
    }

    fun fib(n: Int): Long {
        return if(n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n -2)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
