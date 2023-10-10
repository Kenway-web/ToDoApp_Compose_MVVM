package com.kenway.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kenway.todoapp.navigation.destinations.listComposable
import com.kenway.todoapp.navigation.destinations.taskComposable
import com.kenway.todoapp.ui.viewmodels.SharedViewModel
import com.kenway.todoapp.util.Constants.LIST_SCREEN


@Composable
fun SetUpNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel

){
    val screen = remember(navController){
        Screens(navController=navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN){
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel=sharedViewModel
        )
        taskComposable(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = screen.list
        )
    }
}
