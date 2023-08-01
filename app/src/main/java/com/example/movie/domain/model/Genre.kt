package com.example.movie.domain.model

data class Genre(
    val id:Int,
    val name:String
)

data class GenreResponse(
    val genres:List<Genre>
)



