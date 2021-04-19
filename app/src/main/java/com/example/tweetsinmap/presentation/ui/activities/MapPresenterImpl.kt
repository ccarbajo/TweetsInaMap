package com.example.tweetsinmap.presentation.ui.activities

import com.example.tweetsinmap.data.entities.response.TweetsResponse
import com.example.tweetsinmap.data.factory.RetrofitCallerFactory
import com.example.tweetsinmap.data.repository.remote.OnResponseListener

class MapPresenterImpl : MapPresenter, OnResponseListener{
    lateinit var mView : View

    override fun getTweetsByLocation(tweet: String, coordenadas: String) {
        //globalscope.launch
        val repository = RetrofitCallerFactory.getInstance
        repository.getTweetsByLocation(tweet, coordenadas, this)
    }

    override fun onSuccess(list: List<TweetsResponse>?) {
        mView.showTweetsByLocation(list)
    }

    override fun onError() {
        TODO("Not yet implemented")
    }

    override fun setView(mView: View) {
        this.mView = mView
    }
}