package com.adit.footballclub.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(
        tableName = "event"
)
@Parcelize
data class Events(
    @SerializedName("idEvent")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="id")
    val idEvent:String,

    @SerializedName("strDate")
    @ColumnInfo(name ="date")
    var dateStr:String?,

    @SerializedName("idAwayTeam")
    @ColumnInfo(name ="id_away")
    var idAway: String,

    @SerializedName("idHomeTeam")
    @ColumnInfo(name ="id_home")
    var idHome: String,

    @SerializedName("strHomeTeam")
    @ColumnInfo(name ="name_home_team")
    var nameHomeTeam: String?,

    @SerializedName("intHomeScore")
    @ColumnInfo(name ="home_score")
    var homeScore: String?,

    @SerializedName("intHomeShots")
    @ColumnInfo(name ="home_shots")
    var homeShots: String?,

    @SerializedName("strAwayTeam")
    @ColumnInfo(name ="name_away_team")
    var nameAwayTeam: String?,

    @SerializedName("intAwayScore")
    @ColumnInfo(name ="away_score")
    var awayScore: String?,

    @SerializedName("intAwayShots")
    @ColumnInfo(name ="away_shots")
    var awayShots: String?,

    @SerializedName("strAwayGoalDetails")
    @ColumnInfo(name ="away_goal_details")
    var awayGoalDetails: String?,

    @SerializedName("strAwayLineupDefense")
    @ColumnInfo(name ="away_lineup_defense")
    var awayLineupDefense: String?,

    @SerializedName("strAwayLineupForward")
    @ColumnInfo(name ="away_lineup_forward")
    var awayLineupForward: String?,

    @SerializedName("strAwayLineupGoalkeeper")
    @ColumnInfo(name ="away_lineup_goalkeeper")
    var awayLineupGoalkeeper: String?,

    @SerializedName("strAwayLineupMidfield")
    @ColumnInfo(name ="away_lineup_midfield")
    var awayLineupMidfield: String?,

    @SerializedName("strAwayLineupSubstitutes")
    @ColumnInfo(name ="away_lineup_substitutes")
    var awayLineupSubstitutes: String?,

    @SerializedName("strHomeGoalDetails")
    @ColumnInfo(name ="home_goal_details")
    var homeGoalDetails: String?,

    @SerializedName("strHomeLineupDefense")
    @ColumnInfo(name ="home_lineup_defense")
    var homeLineupDefense: String?,

    @SerializedName("strHomeLineupForward")
    @ColumnInfo(name ="home_lineup_forward")
    var homeLineupForward: String?,

    @SerializedName("strHomeLineupGoalkeeper")
    @ColumnInfo(name ="home_lineup_goalkeeper")
    var homeLineupGoalkeeper: String?,

    @SerializedName("strHomeLineupMidfield")
    @ColumnInfo(name ="home_lineup_midfield")
    var homeLineupMidfield: String?,

    @SerializedName("strHomeLineupSubstitutes")
    @ColumnInfo(name ="home_lineup_substitutes")
    var homeLineupSubstitutes: String?

):Parcelable