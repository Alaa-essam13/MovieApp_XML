package com.example.movieapp_xml.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.movieapp_xml.R
import com.example.movieapp_xml.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.button2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when{
            v == binding.button2 -> {
                if (!binding.editTextUserName.text.isEmpty()||!binding.editTextPassword.text.isEmpty()){
                    Toast.makeText(this,"Hello mr 3laa",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    Toast.makeText(this,"UserName and Password are required",Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                // Navigate to Register Activity
            }
        }
    }
}