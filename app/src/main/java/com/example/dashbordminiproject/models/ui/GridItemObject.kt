package com.example.dashbordminiproject.models.ui

data class GridItemObject(
    val row: Int = 0,
    val column: Int = 0,
    val id: Int = row * column,
    val title : String = "No Title Provided",
    val imageURL: String = "https://1000logos.net/wp-content/uploads/2016/10/batman-logo-1966-1999.jpg"
)