package com.appkotlin.activities

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.appkotlin.R
import com.appkotlin.databinding.ActivityLoginBinding
import com.appkotlin.service.MyService

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val userName: String = "mohamed"
    val password: String = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initView()
    }

    private fun initView() {
        binding.btnLogin.setOnClickListener {
            if (!binding.etUserName.text.toString()
                    .isNullOrEmpty() && !binding.etPassword.text.toString().isNullOrEmpty()
            ) {
                if (binding.etUserName.text.toString()
                        .equals(userName) && binding.etPassword.text.toString().equals(password)
                ) {
                    var intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("name", userName)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid Login", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Plz Enter your data", Toast.LENGTH_LONG).show()

            }

        }
        binding.btnService.setOnClickListener {
            startStopService()
        }
    }

    private fun startStopService() {
        if (isMyServiceRunning(MyService::class.java)) {
            Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show()
            stopService(Intent(this, MyService::class.java))
        } else {
            Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show()
            startService(Intent(this, MyService::class.java))
        }
    }

    private fun isMyServiceRunning(mClass: Class<MyService>): Boolean {
        val manager: ActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for (service: ActivityManager.RunningServiceInfo in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (mClass.name.equals(service.service.className)) {
                return true
            }
        }
        return false
    }
}