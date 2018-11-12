package com.adit.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.adit.footballclub.ui.activity.DetailActivity
import com.adit.footballclub.R
import com.adit.footballclub.utils.Const
import com.adit.footballclub.utils.Utils
import com.adit.footballclub.entity.Events
import kotlinx.android.synthetic.main.match_item.view.*
import org.jetbrains.anko.*
import android.support.v4.content.ContextCompat.startActivity
import android.provider.CalendarContract
import org.robolectric.shadows.ShadowBuild.setType
import android.content.Intent
import android.R



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
            itemView.btn_calendar.setOnClickListener {
                val calIntent = Intent(Intent.ACTION_INSERT)
                calIntent.type = "vnd.android.cursor.item/event"
                calIntent.putExtra(Events.TITLE, "My House Party")
                calIntent.putExtra(Events.EVENT_LOCATION, "My Beach House")
                calIntent.putExtra(Events.DESCRIPTION, "A Pig Roast on the Beach")

                val calDate = GregorianCalendar(2018,10,12)
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis())
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis())

                itemView.context.startActivity(calIntent)
            }
        }


    }

}