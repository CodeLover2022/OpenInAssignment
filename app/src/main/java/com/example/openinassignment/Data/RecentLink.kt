package com.example.openinassignment.Data

data class RecentLink(
    val original_image: String,
    val times_ago: String,
    val title: String,
    val total_clicks: Int,
   // val url_id: Int,
    val web_link: String
)