package com.example.datastoresample

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

object DataStoreExtension {

    suspend fun DataStore<Preferences>.setString(key: Preferences.Key<String>, value: String) {
        edit {
            it[key] = value
        }
    }

    fun DataStore<Preferences>.getString(key: Preferences.Key<String>, default: String = ""): Flow<String> {
        return data.catch {
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                //FirebaseCrashlytics.getInstance().recordException(it)
            }
        }.map {
            it[key] ?: default
        }
    }

    suspend fun DataStore<Preferences>.deleteString(key: Preferences.Key<String>) {
        edit {
            it.remove(key)
        }
    }

}
