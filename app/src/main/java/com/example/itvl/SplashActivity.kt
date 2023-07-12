package com.example.itvl

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val progressBar = findViewById<ProgressBar>(R.id.progressBarsplash)
        val animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        animator.duration = 1500 // Duración de la animación en milisegundos
        animator.start()


        val backgrouning : ImageView = findViewById(R.id.imgplash)
        backgrouning.startAnimation(androidx.appcompat.R.anim.abc_fade_in)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, 1500)




    }
}

private fun ImageView.startAnimation(abcFadeOut: Int) {

}
