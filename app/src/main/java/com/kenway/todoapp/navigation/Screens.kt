package com.kenway.todoapp.navigation

import androidx.navigation.NavHostController
import com.kenway.todoapp.util.Action
import com.kenway.todoapp.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {
    val list:(Action) -> Unit = {action ->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){inclusive=true}
        }
    }

    val task:(Int) -> Unit = {taskId ->
        navController.navigate("task/$taskId")
    }


}