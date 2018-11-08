package com.adit.footballclub.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.adit.footballclub.R
import com.adit.footballclub.adapter.ViewPagerAdapter
import com.adit.footballclub.entity.Team
import com.adit.footballclub.ui.fragment.PlayerListFragment
import com.adit.footballclub.ui.fragment.TeamDetailFragment
import com.adit.footballclub.utils.Const
import com.adit.footballclub.viewmodel.TeamViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_team.*
import javax.inject.Inject

class TeamActivity : AppCompatActivity(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var teamViewModel: TeamViewModel
    lateinit var menu:Menu
    lateinit var team:Team

    private var isFavorite = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        AndroidInjection.inject(this)
        team = intent.getParcelableExtra(Const.team)
        teamViewModel = ViewModelProviders.of(this, viewModelFactory)[TeamViewModel::class.java]

        setToolbar()
        setViewModel()
        setupViewPager(team_viewpager)
        setupHeader()
        team_tabs.setupWithViewPager(team_viewpager)
    }

    private fun setViewModel() {
        teamViewModel = ViewModelProviders.of(this, viewModelFactory)[TeamViewModel::class.java]
        teamViewModel.getSelectedTeam().value = team

        teamViewModel.getFavTeam().observe(this, Observer{
            isFavorite = true
            invalidateOptionsMenu()
        })
        teamViewModel.getInsertStatus().observe(this, Observer {
            if(it!!){
                Snackbar.make(content, "Event saved to Database", Snackbar.LENGTH_SHORT).show()
            }
        })
        teamViewModel.getDeleteStatus().observe(this, Observer {
            if(it!!){
                Snackbar.make(content, "Event deleted from Database", Snackbar.LENGTH_SHORT).show()
            }
        })

        teamViewModel.checkTeam(team.teamId)
    }

    private fun setupHeader() {
        Picasso.get().load(team.teamLogo).into(team_image)
        team_name.text = team.teamName
        team_year.text = team.teamFormedYear
        team_stadium.text = team.teamStadium
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupViewPager(vp: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager!!)
        adapter.addFragment(TeamDetailFragment(), resources.getString(R.string.overview))
        adapter.addFragment(PlayerListFragment(), resources.getString(R.string.players))
        vp.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    private fun setFavorite() {
        if (!isFavorite) {
            menu.getItem(0).icon = getDrawable(R.drawable.ic_add_to_favorites)
        } else {
            menu.getItem(0).icon = getDrawable(R.drawable.ic_added_to_favorites)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_fav){
            if (isFavorite){
                teamViewModel.deleteTeam(team)
                isFavorite = false
            } else {
                teamViewModel.insertTeam(team)
                isFavorite = true
            }
            setFavorite()
            invalidateOptionsMenu()
        }
        return super.onOptionsItemSelected(item)
    }
}
