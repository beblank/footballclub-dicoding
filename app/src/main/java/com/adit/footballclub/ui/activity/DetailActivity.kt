package com.adit.footballclub.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.adit.footballclub.R
import com.adit.footballclub.Utils.Const
import com.adit.footballclub.entity.Events

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val event = intent.getParcelableExtra<Events>(Const.event)
    }
}
