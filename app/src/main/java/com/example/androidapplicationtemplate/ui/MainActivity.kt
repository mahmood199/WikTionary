package com.example.androidapplicationtemplate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidapplicationtemplate.databinding.ActivityMainBinding
import com.example.androidapplicationtemplate.ui.search_result.WikiActivity

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.btnGoDifferentScreen.setOnClickListener {
			startActivity(Intent(this, WikiActivity::class.java))
		}

	}
}