package com.example.tweetsinmap.data.entities.response

import com.google.gson.annotations.SerializedName

class TweetsResponse {
    @SerializedName("user")
    lateinit var user: User
    lateinit var geo_enabled : String
    lateinit var geo : String
    lateinit var coordinates : String
    lateinit var created_at : String
    lateinit var text : String
}