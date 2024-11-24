package com.enti.dani_pau_marc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var dsada: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)


        dsada = findViewById(R.id.splashScreenButton)

        dsada.setOnClickListener{
            ChangeActivity()
        }
    }

    fun ChangeActivity(){
        startActivity(Intent(this, SelectorLevelActivity::class.java));
    }

}