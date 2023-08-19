package com.grewal.rgflash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grewal.rgflash.databinding.ActivityMainBinding
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binder=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        val executor: Executor = Executors.newSingleThreadExecutor()
        executor.execute {
            Thread.sleep(2000)
            runOnUiThread {
                startActivity(Intent(this, FlashActivity::class.java))
                finish()
            }
        }
    }
}