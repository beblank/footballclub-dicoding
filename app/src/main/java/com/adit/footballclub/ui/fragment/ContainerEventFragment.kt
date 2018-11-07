package com.adit.footballclub.ui.fragment


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import com.adit.footballclub.R
import com.adit.footballclub.utils.Const
import com.adit.footballclub.viewmodel.EventViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_container_event.*
import javax.inject.Inject

class ContainerEventFragment : Fragment(), TabLayout.OnTabSelectedListener, AdapterView.OnItemSelectedListener {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var eventViewModel: EventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container_event, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager(viewpager)

        tabs.setupWithViewPager(viewpager)

        tabs.addOnTabSelectedListener(this)

        spinner_event.onItemSelectedListener = this

        eventViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[EventViewModel::class.java]

    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val league = spinner_event.selectedItem.toString()
        when(league){
            "English Premier League" -> eventViewModel.getLeagueID().value = "4328"
            "German Bundesliga" -> eventViewModel.getLeagueID().value = "4331"
            "Italian Serie A" -> eventViewModel.getLeagueID().value = "4332"
            "French Ligue 1" -> eventViewModel.getLeagueID().value = "4334"
            "Spanish La Liga" -> eventViewModel.getLeagueID().value = "4335"
            "Netherlands Eredivisie" -> eventViewModel.getLeagueID().value = "4337"
            else -> eventViewModel.getLeagueID().value = Const.id
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab) {}

    override fun onTabUnselected(p0: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab) {
        eventViewModel.getSelectedTab().value = tab.position
    }

    private fun setupViewPager(viewpager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(EventFragment(), resources.getString(R.string.upcoming))
        adapter.addFragment(EventFragment(), resources.getString(R.string.last_match))
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
