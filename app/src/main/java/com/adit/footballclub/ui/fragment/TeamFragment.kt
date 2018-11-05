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
import com.adit.footballclub.utils.Const
import com.adit.footballclub.viewmodel.EventViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_team.*
import javax.inject.Inject

class TeamFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var eventViewModel: EventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventViewModel = ViewModelProviders.of(this, viewModelFactory)[EventViewModel::class.java]
        eventViewModel.getListEvents().observe(this, Observer {
            if (it != null){
                progressbarTeam.hide()
                rvTeam.show()
                initRV(it)
            }
        })
        eventViewModel.getListEventsError().observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        progressbarTeam.show()
        rvTeam.hide()
        eventViewModel.getEventsfromApi(Const.id, Const.nextMatchTab)
    }

    private fun initRV(it: List<Events>) {
        Log.d("dodol", "$it")
        rvTeam.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvTeam.adapter = ClubAdapter(it)
    }

}
