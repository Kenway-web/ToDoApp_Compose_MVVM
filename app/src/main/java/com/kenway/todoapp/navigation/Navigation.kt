package com.kenway.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kenway.todoapp.navigation.destinations.listComposable
import com.kenway.todoapp.navigation.destinations.splashComposable
import com.kenway.todoapp.navigation.destinations.taskComposable
import com.kenway.todoapp.ui.viewmodels.SharedViewModel
import com.kenway.todoapp.util.Constants.LIST_SCREEN
import com.kenway.todoapp.util.Constants.SPLASH_SCREEN


@Composable
fun SetUpNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel

){
    val screen = remember(navController){
        Screens(navController=navController)
    }

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {

        splashComposable (
            navigateToListScreen = screen.splash
        )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel=sharedViewModel
        )
        taskComposable(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = screen.task
        )
    }
}
