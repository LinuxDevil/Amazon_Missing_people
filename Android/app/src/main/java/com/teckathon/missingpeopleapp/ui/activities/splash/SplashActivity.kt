package com.teckathon.missingpeopleapp.ui.activities.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.teckathon.missingpeopleapp.R
import com.teckathon.missingpeopleapp.ui.activities.auth.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        animateCircle(1880f, 3000)

        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(it)
            }
        }, 3000)

    }

    private fun animateCircle(rotation: Float, millis: Long) {
        circle_1.animate().rotationBy(rotation).setDuration(millis).start()
        circle_2.animate().rotationBy(rotation).setDuration(millis).alpha(1f).start()
        circle_3.animate().rotationBy(rotation).setDuration(millis).alpha(1f).start()
        circle_4.animate().rotationBy(rotation).setDuration(millis).alpha(1f).start()
        circle_5.animate().rotationBy(rotation).setDuration(millis).alpha(1f).start()
    }
}