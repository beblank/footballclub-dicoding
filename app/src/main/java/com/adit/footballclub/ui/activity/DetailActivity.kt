package com.adit.footballclub.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.adit.footballclub.R
import com.adit.footballclub.Utils.Const
import com.adit.footballclub.adapter.DetailAdapter
import com.adit.footballclub.entity.Events
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val event = intent.getParcelableExtra<Events>(Const.event)
        rvDetailMatch.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvDetailMatch.adapter = DetailAdapter(resources.getStringArray(R.array.attrib), event)
    }
}
