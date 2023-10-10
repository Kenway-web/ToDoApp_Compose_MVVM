package com.kenway.todoapp.data.repositories

import com.kenway.todoapp.data.ToDoDao
import com.kenway.todoapp.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class ToDoRepository @Inject constructor (private val tododao: ToDoDao) {

    val getAllTasks: Flow<List<ToDoTask>> = tododao.getAllTasks()
    val sortByLowerPriority:Flow<List<ToDoTask>> = tododao.sortByLowPriority()
    val sortByHigherPriority:Flow<List<ToDoTask>> = tododao.sortByHighPriority()


    fun getSelectedTask(taskId:Int) : Flow<ToDoTask>{
        return tododao.getSelectedTask(taskId=taskId)
    }

    suspend fun addTask(toDoTask: ToDoTask){
        tododao.addTasks(toDoTask=toDoTask)
    }


    suspend fun updateTask(toDoTask: ToDoTask){
        tododao.updateTask(toDoTask=toDoTask)
    }

    suspend fun deleteTask(toDoTask: ToDoTask){
        tododao.deleteTask(toDoTask=toDoTask)
    }

    suspend fun deleteAllTask(){
        tododao.deleteAllTask()
    }

    fun searchDatabase(searchQuery:String) :Flow<List<ToDoTask>>{
        return tododao.searchDatabase(searchQuery=searchQuery)
    }

}