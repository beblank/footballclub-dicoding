package com.adit.footballclub.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adit.footballclub.R
import com.adit.footballclub.adapter.PlayerAdapter
import com.adit.footballclub.entity.Player
import com.adit.footballclub.ext.hide
import com.adit.footballclub.ext.show
import com.adit.footballclub.viewmodel.PlayerViewModel
import com.adit.footballclub.viewmodel.TeamViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_event.*
import kotlinx.android.synthetic.main.fragment_player_list.*
import javax.inject.Inject

class PlayerListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var playerViewModel: PlayerViewModel
    lateinit var teamViewModel: TeamViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_list, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        teamViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[TeamViewModel::class.java]
        playerViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[PlayerViewModel::class.java]
        playerViewModel.getAllPlayer().observe(this, Observer {
            if (it != null){
                progressbarPlayerList.hide()
                rvPlayerList.show()
                initRV(it)
            }
        })
        teamViewModel.getSelectedTeam().observe(this, Observer{
            if (it != null){
                playerViewModel.getAllPlayerFromApi(it.teamName)
            }
        })
    }

    private fun initRV(it: List<Player>) {
        rvMatch.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvMatch.adapter = PlayerAdapter(it)
    }


}
