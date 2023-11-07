package com.smartherd.diabetessystem.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.smartherd.diabetessystem.R
import com.smartherd.diabetessystem.databinding.ActivityPredictDiabetesPageBinding
import com.smartherd.diabetessystem.databinding.DialogDiabetesPedigreeFunctionBinding
import kotlinx.android.synthetic.main.activity_predict_diabetes_page.btn_pedometer_settings
import kotlinx.android.synthetic.main.activity_predict_diabetes_page.cv_enter_data
import kotlinx.android.synthetic.main.activity_predict_diabetes_page.cv_prediction_result
import kotlinx.android.synthetic.main.activity_predict_diabetes_page.toolbar_predict_diabetes
import kotlinx.android.synthetic.main.activity_predict_diabetes_page.tv_title
import org.json.JSONException
import org.json.JSONObject

class PredictDiabetesPage : AppCompatActivity() {
    var binding: ActivityPredictDiabetesPageBinding? = null
    var dialogView: DialogDiabetesPedigreeFunctionBinding? = null
    var dpf: String = ""
    var height = ""
    var bmi = ""
    var age = ""
    val url: String = "https://flaskapi-122992-eric-kithae-a-98e28377a721.herokuapp.com/predict"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictDiabetesPageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setUpActionBar()
        height = binding!!.height.getText().toString()
        bmi = binding!!.bmi.text.toString()
        age = binding!!.age.text.toString()

        binding!!.dpf.isClickable = false
        binding!!.dpf.setOnClickListener {
            show_dfp_Dialog()
        }
        btn_pedometer_settings.setOnClickListener {
            Log.d("Height", binding!!.dpf.getText().toString())
            predict(binding!!.height.getText().toString(),binding!!.bmi.text.toString(),binding!!.dpf.getText().toString(),binding!!.age.text.toString(), url)
            tv_title.setText("Prediction Results")
            cv_enter_data.visibility = View.GONE
            cv_prediction_result.visibility = View.VISIBLE
        }

    }

    private fun show_dfp_Dialog(){
        val dialog = Dialog(this)
        dialogView = DialogDiabetesPedigreeFunctionBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(dialogView!!.root)
        dialogView!!.tvAdd.setOnClickListener {
            val numberOfParents = dialogView!!.etNumberOfParents.text.toString().toDouble()
            val numberOfSiblings = dialogView!!.etNumberOfSiblings.text.toString().toDouble()
            val numberOfGrandParents = dialogView!!.etNumberOfGrandParents.text.toString().toDouble()
             val doubledpf = multiplyNumbers(0.5,numberOfParents)+multiplyNumbers(0.25,numberOfSiblings)+multiplyNumbers(0.125,numberOfGrandParents)
            dpf = doubledpf.toString()
            binding!!.dpf.setText(dpf)
            dialog.dismiss()
        }
        dialogView!!.tvCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun predict(height: String,bmi: String,dpf: String,age: String,url: String) {
        val stringRequest = object : StringRequest(Method.POST,url,Response.Listener<String> { response ->
            try {
               val jsonObject = JSONObject(response)
               val data = jsonObject.getString("Result")
                if(data.isNotEmpty())
                    binding!!.result.setText(data.toString())
            }catch(e:JSONException) {
                e.printStackTrace()
            }
        },
        Response.ErrorListener { error ->
            Toast.makeText(this@PredictDiabetesPage,error.message.toString(),Toast.LENGTH_SHORT).show()
            Log.e("VolleyError",error.message.toString())
        }) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String,String>()
                params.put("cgpa",height)
                params.put("BMI",bmi)
                params.put("DPF",dpf)
                params.put("age",age)
                for((key,value) in params){
                    Log.d("HashMap: ","Key: $key Value: $value")
                }
                return params
            }
        }
        val queue = Volley.newRequestQueue(this@PredictDiabetesPage)
        queue.add(stringRequest)

    }

    private fun multiplyNumbers(a: Double,b: Double) : Double {
        return a * b
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar_predict_diabetes)
        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.arrow_back_black)
        }
        toolbar_predict_diabetes.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}