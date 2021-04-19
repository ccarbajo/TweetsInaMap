package com.example.tweetsinmap.presentation.ui.activities

import com.example.tweetsinmap.data.repository.remote.OnResponseListener

interface MapPresenter {
    fun getTweetsByLocation(tweet : String, coordenadas : String)
    fun setView(mView : View)
}