package com.adit.footballclub.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.adit.footballclub.R
import com.adit.footballclub.entity.Player
import com.adit.footballclub.utils.Const
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.player_detail_view.*

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        AndroidInjection.inject(this)
        val player:Player = intent.getParcelableExtra(Const.player)
        setToolbar(player)
        setupView(player)
    }

    private fun setToolbar(player: Player) {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(player.playerName)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupView(player: Player) {
        Picasso.get().load(player.playerImage).into(player_image)
        player_desc.text = player.playerDesc
        player_height.text = player.playerHeight
        player_weight.text = player.playerWeight
        player_pos.text = player.playerPos
    }
}
