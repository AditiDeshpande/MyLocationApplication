package com.example.mylocationapplication

import android.content.Context
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
We have an activity that shows the device location on the screen.
A common implementation might be as follows
 */
class MyLocationActivity : AppCompatActivity() {

    private lateinit var myLocationListener: MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myLocationListener = MyLocationListener(this){
            location ->
            //Update UI
        }
        Util.checkUserStatus(....){ result ->
            if(result){
                myLocationListener.enable()
            }
        }

    }

    public override fun onStart() {
        super.onStart()
        myLocationListener.start()
        /*
        Manage other components that need to respond
        to the activity lifecycle
         */
        /*
        Foll is the change made to solve the prob
         */
        Util.checkUserStatus{
            result ->
            //What if this callback is invoked AFTER activity
            //is stopped?
            if(result){
                myLocationListener.start()
            }
        }

    }

    public override fun onStop() {
        super.onStop()
        myLocationListener.stop()
        /*
        Manage other components that need to respond
        to the activity lifecycle
         */
    }
}

/*
for this location tracking ex. we can make MyLocationListener class
implement LifecycleObserver and then initialize it with activity's
Lifecycle in the onCreate() method. THis allows the MyLocationListener
class to be self-sufficient , meaning that the logic to react to
changes in lifecycle status is declared in MyLocationListener instead
of activity. having the individual compo. store thier own logic
makes activities and frag. logic easier to manage.
 */
internal class MyLocationListener(
        private val context: Context ,
        private val callback: (Location) -> Unit
){
    fun start(){
        //Connect to system location service
    }

    fun stop(){
        //disconnect from sys location service
    }
}

/*
Even though sample looks fine in real app u end up having too many
calls that manage the UI and other compo. in response to current
state of lifecyle. Managing multiple compo. places a considerable
amount of code in lifecycle methods , such as onStart() and onStop()
which maked them difficult to maintain
Moreover there js no guarantee that the compo. starts b4 activity/frag.
is stopped. THis is especially true when we need to perform a long
running opearation, such as some config. check in onStart()
This can cause a race condition where onStop() finishes b4 onStart,
keeping compo. alive longer than it's needed.
 */