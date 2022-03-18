package com.toxicflame427.truerandomnumbergenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread{
            Thread.sleep(2500)
            startActivity(Intent(this, MainActivity::class.java))
        }.start()
    }
}