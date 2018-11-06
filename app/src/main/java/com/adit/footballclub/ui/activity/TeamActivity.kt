package com.adit.footballclub.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.adit.footballclub.R
import com.adit.footballclub.ui.fragment.TeamDetailFragment
import com.adit.footballclub.ui.fragment.ViewPagerAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        AndroidInjection.inject(this)
        setupViewPager(team_viewpager)
        team_tabs.setupWithViewPager(team_viewpager)
        team_tabs.addOnTabSelectedListener(this)
    }

    private fun setupViewPager(vp: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager!!)
        adapter.addFragment(TeamDetailFragment(), resources.getString(R.string.last_match))
        adapter.addFragment(TeamDetailFragment(), resources.getString(R.string.upcoming))
        vp.setAdapter(adapter)
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
    }
}
