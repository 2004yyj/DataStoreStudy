package com.example.datastorestudy.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.*
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("dataStoreStudy")

suspend inline fun <T: Any> DataStore<Preferences>.get(key: Preferences.Key<T>, defaultValue: T): T {
    return data.catch { exception(it) }.map { it[key] }.firstOrNull() ?: defaultValue
}

suspend inline fun <T: Any> DataStore<Preferences>.set(key: Preferences.Key<T>, value: T?) {
    edit {
        if (value == null) {
            it.remove(key)
        } else {
            it[key] = value
        }
    }
}

suspend inline fun FlowCollector<Preferences>.exception(throwable: Throwable) {
    if (throwable is IOException) {
        emit(emptyPreferences())
    } else {
        throw throwable
    }
}