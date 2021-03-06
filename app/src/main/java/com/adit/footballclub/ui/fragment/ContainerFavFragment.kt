package com.adit.footballclub.ui.fragment


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.adit.footballclub.R
import com.adit.footballclub.adapter.ViewPagerAdapter
import com.adit.footballclub.viewmodel.EventViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_container_fav.*
import javax.inject.Inject

class ContainerFavFragment : Fragment(), TabLayout.OnTabSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var eventViewModel: EventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container_fav, container, false)
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

        eventViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[EventViewModel::class.java]
    }

    override fun onTabReselected(tab: TabLayout.Tab) {}

    override fun onTabUnselected(p0: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab) {
        eventViewModel.getSelectedTab().value = tab.position
    }

    private fun setupViewPager(viewpager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FavoriteFragment(), resources.getString(R.string.matches))
        adapter.addFragment(FavoriteFragment(), resources.getString(R.string.teams))
        viewpager.setAdapter(adapter)
    }
}
