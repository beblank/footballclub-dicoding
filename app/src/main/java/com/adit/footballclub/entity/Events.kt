package com.adit.footballclub.entity

import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("strDate") val dateStr:String,
    @SerializedName("strHomeTeam") val nameHomeTeam: String,
    @SerializedName("intHomeScore") val homeScore: String,
    @SerializedName("strAwayTeam") val nameAwayTeam: String,
    @SerializedName("intAwayScore") val awayScore: String

)