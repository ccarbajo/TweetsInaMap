package com.example.tweetsinmap.domain

import com.example.tweetsinmap.data.repository.remote.OnResponseListener
import com.example.tweetsinmap.data.repository.remote.RetrofitCaller

class MapUseCaseImpl(val repository : RetrofitCaller) : MapUseCase {
    override fun getTweetsByLocation(tweet: String, coordenadas: String, callback: OnResponseListener) {
        repository.getTweetsByLocation(tweet, coordenadas, callback)
    }
}