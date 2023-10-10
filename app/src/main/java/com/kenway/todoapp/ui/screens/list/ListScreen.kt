package com.kenway.todoapp.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kenway.todoapp.R
import com.kenway.todoapp.ui.theme.fabBackgroundColor
import com.kenway.todoapp.ui.viewmodels.SharedViewModel
import com.kenway.todoapp.util.Action
import com.kenway.todoapp.util.SearchAppBarState
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen : ( taskId :Int) -> Unit,
    sharedViewModel: SharedViewModel
){


    LaunchedEffect(key1=true)
    {
        Log.d("ListScreen","Launched Effect Triggered!")
        sharedViewModel.getAllTasks()
        sharedViewModel.readSortState()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by sharedViewModel.highPriorityTasks.collectAsState()



    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()


    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseActions = { sharedViewModel.handleDatabaseActions(action=action) },
        onUndoClicked={
          sharedViewModel.action.value=it
        },
        taskTitle = sharedViewModel.title.value,
        action = action
    )


    Scaffold(
        scaffoldState=scaffoldState,
        topBar = {
                 ListAppBar(
                     sharedViewModel=sharedViewModel,
                     searchAppBarState=searchAppBarState,
                     searchTextState=searchTextState
                 )
        },
        content = {
            ListContent(
                allTasks = allTasks,
                searchedTasks = searchedTasks,
                lowPriorityTasks=lowPriorityTasks,
                highPriorityTasks=highPriorityTasks,
                sortState=sortState,
                searchAppBarState = searchAppBarState,
                navigateToTaskScreen = navigateToTaskScreen
            )
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked : (taskId :Int) -> Unit
){
    FloatingActionButton(onClick = {
        onFabClicked(-1)
    },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint= Color.White
            )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseActions: () ->Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle:String,
    action: Action,
    ){

    handleDatabaseActions()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action)
    {
       if(action!=Action.NO_ACTION)
       {
           scope.launch {
               val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                   message = setMessage(action=action,taskTitle=taskTitle),
                   actionLabel = setActionLabel(action=action)
               )
               undoDeletedTask(
                   action=action,
                   snackBarResult=snackBarResult,
                   onUndoClicked=onUndoClicked
               )
           }
       }
    }

}


private fun setMessage(action: Action,taskTitle:String):String{
    return when(action){
            Action.DELETE_ALL -> "All Tasks Removed."
            else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action):String{
    return if(action.name=="DELETE"){
        "UNDO"
    }
    else{
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult:SnackbarResult,
    onUndoClicked: (Action)-> Unit
){
    if(snackBarResult==SnackbarResult.ActionPerformed&&action==Action.DELETE){
        onUndoClicked(Action.UNDO)
    }
}




/*In summary, Scaffold in Jetpack Compose provides a way to hold different material
components like an app bar, bottom navigation view, drawer, floating action button,
etc. together by implementing a layout structure strategy to make sure all these
components work together seamlessly3.*/