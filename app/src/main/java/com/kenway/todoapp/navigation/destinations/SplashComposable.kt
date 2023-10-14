package com.kenway.todoapp.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kenway.todoapp.ui.screens.list.ListScreen
import com.kenway.todoapp.ui.screens.splash.SplashScreen
import com.kenway.todoapp.ui.viewmodels.SharedViewModel
import com.kenway.todoapp.util.Constants
import com.kenway.todoapp.util.Constants.SPLASH_SCREEN
import com.kenway.todoapp.util.toAction

fun NavGraphBuilder.splashComposable(
    navigateToListScreen : () -> Unit,
){
    composable(
        route = SPLASH_SCREEN,
    ){
        SplashScreen(navigateToListScreen=navigateToListScreen)
    }
}