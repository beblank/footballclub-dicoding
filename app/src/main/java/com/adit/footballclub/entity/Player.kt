package com.adit.footballclub.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
        @SerializedName("strPlayer") var playerName:String?,
        @SerializedName("strCutout") var playerPhoto:String?,
        @SerializedName("strFanart1") var playerImage:String?,
        @SerializedName("idPlayer") var playerId:String?,
        @SerializedName("strPosition") var playerPos:String?,
        @SerializedName("strHeight") var playerHeight:String?,
        @SerializedName("strWeight") var playerWeight:String?,
        @SerializedName("strDescriptionEN") var playerDesc:String?
):Parcelable