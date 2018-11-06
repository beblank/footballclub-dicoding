package com.adit.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.adit.footballclub.R
import com.adit.footballclub.entity.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_item.view.*

class TeamAdapter(val teamList:List<Team>):RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_item,parent, false))

    override fun getItemCount() = teamList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(teamList[position])
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(team: Team){
            Picasso.get().load(team.logo).into(itemView.team_logo)
            itemView.team_name.text = team.teamName
        }
    }
}