package com.example.tweetsinmap.data.repository.remote

import com.example.tweetsinmap.data.entities.response.TweetsResponse

interface OnResponseListener {
    fun onSuccess(list : List<TweetsResponse>?)
    fun onError()
}