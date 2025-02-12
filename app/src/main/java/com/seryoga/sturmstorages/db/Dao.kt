package com.seryoga.sturmstorages.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seryoga.sturmstorages.util.Const
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM ${Const.TABLE_PRODUCT_NAME} WHERE :filter = '*' OR provider LIKE :filter")
    fun getNewProducts(filter: String): Flow<List<Product>>

    @Query("SELECT * FROM ${Const.TABLE_PRODUCT_NAME}")
    fun getProductsAll(): LiveData<List<Product>>

    @Query("SELECT * FROM ${Const.TABLE_PRODUCT_NAME} WHERE provider = :chosenProvider")
    fun getProducts(chosenProvider: String): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // NOTE: Experimental case with insert the whole list of Products at once
    suspend fun insertProducts(products: List<Product>)

    @Query("SELECT DISTINCT provider FROM ${Const.TABLE_PRODUCT_NAME}")
    fun getProvider() : LiveData<List<String>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertProvider(providers: List<Provider>)
//
//    @Query("SELECT * FROM ${Const.TABLE_PROVIDER_NAME}")
//    fun getAllProviders(): LiveData<List<Product>>
}
