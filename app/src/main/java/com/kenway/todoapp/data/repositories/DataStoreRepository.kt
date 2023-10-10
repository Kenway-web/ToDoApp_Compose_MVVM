package com.kenway.todoapp.data.repositories


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kenway.todoapp.data.models.Priority
import com.kenway.todoapp.util.Constants.PREFERENCE_KEY
import com.kenway.todoapp.util.Constants.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.prefs.Preferences
import javax.inject.Inject

private val Context.datastore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context:Context
) {

    private object PreferenceKeys{
        val sortKey = stringPreferencesKey(name= PREFERENCE_KEY)
    }

    private val datastore = context.datastore

    suspend fun persistSortState(priority: Priority){
        datastore.edit { preference->
            preference[PreferenceKeys.sortKey] = priority.name
        }
    }

    val readSortState: Flow<String> = datastore.data
        .catch { exception->
            if(exception is IOException){
                emit(emptyPreferences())
            }
            else{
                throw exception
            }
        }
        .map { preferences->
            val sortState = preferences[PreferenceKeys.sortKey]?: Priority.NONE.name
            sortState
        }

}