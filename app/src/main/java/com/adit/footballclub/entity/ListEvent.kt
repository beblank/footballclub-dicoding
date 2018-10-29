package com.adit.footballclub.entity

import com.google.gson.annotations.SerializedName

data class ListEvent (
        @SerializedName("events") val events: List<Events>
)