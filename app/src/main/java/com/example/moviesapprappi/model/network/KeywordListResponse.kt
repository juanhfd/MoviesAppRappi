package com.example.moviesapprappi.model.network

import com.example.moviesapprappi.model.Keyword
import com.example.moviesapprappi.model.NetworkResponseModel

data class KeywordListResponse(
    val id: Int,
    val keywords: List<Keyword>
) : NetworkResponseModel