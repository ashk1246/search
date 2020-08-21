package com.white.whiterabbit.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable
import com.white.whiterabbit.model.addressModel
import com.white.whiterabbit.model.companyModel

data class CompanyResponseModel( val id : Int?, val name : String?, val username : String?,  val email : String?, val profile_image : String?,  val phone:String?, val website:String?,val company : companyModel?,  val address:addressModel?): Serializable