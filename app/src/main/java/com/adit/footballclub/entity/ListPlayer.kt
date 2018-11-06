package com.adit.footballclub.entity

import com.google.gson.annotations.SerializedName

data class ListPlayer (
        @SerializedName("player") val player: List<Player>
)