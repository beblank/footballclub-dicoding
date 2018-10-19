package com.adit.footballclub.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.adit.footballclub.LastMatchFragment
import com.adit.footballclub.NextMatchFragment
import com.adit.footballclub.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupViewPager(viewpager)

        tabs.setupWithViewPager(viewpager)
        AndroidInjection.inject(this)
    }

    private fun setupViewPager(viewpager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LastMatchFragment(), resources.getString(R.string.last_match))
        adapter.addFragment(NextMatchFragment(), resources.getString(R.string.next_match))
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
