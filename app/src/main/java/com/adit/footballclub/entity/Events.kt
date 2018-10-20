package com.adit.footballclub.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events(
    @SerializedName("strDate") var dateStr:String?,
    @SerializedName("strHomeTeam") var nameHomeTeam: String?,
    @SerializedName("intHomeScore") var homeScore: String?,
    @SerializedName("intHomeShots") var homeShots: String?,
    @SerializedName("strAwayTeam") var nameAwayTeam: String?,
    @SerializedName("intAwayScore") var awayScore: String?,
    @SerializedName("intAwayShots") var awayShots: String?,
    @SerializedName("strAwayGoalDetails") var awayGoalDetails: String?,
    @SerializedName("strAwayLineupDefense") var awayLineupDefense: String?,
    @SerializedName("strAwayLineupForward") var awayLineupForward: String?,
    @SerializedName("strAwayLineupGoalkeeper") var awayLineupGoalkeeper: String?,
    @SerializedName("strAwayLineupMidfield") var awayLineupMidfield: String?,
    @SerializedName("strAwayLineupSubstitutes") var awayLineupSubstitutes: String?,
    @SerializedName("strHomeGoalDetails") var homeGoalDetails: String?,
    @SerializedName("strHomeLineupDefense") var homeLineupDefense: String?,
    @SerializedName("strHomeLineupForward") var homeLineupForward: String?,
    @SerializedName("strHomeLineupGoalkeeper") var homeLineupGoalkeeper: String?,
    @SerializedName("strHomeLineupMidfield") var homeLineupMidfield: String?,
    @SerializedName("strHomeLineupSubstitutes") var homeLineupSubstitutes: String?
):Parcelable