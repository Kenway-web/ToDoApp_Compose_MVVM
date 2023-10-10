package com.kenway.todoapp.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kenway.todoapp.ui.screens.task.TaskScreen
import com.kenway.todoapp.ui.viewmodels.SharedViewModel
import com.kenway.todoapp.util.Action
import com.kenway.todoapp.util.Constants
import com.kenway.todoapp.util.Constants.TASK_ARGUMENT_KEY
import com.kenway.todoapp.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        sharedViewModel.getSelectedTask(taskId = taskId)
        Log.d("getSelectedTask",taskId.toString())
        val selectedTask by sharedViewModel.selectedTask.collectAsState()


        LaunchedEffect(key1 = selectedTask) {
            if(selectedTask!=null||taskId==-1){
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }

        }


        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}