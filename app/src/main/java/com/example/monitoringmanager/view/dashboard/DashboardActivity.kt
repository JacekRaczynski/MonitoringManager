package com.example.monitoringmanager.view.dashboard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.monitoringmanager.R

fun Context.DashboardActivityIntent(): Intent {
    return Intent(this, DashboardActivity::class.java)
}
class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }
}