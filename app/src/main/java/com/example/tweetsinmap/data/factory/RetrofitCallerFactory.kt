package com.example.tweetsinmap.data.factory

import com.example.tweetsinmap.data.repository.remote.RetrofitCaller
import com.example.tweetsinmap.data.repository.remote.RetrofitCallerImpl

class RetrofitCallerFactory {
    companion object {
        val getInstance = RetrofitCallerImpl()
    }
}