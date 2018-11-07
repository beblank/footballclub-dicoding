package com.adit.footballclub.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
        @SerializedName("strTeamBadge") val teamLogo:String,
        @SerializedName("strTeam") val teamName:String,
        @SerializedName("idTeam") val teamId:String,
        @SerializedName("intFormedYear") val teamFormedYear:String,
        @SerializedName("strStadium") val teamStadium:String,
        @SerializedName("strDescriptionEN") val teamDesc:String
): Parcelable