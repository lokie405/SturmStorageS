package com.seryoga.sturmstorages.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seryoga.sturmstorages.util.Const


@Entity(tableName = Const.TABLE_PROVIDER_NAME)
data class Provider(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name") val name: String
)