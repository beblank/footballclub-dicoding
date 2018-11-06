package com.adit.footballclub.entity

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("strPlayer") val playerName:String,
        @SerializedName("strCutout") val playerPhoto:String,
        @SerializedName("strFanart1") val playerImage:String,
        @SerializedName("idPlayer") val playerId:String,
        @SerializedName("strPosition") val playerPos:String,
        @SerializedName("dateBorn") val playerBorn:String,
        @SerializedName("strDescriptionEN") val playerDesc:String
)