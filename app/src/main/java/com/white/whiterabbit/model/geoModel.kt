package com.white.whiterabbit.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class geoModel (val lat:String?, val lng:String?): Serializable