package com.example.tweetsinmap.data.repository.remote

import com.example.tweetsinmap.data.entities.response.TweetsResponse

interface RetrofitCaller {
    fun getTweetsByLocation(tweet : String, coordenadas : String, callback : OnResponseListener)
}