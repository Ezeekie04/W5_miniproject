package com.example.w5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.w5.api.RetrofitInstance
import com.example.w5.model.DogResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var dogImage: ImageView
    private lateinit var btnLoad: Button
    private var currentImageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogImage = findViewById(R.id.dogImage)
        btnLoad = findViewById(R.id.btnLoad)

        btnLoad.setOnClickListener { loadDogImage() }

        dogImage.setOnClickListener {
            currentImageUrl?.let {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("image_url", it)
                startActivity(intent)
            }
        }
    }

    private fun loadDogImage() {
        RetrofitInstance.api.getRandomDog().enqueue(object : Callback<DogResponse> {
            override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {
                if (response.isSuccessful) {
                    val imageUrl = response.body()?.imageUrl
                    currentImageUrl = imageUrl
                    Glide.with(this@MainActivity)
                        .load(imageUrl)
                        .into(dogImage)
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load image", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
