package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ViewAdapter
    private val retrofitService: CatApi by lazy {
        RetrofitService.getClient("https://api.api-ninjas.com/v1/").create(CatApi::class.java)
    }
    private val apiKey = "9ViVeKfYskMZ8w6ayLwlSg==s3TvxGzTTff5dlPn"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ViewAdapter(emptyList())
        recyclerView.adapter = adapter
        val searchEditText: EditText = findViewById(R.id.editText)
        val parameterEditText: EditText = findViewById(R.id.editTextParameter)
        findViewById<Button>(R.id.search_button).setOnClickListener {
            val parameter = parameterEditText.text.toString()
            val value = searchEditText.text.toString()
            if (value.isNotEmpty()) {
                performSearch(parameter, value)
            } else {
                Toast.makeText(this, "Введите значение для поиска", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun performSearch(parameter: String, value: String) {
        val options = mapOf(parameter to value)
        val call = retrofitService.getCatsByParameter(options, apiKey)
        call.enqueue(object : Callback<List<CatModel>> {
            override fun onResponse(call: Call<List<CatModel>>, response: Response<List<CatModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let { adapter.setData(it) }
                } else {
                    Log.e("API_CALL", "Failed to fetch data: ${response.code()}, ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<CatModel>>, t: Throwable) {
                Log.e("API_CALL", "Failed to fetch data: ${t.message}", t)
            }
        })
    }
}





