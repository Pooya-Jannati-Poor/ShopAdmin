package ir.arinateam.shopadmin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.arinateam.shopadmin.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onPause() {
        super.onPause()

        finishAffinity()

    }

}