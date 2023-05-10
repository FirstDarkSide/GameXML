package com.example.gamexml

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.example.gamexml.databinding.ActivityMainBinding
import kotlin.random.Random

class Roulette(var surfaceView: SurfaceView, binding: ActivityMainBinding) : SurfaceHolder.Callback, Runnable {
    private var holder = surfaceView.holder
    private var thread: Thread? = null
    @Volatile private var isRunning: Boolean = false

    private val roulettee = binding.rouletteImg
    private var randomNumber: String = ""
override fun run() {
    }
    fun start() {
        isRunning = true
        holder.addCallback(this)
        thread = Thread(this)
        thread!!.start()
    }

    fun stop() {
        isRunning = false
        surfaceView.holder.removeCallback(this)
    }

    @SuppressLint("Recycle")
    fun spinRoulette(binding: ActivityMainBinding) {
        randomNumber = numbers.random()
        val rotationAngle = Random.nextFloat() * 360 + 3600
        val animation = ObjectAnimator.ofFloat(roulettee, "rotation", 0f,  rotationAngle )
        animation.interpolator = AccelerateDecelerateInterpolator()
        animation.duration = 4000L


        animation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                binding.tvWin.visibility = View.GONE
                binding.tvLose.visibility = View.GONE
                binding.tvIndex.text = ""


            }
            @SuppressLint("SetTextI18n")
            override fun onAnimationEnd(animation: Animator) {
                val indexCalc = (360 - (rotationAngle - 3600)) / (360f / 38)
                val index = numbers[indexCalc.toInt()]
                binding.tvIndex.text = "Результат: $index"
                if (binding.tvBet.text.toString() == index)
                {
                    binding.tvWin.visibility = View.VISIBLE
                }
                else {binding.tvLose.visibility = View.VISIBLE
                }

            }
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        animation.start()
        binding.tvBet.text = binding.etBetting.text
}

    override fun surfaceCreated(holder: SurfaceHolder) {
        start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        stop()    }
}

