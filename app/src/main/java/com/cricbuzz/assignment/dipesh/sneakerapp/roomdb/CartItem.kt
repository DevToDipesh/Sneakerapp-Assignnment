package com.cricbuzz.assignment.dipesh.sneakerapp.roomdb

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "cart_items")
@Parcelize
data class CartItem(
    @PrimaryKey(autoGenerate = true) var itemId: Int = 0,
    @ColumnInfo val sneakerId: String,
    @ColumnInfo val size: String,
    @ColumnInfo val colorWay: String,
    @ColumnInfo val price: Int,
    @ColumnInfo val title: String
) : Parcelable