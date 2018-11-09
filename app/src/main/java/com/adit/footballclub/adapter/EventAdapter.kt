package com.adit.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adit.footballclub.ui.activity.DetailActivity
import com.adit.footballclub.R
import com.adit.footballclub.utils.Const
import com.adit.footballclub.utils.Utils
import com.adit.footballclub.entity.Events
import kotlinx.android.synthetic.main.match_item.view.*
import org.jetbrains.anko.*

class EventAdapter(val eventList:List<Events>):RecyclerView.Adapter<EventAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item,parent, false))

    override fun getItemCount() = eventList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        fun bind(event:Events){
            itemView.txt_date.text = Utils.dateStringConverter(event.dateStr, event.eventTime)
            itemView.txt_time.text = Utils.timeStringConverter(event.dateStr, event.eventTime)
            itemView.txt_home_name.text = event.nameHomeTeam
            itemView.txt_home_score.text = event.homeScore
            itemView.txt_away_name.text = event.nameAwayTeam
            itemView.txt_away_score.text = event.awayScore

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailActivity>(Const.event to event)
            }
        }


    }

}