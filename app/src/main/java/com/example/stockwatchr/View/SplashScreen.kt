package com.example.stockwatchr.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.stockwatchr.R
import com.example.stockwatchr.databinding.SplashActivityBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen: AppCompatActivity() {

    private var SPLASH_SCREEN_TIME : Long = 3500
    private lateinit var binding: SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.visibility = View.VISIBLE


        lifecycleScope.launch {
            try {
                delay(SPLASH_SCREEN_TIME)
                startMainActivity()

            } catch (e: Exception) {
                e.printStackTrace()
                startMainActivity()
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this@SplashScreen, MainActivity::class.java))
        finish()
    }
}