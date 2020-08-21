package com.white.whiterabbit

import androidx.lifecycle.LiveData
import androidx.room.*
import com.white.whiterabbit.model.db.CompanyEntity


@Dao
interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCmpy(companyEntity: CompanyEntity)


    @Query("SELECT * FROM CompanyEntity  WHERE name LIKE '%' || :nameStr || '%' OR phone LIKE '%' || :phnoStr || '%'")
    fun getSearch(nameStr: String,phnoStr: String): LiveData<List<CompanyEntity>>

}