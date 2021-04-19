package com.example.tweetsinmap.presentation.ui.activities

import com.example.tweetsinmap.data.entities.response.TweetsResponse

interface View {
    fun showTweetsByLocation(list : List<TweetsResponse>?)
}