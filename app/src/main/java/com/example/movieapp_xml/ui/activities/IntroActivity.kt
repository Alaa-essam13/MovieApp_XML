package com.example.movieapp_xml.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp_xml.R

class IntroActivity : AppCompatActivity() {
    lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)
        btn = findViewById(R.id.ItroBtn)
        btn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}