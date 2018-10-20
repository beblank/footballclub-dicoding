package com.adit.footballclub.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adit.footballclub.R
import com.adit.footballclub.Utils.Const
import com.adit.footballclub.Utils.Utils
import com.adit.footballclub.entity.Events
import com.adit.footballclub.viewmodel.DetailActivityViewModel
import com.adit.footballclub.viewmodel.DetailActivityViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_detail_defense.*
import kotlinx.android.synthetic.main.item_detail_forward.*
import kotlinx.android.synthetic.main.item_detail_goalkeeper.*
import kotlinx.android.synthetic.main.item_detail_goals.*
import kotlinx.android.synthetic.main.item_detail_midfield.*
import kotlinx.android.synthetic.main.item_detail_shots.*
import kotlinx.android.synthetic.main.item_detail_substitutes.*
import kotlinx.android.synthetic.main.match_detail_header_item.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var detailActivityViewModelFactory:DetailActivityViewModelFactory

    lateinit var detailActivityViewModel: DetailActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        AndroidInjection.inject(this)

        val event = intent.getParcelableExtra<Events>(Const.event)

        setToolbar()
        setViewModel(event)


        initView(event)
    }

    private fun setViewModel(event: Events) {
        detailActivityViewModel = ViewModelProviders.of(this, detailActivityViewModelFactory)
                .get(DetailActivityViewModel::class.java)

        detailActivityViewModel.getListAwayTeam().observe(this, Observer{
            if (it != null){
                Picasso.get().load(it.logo).into(img_away)
            }
        })
        detailActivityViewModel.getListHomeTeam().observe(this, Observer{
            if (it != null){
                Picasso.get().load(it.logo).into(img_home)
            }
        })
        detailActivityViewModel.getListTeamError().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        detailActivityViewModel.getAwayTeams(event.idAway)
        detailActivityViewModel.getHomeTeams(event.idHome)
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.detail_title)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initView(event: Events) {
        txt_home_name.text = event.nameHomeTeam
        txt_home_score.text = event.homeScore
        txt_away_name.text = event.nameAwayTeam
        txt_away_score.text = event.awayScore
        txt_date.text = Utils.dateStringConverter(event.dateStr)

        txt_home_shots.text = event.homeShots
        txt_home_goals.text = event.homeGoalDetails
        txt_home_goalkeeper.text = event.homeLineupGoalkeeper
        txt_home_defense.text = event.homeLineupDefense
        txt_home_midfield.text = event.homeLineupMidfield
        txt_home_forward.text = event.homeLineupForward
        txt_home_substitutes.text = event.homeLineupSubstitutes

        txt_away_shots.text = event.awayShots
        txt_away_goals.text = event.awayGoalDetails
        txt_away_goalkeeper.text = event.awayLineupGoalkeeper
        txt_away_defense.text = event.awayLineupDefense
        txt_away_midfield.text = event.awayLineupMidfield
        txt_away_forward.text = event.awayLineupForward
        txt_away_substitutes.text = event.awayLineupSubstitutes

    }
}
