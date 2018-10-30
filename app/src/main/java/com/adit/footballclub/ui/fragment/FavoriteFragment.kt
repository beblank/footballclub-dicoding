package com.adit.footballclub.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.adit.footballclub.R
import com.adit.footballclub.adapter.ClubAdapter
import com.adit.footballclub.entity.Events
import com.adit.footballclub.ext.hide
import com.adit.footballclub.ext.show
import com.adit.footballclub.viewmodel.ActivityMainViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject


class FavoriteFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var activityMainViewModel: ActivityMainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onStart() {
        super.onStart()
        activityMainViewModel = ViewModelProviders.of(this, viewModelFactory)[ActivityMainViewModel::class.java]
        activityMainViewModel.getListEvents().observe(this, Observer {
            if (it != null){
                progressbarFavMatch.hide()
                rvMatchFav.show()
                initRV(it)
            }
        })
        activityMainViewModel.getListEventsError().observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        progressbarFavMatch.show()
        rvMatchFav.hide()
        activityMainViewModel.getEventsfromDB()
    }

    private fun initRV(it: List<Events>) {
        Log.d("dodol", "$it")
        rvMatchFav.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = ClubAdapter(it)
        adapter.notifyDataSetChanged()
        rvMatchFav.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        activityMainViewModel.getListEvents().value = null

    }
}
