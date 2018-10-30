package com.adit.footballclub.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.adit.footballclub.R
import com.adit.footballclub.utils.Const
import com.adit.footballclub.utils.Utils
import com.adit.footballclub.entity.Events
import com.adit.footballclub.viewmodel.DetailActivityViewModel
import com.adit.footballclub.viewmodel.ViewModelFactory
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
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var detailActivityViewModel: DetailActivityViewModel

    lateinit var event:Events

    lateinit var menu:Menu

    private var isFavorite = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        AndroidInjection.inject(this)
        event = intent.getParcelableExtra<Events>(Const.event)
        setViewModel(event)
        initView(event)
        setToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    private fun setFavorite() {
        if (!isFavorite) {
            menu.getItem(0).icon = getDrawable(R.drawable.ic_add_to_favorites)
        } else {
            menu.getItem(0).icon = getDrawable(R.drawable.ic_added_to_favorites)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_fav){
            if (isFavorite){
                detailActivityViewModel.deleteEvent(event)
                isFavorite = false
            } else {
                detailActivityViewModel.insertEvent(event)
                isFavorite = true
            }
            setFavorite()
            invalidateOptionsMenu()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setViewModel(event: Events) {
        detailActivityViewModel = ViewModelProviders.of(this, viewModelFactory)[DetailActivityViewModel::class.java]

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
        detailActivityViewModel.getEventById().observe(this, Observer{
            isFavorite = true
            invalidateOptionsMenu()
        })
        detailActivityViewModel.getInsertStatus().observe(this, Observer {
            if(it!!){
                Snackbar.make(content, "Event saved to Database", Snackbar.LENGTH_SHORT).show()
            }

        })
        detailActivityViewModel.getDeleteStatus().observe(this, Observer {
            if(it!!){
                Snackbar.make(content, "Event deleted from Database", Snackbar.LENGTH_SHORT).show()
            }

        })

        detailActivityViewModel.getAwayTeams(event.idAway)
        detailActivityViewModel.getHomeTeams(event.idHome)
        detailActivityViewModel.checkEvent(event.idEvent)
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.detail_title)
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

    override fun onDestroy() {
        detailActivityViewModel.disposeElements()
        super.onDestroy()
    }
}
