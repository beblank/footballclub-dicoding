package com.adit.footballclub.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.adit.footballclub.R
import com.adit.footballclub.Utils.Const
import com.adit.footballclub.Utils.Utils
import com.adit.footballclub.entity.Events
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
    }
}
