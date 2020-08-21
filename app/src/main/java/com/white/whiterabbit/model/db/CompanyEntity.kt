package com.white.whiterabbit.model.db
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable
import com.white.whiterabbit.model.addressModel
import com.white.whiterabbit.model.companyModel

@Entity
data class CompanyEntity(@PrimaryKey(autoGenerate = false)
                          @NonNull val id : Int,
                         val name : String,
                         val username : String,
                         val email : String,
                         val profile_image : String,
                         val phone:String,
                         val website:String,
                         val street:String,
                         val suite:String,
                         val city:String,
                         val zipcode:String,
                         val lat:String,
                         val lng:String,
                         val companyName:String,
                         val catchPhrase:String,
                         val bs:String): Serializable