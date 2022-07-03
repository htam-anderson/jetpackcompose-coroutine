package com.htam.coroutine

sealed class ScreenRoutes(val route: String) {
    object FirstScreen: ScreenRoutes("Greeting")
    object SecondScreen: ScreenRoutes("Greeting2")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
