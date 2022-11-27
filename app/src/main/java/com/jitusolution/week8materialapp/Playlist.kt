package com.jitusolution.week8materialapp

data class Playlist(
    val id: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val image_url: String,
    var num_likes: Int
)
