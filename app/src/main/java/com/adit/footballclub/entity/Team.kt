package com.adit.footballclub.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(
        tableName = "team"
)
@Parcelize
data class Team(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name ="id")
        @SerializedName("idTeam") val teamId:String,
        @ColumnInfo(name ="team_logo")
        @SerializedName("strTeamBadge") val teamLogo:String,
        @ColumnInfo(name ="team_name")
        @SerializedName("strTeam") val teamName:String,
        @ColumnInfo(name ="team_formed")
        @SerializedName("intFormedYear") val teamFormedYear:String,
        @ColumnInfo(name ="team_stadium")
        @SerializedName("strStadium") val teamStadium:String,
        @ColumnInfo(name ="team_desc")
        @SerializedName("strDescriptionEN") val teamDesc:String
): Parcelable