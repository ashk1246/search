package com.white.whiterabbit.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class companyModel(val name:String?, val catchPhrase:String?,val bs:String?): Serializable