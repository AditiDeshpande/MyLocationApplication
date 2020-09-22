package com.example.mylocationapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/*
Custom LifecycleOwner
 */
/*
If u have a custom class that u would like to make a LifecycleOwner
u can use LifecycleRegistry class , but u need to forward events
into the class.
 */
class MyActivity : Activity() , LifecycleOwner {

    private lateinit var lifecycleRegistry : LifecycleRegistry
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}