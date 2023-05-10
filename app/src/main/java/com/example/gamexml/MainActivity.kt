package com.example.gamexml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import com.example.gamexml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var surfaceView: SurfaceView
    private lateinit var roulette: Roulette
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        surfaceView = binding.surfaceView
        roulette = Roulette(surfaceView, this, binding)


        val button = binding.btPlay
        button.setOnClickListener {
            roulette.spinRoulette(binding)
        }
    }
}