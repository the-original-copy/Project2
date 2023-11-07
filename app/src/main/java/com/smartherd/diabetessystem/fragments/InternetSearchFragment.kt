package com.smartherd.diabetessystem.fragments

import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smartherd.diabetessystem.databinding.FragmentInternetSearchBinding
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class InternetSearchFragment : Fragment() {
    private var binding: FragmentInternetSearchBinding? = null
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInternetSearchBinding.inflate(inflater, container, false)
        val view = binding!!.root
        binding!!.btnInternetSearch.setOnClickListener {
            val query = binding!!.textInputEditText.text.toString()
            Log.d("Query",query)
            if (query.isNotEmpty())
                searchWithChatGPT(query) { response ->
                    activity?.runOnUiThread {
                        run {
                            binding!!.tvResponse.text = response
                        }
                    }
                }
        }
        // Inflate the layout for this fragment
        return view
    }

    private fun searchWithChatGPT(query: String, callback: (String) -> Unit) {
        Log.d("Query 1",query)
        val apiKey = "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"
        val url = "https://api.openai.com/v1/completions"

        val requestBody = """
            {
            "model": "gpt-3.5-turbo-instruct",
            "prompt": "$query",
            "max_tokens": 700,
            "temperature": 0
             }  
        """.trimIndent()
        Log.d("Query 2",query)
        val request = Request.Builder()
            .url(url)
            .header("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API error","API failed",e)
            }

            override fun onResponse(call: Call, response: Response) {
               val body = response.body?.string()
                if (body != null) {
                    Log.d("ChatGpt response",body)
                    val jsonObject = JSONObject(body)
                    val jsonArray:JSONArray = jsonObject.getJSONArray("choices")
                    val text = jsonArray.getJSONObject(0).getString("text").substring(1)
                    callback(text)
                } else {
                    Log.d("ChatGpt response","Empty")
                }
            }

        })
    }
}