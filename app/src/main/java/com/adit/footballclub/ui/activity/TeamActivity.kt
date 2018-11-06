package com.adit.footballclub.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.adit.footballclub.R
import com.adit.footballclub.entity.Team
import com.adit.footballclub.ui.fragment.TeamDetailFragment
import com.adit.footballclub.ui.fragment.ViewPagerAdapter
import com.adit.footballclub.utils.Const
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    lateinit var team:Team


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        AndroidInjection.inject(this)
       if(savedInstanceState != null)
           team = intent.getParcelableExtra(Const.team)
        setupViewPager(team_viewpager)
        toolbar.title = team.teamName
        team_tabs.setupWithViewPager(team_viewpager)
        team_tabs.addOnTabSelectedListener(this)

    }

    private fun setupViewPager(vp: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager!!)
        adapter.addFragment(TeamDetailFragment(), resources.getString(R.string.last_match))
        adapter.addFragment(TeamDetailFragment(), resources.getString(R.string.upcoming))
        vp.adapter = adapter
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
    }
}
