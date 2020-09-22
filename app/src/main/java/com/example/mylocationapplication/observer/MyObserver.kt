package com.example.mylocationapplication.observer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/*
A class can monitor the component's lifecycle status by adding
annotations to it's methods. Then u can add an observer by calling
addObserver() method of the Lifecycle class and passing an instance
of your observer.
 */
class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectListener(){

    }

    myLifecycleOwner.getLifecycle().addObserver(MyObserver())

    /*
    In the ex. above the myLifecycleOwner object implements the
    LifecycleOwner interface.
     */
}