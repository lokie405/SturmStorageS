package com.seryoga.sturmstorages.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seryoga.sturmstorages.util.Const

@Entity(tableName = Const.TABLE_PRODUCTS_OLD_NAME)
data class ProductOld(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "product")val name: String,
    val price: String,
    val quantity: String,
    @ColumnInfo(name = "provider")val provider: String,
)