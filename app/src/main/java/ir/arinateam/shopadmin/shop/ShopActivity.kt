package ir.arinateam.shopadmin.shop

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import ir.arinateam.shopadmin.R
import ir.arinateam.shopadmin.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {

    private lateinit var bindingActivity: ActivityShopBinding

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = DataBindingUtil.setContentView(this, R.layout.activity_shop)

        initView()

        val navController = findNavController(R.id.fragment_controller_main)

        bottomNav.setupWithNavController(navController)

    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    private fun initView() {

        bottomNav = bindingActivity.bottomNav

    }

}