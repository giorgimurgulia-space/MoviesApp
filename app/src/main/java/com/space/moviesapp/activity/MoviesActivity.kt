package com.space.moviesapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.moviesapp.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}