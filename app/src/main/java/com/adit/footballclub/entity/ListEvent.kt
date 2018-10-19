package com.adit.footballclub.entity

import com.adit.footballclub.entity.Events
import com.google.gson.annotations.SerializedName

data class ListEvent (
        @SerializedName("events") val events: List<Events>
)