package com.adit.footballclub.entity

import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("strDate") val dateStr:String,
    @SerializedName("strHomeTeam") val nameHomeTeam: String,
    @SerializedName("intHomeScore") val homeScore: String,
    @SerializedName("intHomeShots") val homeShots: String,
    @SerializedName("strAwayTeam") val nameAwayTeam: String,
    @SerializedName("intAwayScore") val awayScore: String,
    @SerializedName("intAwayShots") val awayShots: String,
    @SerializedName("strAwayGoalDetails") val awayGoalDetails: String,
    @SerializedName("strAwayLineupDefense") val awayLineupDefense: String,
    @SerializedName("strAwayLineupForward") val awayLineupForward: String,
    @SerializedName("strAwayLineupGoalkeeper") val awayLineupGoalkeeper: String,
    @SerializedName("strAwayLineupMidfield") val awayLineupMidfield: String,
    @SerializedName("strAwayLineupSubstitutes") val awayLineupSubstitutes: String,
    @SerializedName("strHomeGoalDetails") val homeGoalDetails: String,
    @SerializedName("strHomeLineupDefense") val homeLineupDefense: String,
    @SerializedName("strHomeLineupForward") val homeLineupForward: String,
    @SerializedName("strHomeLineupGoalkeeper") val homeLineupGoalkeeper: String,
    @SerializedName("strHomeLineupMidfield") val homeLineupMidfield: String,
    @SerializedName("strHomeLineupSubstitutes") val homeLineupSubstitutes: String

)