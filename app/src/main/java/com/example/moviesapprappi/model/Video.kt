package com.example.moviesapprappi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val id: String,
    val name: String,
    val site: String,
    val key: String,
    val size: Int,
    val type: String
) : Parcelable
