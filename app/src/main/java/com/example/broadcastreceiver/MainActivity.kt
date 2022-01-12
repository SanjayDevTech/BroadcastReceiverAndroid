package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator

class MainActivity : AppCompatActivity() {private lateinit var textView: TextView
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val text = intent?.extras?.getString("text") ?: return
            textView.text = text
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)
        registerReceiver(receiver, IntentFilter("com.example.receiver.aclient"))
        button.setOnClickListener {
            sendBroadcast(Intent("com.example.receiver.bclient").apply {
                putExtra("text", RandomWordGenerator.getRandomWord())
            })
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}