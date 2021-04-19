package com.example.tweetsinmap.data.repository.remote

import com.example.tweetsinmap.data.api.ApiService
import com.example.tweetsinmap.data.entities.response.TweetsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitCallerImpl : RetrofitCaller {
    override fun getTweetsByLocation(tweet: String, coordenadas: String, callback: OnResponseListener){
        var getTweetsCallback: Callback<List<TweetsResponse>> = object : Callback<List<TweetsResponse>> {
            override fun onResponse(call: Call<List<TweetsResponse>?>?, response: Response<List<TweetsResponse>?>) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body())
                }else{
                    callback.onError()
                }
            }

            override fun onFailure(call: Call<List<TweetsResponse>?>?, t: Throwable) {
                t.printStackTrace()
            }
        }
        var api = ApiService.getAPI()
        var call = api.getTweetsByLocation(tweet, coordenadas)
        call.enqueue(getTweetsCallback)
    }

}