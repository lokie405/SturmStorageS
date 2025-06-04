package com.seryoga.sturmstorages.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seryoga.sturmstorages.util.Const
import kotlinx.serialization.Serializable

//@Serializable
//data class ProductResponse(
//    val data: List<Product>
//)

@Entity(tableName = Const.TABLE_PRODUCTS_NAME)
//@Serializable
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "product")val name: String,
    val price: String,
    val quantity: String,
    @ColumnInfo(name = "provider")val provider: String,
    @ColumnInfo(name = "date")val date: String,
)