package com.example.datastorestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.datastorestudy.util.dataStore
import com.example.datastorestudy.util.get
import com.example.datastorestudy.util.set

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
        lifecycleScope.launchWhenCreated {
            applicationContext.dataStore.set(EXAMPLE_COUNTER, 100)
            val value = applicationContext.dataStore.get(EXAMPLE_COUNTER, 0)
            Log.d("MainActivity", "onCreate: $value")
        }
    }
}