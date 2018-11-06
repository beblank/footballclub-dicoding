package com.adit.footballclub.entity

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("strTeamBadge") val teamLogo:String,
        @SerializedName("strTeamFanart1") val teamImage:String,
        @SerializedName("strTeam") val teamName:String,
        @SerializedName("idTeam") val teamId:String,
        @SerializedName("strManager") val teamManager:String,
        @SerializedName("strStadium") val teamStadium:String,
        @SerializedName("strDescriptionEN") val teamDesc:String
)