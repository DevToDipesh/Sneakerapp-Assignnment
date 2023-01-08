package com.cricbuzz.assignment.dipesh.sneakerapp.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SneakerItem(
    val brand: String,
    val colorway: String,
    val gender: String,
    val id: String,
    val media: Media,
    val name: String,
    val releaseDate: String,
    val retailPrice: Int,
    val shoe: String,
    val styleId: String,
    val title: String,
    val year: Int
) : Parcelable

@Parcelize
data class Media(
    val imageUrl: String,
    val smallImageUrl: String,
    val thumbUrl: String
) : Parcelable