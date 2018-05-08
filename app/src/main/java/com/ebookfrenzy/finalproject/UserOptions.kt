package com.ebookfrenzy.finalproject


import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView


import com.ebookfrenzy.finalproject.R.layout.activity_main

class UserOptions : AppCompatActivity(),SensorEventListener {

    private var mSensorManager : SensorManager ?= null
    private var mAccelerometer : Sensor ?= null
    private var mDetector : GestureDetector?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        //super.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        val button:Button = findViewById(R.id.cardio_button)
        button.setOnClickListener {
            goToCardioPage()
        }

        val logoutButton:Button = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener{
            val intent: Intent = Intent(this,AnonymousLogin::class.java)
            startActivity(intent)
        }
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }


    fun goToCardioPage()
    {

        val intent: Intent = Intent(this, CardioActivity::class.java)
        startActivity(intent)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this,mAccelerometer, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onSensorChanged(event: SensorEvent?)
    {
        if(event!=null)
        {

            var xval = event.values[0].toString()
            var yval = event.values[1].toString()
            var zval = event.values[2].toString()
            var line = "$xval, $yval, $zval"

            Log.i("Sensing", line)


        }


    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        var xcoo = event.getX().toString()
        var ycoo = event.getY().toString()

        var lines = xcoo + "  " +  ycoo
        var xvalue = findViewById<TextView>(R.id.userprofilename)
        xvalue.setText(lines)



        return true
    }



}