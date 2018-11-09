package com.adit.footballclub.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.adit.footballclub.R
import com.adit.footballclub.viewmodel.EventViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import javax.inject.Inject
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import com.adit.footballclub.viewmodel.TeamViewModel


class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var eventViewModel: EventViewModel
    lateinit var teamViewModel: TeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_bottom.setupWithNavController(Navigation.findNavController(this, R.id.nav_host_fragment))

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.main_title)

        AndroidInjection.inject(this)

        eventViewModel = ViewModelProviders.of(this, viewModelFactory)[EventViewModel::class.java]
        teamViewModel = ViewModelProviders.of(this, viewModelFactory)[TeamViewModel::class.java]
    }

    override fun onDestroy() {
        eventViewModel.disposeElements()
        teamViewModel.disposeElements()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (!query.isNullOrBlank()){
            eventViewModel.getIsFiltered().value = true
            teamViewModel.getIsFiltered().value = true
            eventViewModel.filterEvent(query)
            teamViewModel.filterTeam(query)
        } else {
            eventViewModel.getIsFiltered().value = false
            teamViewModel.getIsFiltered().value = false
        }
        return true
    }

}
