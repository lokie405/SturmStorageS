package com.seryoga.sturmstorages.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.seryoga.sturmstorages.util.Const
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM ${Const.TABLE_PRODUCTS_NAME} WHERE product LIKE :product AND provider LIKE :provider")
    fun getSomeProducts(product: String, provider: String): Flow<List<Product>>

    @Query("SELECT * FROM ${Const.TABLE_PRODUCTS_NAME}")
    suspend fun getProductsAll(): List<Product>

    @Query("SELECT * FROM ${Const.TABLE_PRODUCTS_NAME} WHERE provider = :chosenProvider")
    fun getProducts(chosenProvider: String): LiveData<List<Product>>

    /*- Raw -*/
    @RawQuery(observedEntities = [Product::class])
    fun getSomeProductRaw(query: SupportSQLiteQuery): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
//     NOTE: Experimental case with insert the whole list of Products at once
    suspend fun insertProducts(products: List<Product>)

    @Query("SELECT DISTINCT provider FROM ${Const.TABLE_PRODUCTS_NAME}")
    suspend fun getProvider() : List<String>

    //  --- Product New ---
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
////     NOTE: Experimental case with insert the whole list of Products at once
//    suspend fun insertProductsNew(products: List<ProductNew>)

    @Query("SELECT * FROM ${Const.TABLE_PRODUCTS_NEW_NAME}")
    suspend fun getProductsNew(): List<ProductNew>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductsNew(products: List<ProductNew>)

    // --- Backup Table ---
    @Query("SELECT * FROM ${Const.TABLE_PRODUCTS_OLD_NAME}")
    suspend fun getProductsOld(): List<ProductOld>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductsOld(data: List<ProductOld>)

    @Query("DELETE FROM ${Const.TABLE_PRODUCTS_OLD_NAME}")
    suspend fun clearBackup()


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertProvider(providers: List<Provider>)
//
//    @Query("SELECT * FROM ${Const.TABLE_PROVIDER_NAME}")
//    fun getAllProviders(): LiveData<List<Product>>
}
