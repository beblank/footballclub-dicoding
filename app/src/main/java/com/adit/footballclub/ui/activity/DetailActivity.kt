package com.adit.footballclub.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.adit.footballclub.R
import com.adit.footballclub.Utils.Const
import com.adit.footballclub.Utils.Utils
import com.adit.footballclub.entity.Events
import kotlinx.android.synthetic.main.item_detail_defense.*
import kotlinx.android.synthetic.main.item_detail_forward.*
import kotlinx.android.synthetic.main.item_detail_goalkeeper.*
import kotlinx.android.synthetic.main.item_detail_goals.*
import kotlinx.android.synthetic.main.item_detail_midfield.*
import kotlinx.android.synthetic.main.item_detail_shots.*
import kotlinx.android.synthetic.main.item_detail_substitutes.*
import kotlinx.android.synthetic.main.match_detail_header_item.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val event = intent.getParcelableExtra<Events>(Const.event)

        initView(event)
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
