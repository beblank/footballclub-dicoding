package com.adit.footballclub.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.adit.footballclub.R
import com.adit.footballclub.entity.Team
import com.adit.footballclub.ui.fragment.PlayerListFragment
import com.adit.footballclub.ui.fragment.TeamDetailFragment
import com.adit.footballclub.ui.fragment.ViewPagerAdapter
import com.adit.footballclub.utils.Const
import com.adit.footballclub.viewmodel.TeamViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_team.*
import javax.inject.Inject

class TeamActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var team:Team
    lateinit var teamViewModel: TeamViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        AndroidInjection.inject(this)
        team = intent.getParcelableExtra(Const.team)
        teamViewModel = ViewModelProviders.of(this, viewModelFactory)[TeamViewModel::class.java]
        teamViewModel.getSelectedTeam().value = team
        setupViewPager(team_viewpager)
        setupHeader()
        team_tabs.setupWithViewPager(team_viewpager)
        team_tabs.addOnTabSelectedListener(this)

    }

    private fun setupHeader() {
        Picasso.get().load(team.teamLogo).into(team_image)
        team_name.text = team.teamName
        team_year.text = team.teamFormedYear
        team_stadium.text = team.teamStadium
    }

    private fun setupViewPager(vp: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager!!)
        adapter.addFragment(TeamDetailFragment(), resources.getString(R.string.overview))
        adapter.addFragment(PlayerListFragment(), resources.getString(R.string.players))
        vp.adapter = adapter
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
    }
}
