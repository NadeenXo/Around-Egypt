package com.example.dto

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "FavouriteTable")
data class FavouriteTable(
@PrimaryKey val id: String,
val title: String,
val address: String,
val description: String,
val coverPhoto: String
)
