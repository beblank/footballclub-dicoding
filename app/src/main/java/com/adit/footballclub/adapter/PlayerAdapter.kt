package com.adit.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adit.footballclub.R
import com.adit.footballclub.entity.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.player_item.view.*

class PlayerAdapter(val teamList:List<Player>):RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_item,parent, false))

    override fun getItemCount() = teamList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(teamList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(player: Player){
            Picasso.get().load(player.playerPhoto).resize(100,100).into(itemView.player_photo)
            itemView.player_name.text = player.playerName
            itemView.player_position.text = player.playerPos

//            itemView.setOnClickListener {
//                itemView.context.startActivity<TeamActivity>(Const.team to player)
//            }
        }
    }
}