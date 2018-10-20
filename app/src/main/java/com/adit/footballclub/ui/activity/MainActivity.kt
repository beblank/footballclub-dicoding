package com.adit.footballclub.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.adit.footballclub.ui.fragment.LastMatchFragment
import com.adit.footballclub.R
import com.adit.footballclub.viewmodel.ActivityMainViewModel
import com.adit.footballclub.viewmodel.ActivityMainViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    @Inject
    lateinit var activityMaiViewModelFactory: ActivityMainViewModelFactory

    lateinit var activityMainViewModel: ActivityMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.main_title)

        setupViewPager(viewpager)

        tabs.setupWithViewPager(viewpager)
        AndroidInjection.inject(this)

        tabs.addOnTabSelectedListener(this)

        activityMainViewModel = ViewModelProviders.of(this, activityMaiViewModelFactory).get(ActivityMainViewModel::class.java)
    }

    override fun onTabReselected(tab: TabLayout.Tab) {}

    override fun onTabUnselected(p0: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab) {
        activityMainViewModel.getSelectedTab().value = tab.position
    }

    private fun setupViewPager(viewpager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LastMatchFragment(), resources.getString(R.string.last_match))
        adapter.addFragment(LastMatchFragment(), resources.getString(R.string.next_match))
        viewpager.setAdapter(adapter)
    }

}

class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }
}
