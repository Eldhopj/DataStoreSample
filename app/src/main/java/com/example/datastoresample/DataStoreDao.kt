package com.example.datastoresample

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.datastoresample.DataStoreDao.PreferencesKeys.NAME
import com.example.datastoresample.DataStoreExtension.deleteString
import com.example.datastoresample.DataStoreExtension.getString
import com.example.datastoresample.DataStoreExtension.setString
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DataStoreDao @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val name: Flow<String> get() = dataStore.getString(NAME)

    suspend fun setName(name: String) {
        dataStore.setString(NAME, name)
    }

    suspend fun deleteName() {
        dataStore.deleteString(NAME)
    }

    private object PreferencesKeys {
        val NAME = stringPreferencesKey("name")
    }
}
