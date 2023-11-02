package com.smartherd.diabetessystem.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.widget.Toast
import java.security.Provider

class PedometerService : Service(), SensorEventListener {
    private val mStepDistance = 0.762
    private val fStepDistance = 0.67
    private var sensorManager: SensorManager? = null
    private var running = false
    private var stepCount = 0
    private var totalSteps = 0.0
    private var previousTotalSteps = 0.0
    private var caloriesBurnt = 0.0
    private var p_distance = 0.0
    private var accelerometer: Sensor? = null
    private var lastValue: Float = 0f
    private var threshold = 20f
    private var isStepDetected = false
    private var stepGoal: Int = 0
    private var stepGoalSet: Boolean = false

    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (accelerometer == null) {
            Toast.makeText(this, "No accelerometer sensor found", Toast.LENGTH_LONG).show()
        }
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onSensorChanged(p0: SensorEvent?) {

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}