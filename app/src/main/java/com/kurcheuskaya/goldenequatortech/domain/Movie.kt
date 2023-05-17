package com.kurcheuskaya.goldenequatortech.domain

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String?
) {
    // Add any additional properties or methods as needed
}