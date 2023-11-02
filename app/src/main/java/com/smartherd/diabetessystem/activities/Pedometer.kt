package com.smartherd.diabetessystem.activities

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_STEP_COUNTER
import android.hardware.Sensor.TYPE_STEP_DETECTOR
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.databinding.ActivityPedometerBinding
import com.smartherd.diabetessystem.databinding.DialogPedometerSettingsBinding
import kotlinx.android.synthetic.main.activity_pedometer.*
import kotlinx.android.synthetic.main.activity_pedometer.toolbar_predict_diabetes
import kotlinx.android.synthetic.main.activity_predict_diabetes_page.*

class Pedometer : AppCompatActivity(), SensorEventListener {
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
    private var binding: ActivityPedometerBinding? = null
    private var dialogBinding: DialogPedometerSettingsBinding? = null
    private var stepGoal: Int = 0
    private var stepGoalSet: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedometerBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setUpActionBar()
        loadData()
        resetStepsSaved()
        binding!!.btnPedometerSettings.setOnClickListener {
            show_settings_dialog()
        }
        binding!!.continuePedometer.setOnClickListener {
            previousTotalSteps = totalSteps
            binding!!.cvGoalAchieved.visibility = View.INVISIBLE
            binding!!.tvStepGoal.visibility = View.INVISIBLE
            binding!!.tvStepsTaken.setTextColor(Color.parseColor("#000000"))
            stepGoal = 100000
            stepGoalSet = false
            binding!!.circularProgressBar.progressMax = stepGoal.toFloat()
        }
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (accelerometer == null) {
            Toast.makeText(this, "No accelerometer sensor found", Toast.LENGTH_LONG).show()
        }
    }

    private fun show_settings_dialog(){
        val dialog = Dialog(this)
        dialogBinding = DialogPedometerSettingsBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(dialogBinding!!.root)
        dialogBinding!!.tvSet.setOnClickListener {
            stepGoalSet = true
            val s_stepGoal = dialogBinding!!.etStepGoal.text.toString()
            stepGoal = s_stepGoal.toInt()
            dialog.dismiss()
        }
        dialogBinding!!.tvCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding?.toolbarPredictDiabetes)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }
        toolbar_predict_diabetes.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if (event.sensor == accelerometer) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()

                if (isStepDetected && acceleration < threshold) {
                    // Count a step when the acceleration drops below the threshold
                    isStepDetected = false
                    stepCount += 1
                    if (stepGoalSet) {
                        binding!!.tvStepGoal.visibility = View.VISIBLE
                        binding!!.tvStepGoal.text = "Step Goal : ${stepGoal}"
                        binding!!.circularProgressBar.progressMax = stepGoal.toFloat()
                        if (stepGoal.equals(stepCount)) {
                            binding!!.cvGoalAchieved.visibility = View.VISIBLE
                            binding!!.tvStepsTaken.setTextColor(Color.parseColor("#4CAF50"))
                        } else {
                            binding?.tvStepsTaken?.text = "$stepCount"
                            binding!!.steps.text = "${stepCount} steps"
                            p_distance = mStepDistance * stepCount
                            val f_distance = "%.2f".format(p_distance)
                            binding!!.distance.text = "${f_distance} m"
                            val caloriesBurnt =
                                caloriesBurned(stepCount.toDouble(), p_distance / 1000, 72.0)
                            val formated = "%.4f".format(caloriesBurnt)
                            binding!!.tvCalories.text = "${formated} kcals"
                            circularProgressBar.apply {
                                setProgressWithAnimation(stepCount.toFloat())
                            }
                        }
                    }
                    else {
                        binding!!.tvStepGoal.visibility = View.INVISIBLE
                        binding?.tvStepsTaken?.text = "$stepCount"
                        binding!!.steps.text = "${stepCount} steps"
                        p_distance = mStepDistance * stepCount
                        val f_distance = "%.2f".format(p_distance)
                        binding!!.distance.text = "${f_distance} m"
                        val caloriesBurnt =
                            caloriesBurned(stepCount.toDouble(), p_distance / 1000, 72.0)
                        val formated = "%.4f".format(caloriesBurnt)
                        binding!!.tvCalories.text = "${formated} kcals"
                        circularProgressBar.apply {
                            setProgressWithAnimation(stepCount.toFloat())
                        }
                    }
                }
                if (!isStepDetected && acceleration >= threshold) {
                    // Mark a step as detected when the acceleration goes above the threshold
                    isStepDetected = true
                }
            }
        }
    }

    fun caloriesBurned(totalSteps: Double, distance: Double, weight: Double): Double {
        val stepLength = distance / totalSteps
        Log.d("Distance", "$distance")
        Log.d("TotalSteps", "$totalSteps")
        Log.d("StepLegnth", "$stepLength")
        val one = (stepLength * totalSteps * 0.000045)
        Log.d("One", "$one")
        val two = (distance * weight * 0.024)
        Log.d("Two", "$two")
        return one + two
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    private fun resetStepsSaved() {
        binding!!.tvStepsTaken.setOnClickListener {
            Toast.makeText(this, "Long tap to reset steps", Toast.LENGTH_LONG).show()
        }
        binding!!.tvStepsTaken.setOnLongClickListener {
            previousTotalSteps = totalSteps
            binding!!.tvStepsTaken.text = "0"
            saveData()

            true
        }

    }

    private fun resetSteps(){
        binding!!.tvStepsTaken.text = "0"
        binding!!.steps.text="0"
        binding!!.cvGoalAchieved.visibility = View.INVISIBLE
        binding!!.tvStepGoal.visibility = View.INVISIBLE
        binding!!.tvStepsTaken.setTextColor(Color.parseColor("#000000"))
        stepGoalSet = false
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("key1", previousTotalSteps.toFloat())
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getFloat("key1", 0f)
        Log.d("QPedometer", "$savedNumber")
        previousTotalSteps = savedNumber.toDouble()
    }
}