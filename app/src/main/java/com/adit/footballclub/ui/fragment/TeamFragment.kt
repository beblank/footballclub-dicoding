package com.adit.footballclub.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.adit.footballclub.R
import com.adit.footballclub.adapter.TeamAdapter
import com.adit.footballclub.entity.Team
import com.adit.footballclub.ext.hide
import com.adit.footballclub.ext.show
import com.adit.footballclub.viewmodel.TeamViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_team.*
import javax.inject.Inject

class TeamFragment : Fragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var teamViewModel: TeamViewModel

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
        teamViewModel = ViewModelProviders.of(this, viewModelFactory)[TeamViewModel::class.java]
        teamViewModel.getAllTeam().observe(this, Observer {
            if (it != null){
                progressbarTeam.hide()
                rvTeam.show()
                initRV(it)
            }
        })
        teamViewModel.getAllTeamError().observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        spinner_league.onItemSelectedListener = this
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        val league = spinner_league.selectedItem.toString()
        progressbarTeam.show()
        rvTeam.hide()
        teamViewModel.getAllTeamByName(league)
    }

    private fun initRV(it: List<Team>) {
        rvTeam.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvTeam.adapter = TeamAdapter(it)
    }

}
