package com.adit.footballclub.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.adit.footballclub.R
import com.adit.footballclub.viewmodel.ActivityMainViewModel
import com.adit.footballclub.viewmodel.ActivityMainViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var activityMaiViewModelFactory: ActivityMainViewModelFactory

    lateinit var activityMainViewModel: ActivityMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_bottom.setupWithNavController(Navigation.findNavController(this, R.id.nav_host_fragment))

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.main_title)

        AndroidInjection.inject(this)

        activityMainViewModel = ViewModelProviders.of(this, activityMaiViewModelFactory).get(ActivityMainViewModel::class.java)
    }

    override fun onDestroy() {
        activityMainViewModel.disposeElements()
        super.onDestroy()
    }

}
