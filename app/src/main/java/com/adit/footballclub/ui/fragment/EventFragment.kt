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
import com.adit.footballclub.adapter.EventAdapter
import com.adit.footballclub.entity.Events
import com.adit.footballclub.ext.hide
import com.adit.footballclub.ext.show
import com.adit.footballclub.utils.Const
import com.adit.footballclub.viewmodel.EventViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_event.*
import javax.inject.Inject


class EventFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var eventViewModel: EventViewModel
    lateinit var leagueID:String
    var tabPos:Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[EventViewModel::class.java]
        eventViewModel.getListEvents().observe(this, Observer {
            if (it != null){
                progressbarMatch.hide()
                rvMatch.show()
                initRV(it)
            }
        })
        eventViewModel.getIsFiltered().observe(this, Observer{
            if (eventViewModel.getListEvents().value != null && eventViewModel.getFilteredEvent().value != null){
                if(it!!)
                    initRV(eventViewModel.getFilteredEvent().value!!)
                else
                    initRV(eventViewModel.getListEvents().value!!)
            }
        })
        eventViewModel.getLeagueID().observe(this, Observer {
            progressbarMatch.show()
            rvMatch.hide()
            leagueID = it ?: Const.id
            eventViewModel.getEventsfromApi(it ?: Const.id, tabPos)
        })
        eventViewModel.getListEventsError().observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        eventViewModel.getSelectedTab().observe(this, Observer{
            progressbarMatch.show()
            rvMatch.hide()
            tabPos = it ?: 0
            eventViewModel.getEventsfromApi(leagueID, it ?: 0)
        })
    }

    private fun initRV(it: List<Events>) {
        rvMatch.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvMatch.adapter = EventAdapter(it)
    }
}
