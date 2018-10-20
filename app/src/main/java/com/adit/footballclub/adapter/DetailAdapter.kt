package com.adit.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adit.footballclub.entity.Events
import com.adit.footballclub.R
import com.adit.footballclub.Utils.Const
import com.adit.footballclub.Utils.Utils
import kotlinx.android.synthetic.main.match_item.view.*

class DetailAdapter(val listAttrib:Array<String>, val event:HashMap<String, String>): RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:ViewHolder= HeaderViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.team_detail_item, parent, false))
        when(viewType){
            0 -> view = HeaderViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.match_detail_header_item, parent, false))
        }
        return view
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("dodol", "${holder.itemViewType}")
        when(holder.itemViewType){
            0 -> {
                holder as HeaderViewHolder
                holder.bind(event)
            }
        }
    }


    class HeaderViewHolder(itemView:View):ViewHolder(itemView) {
        fun bind(event: HashMap<String, String>){
            itemView.txt_date.text  = Utils.dateStringConverter(event.get(Const.date))
            itemView.txt_home_name.text = event.get(Const.home)
            itemView.txt_home_score.text = event.get(Const.homeScore)
            itemView.txt_away_name.text = event.get(Const.away)
            itemView.txt_away_score.text = event.get(Const.awayScore)
        }
    }

}