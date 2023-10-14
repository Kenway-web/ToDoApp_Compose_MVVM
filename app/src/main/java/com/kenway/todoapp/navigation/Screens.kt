package com.kenway.todoapp.navigation

import android.window.SplashScreen
import androidx.navigation.NavHostController
import com.kenway.todoapp.util.Action
import com.kenway.todoapp.util.Constants.LIST_SCREEN
import com.kenway.todoapp.util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION.name}"){
            popUpTo(SPLASH_SCREEN){ inclusive = true }
        }
    }
    val list:(Int) -> Unit = {taskId ->
        navController.navigate("task/$taskId")
    }

    val task:(Action) -> Unit = {action ->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){inclusive=true}
        }
    }



}