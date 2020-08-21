package com.white.whiterabbit.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable

data class addressModel(val street:String?,val suite:String?, val city:String?, val zipcode:String?,  val geo:geoModel?): Serializable