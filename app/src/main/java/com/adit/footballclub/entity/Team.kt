package com.adit.footballclub.entity

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("strTeamBadge") val logo:String
)