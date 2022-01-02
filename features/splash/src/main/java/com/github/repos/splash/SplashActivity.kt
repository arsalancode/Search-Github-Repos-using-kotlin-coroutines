package com.github.repos.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.feliperrm.utils.getViewModel
import com.github.repos.base.BaseActivity
import com.github.repos.core.utils.hideSystemUI
import com.github.repos.navigation.AppNavigation
import com.github.repos.splash.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContentView(ActivitySplashBinding.inflate(layoutInflater).root)

        splashViewModel.launchHomeScreen.observe(this, { launch ->

            if (launch){
                startActivity(AppNavigation.openHome())
            }

        })

    }
}
