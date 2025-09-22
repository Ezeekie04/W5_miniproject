package com.example.w5

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageView = ImageView(this)
        setContentView(imageView)

        val imageUrl = intent.getStringExtra("image_url")
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
    }
}