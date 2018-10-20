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
import com.adit.footballclub.viewmodel.ActivityMainViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_last_match.*
import javax.inject.Inject

class LastMatchFragment : Fragment() {

    @Inject
    lateinit var activityMainViewModelFactory: ActivityMainViewModelFactory

    lateinit var activityMainViewModel: ActivityMainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityMainViewModel = ViewModelProviders.of(requireActivity(), activityMainViewModelFactory).get(ActivityMainViewModel::class.java)
        activityMainViewModel.getListEvents().observe(this, Observer {
            if (it != null){
                progressbarLastMatch.hide()
                rvClubLast.show()
                initRV(it)
            }
        })
        activityMainViewModel.getListEventsError().observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        activityMainViewModel.getSelectedTab().observe(requireActivity(), Observer {
            progressbarLastMatch.show()
            rvClubLast.hide()
            activityMainViewModel.getEvents(it ?: 0)
        })
        progressbarLastMatch.visibility = View.VISIBLE
    }

    private fun initRV(it: List<Events>) {
        Log.d("dodol", "$it")
        rvClubLast.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvClubLast.adapter = ClubAdapter(it)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainViewModel.disposeElements()
    }
}
