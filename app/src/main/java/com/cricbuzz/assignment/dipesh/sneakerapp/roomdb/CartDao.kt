package com.cricbuzz.assignment.dipesh.sneakerapp.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    suspend fun getAll(): List<CartItem>

    @Query("SELECT * FROM cart_items WHERE itemId IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<CartItem>

    @Query("SELECT * FROM cart_items WHERE title LIKE :first ")
    suspend fun findByName(first: String): CartItem

    @Insert
    suspend fun insert(users: CartItem): Long

    @Delete
    suspend fun delete(user: CartItem)
}
