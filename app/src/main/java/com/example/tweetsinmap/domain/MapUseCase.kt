package com.example.tweetsinmap.domain

import com.example.tweetsinmap.data.repository.remote.OnResponseListener

interface MapUseCase {
    fun getTweetsByLocation(tweet : String, coordenadas : String, callback : OnResponseListener)
}