package com.bignerdranch.android.photogallery.api

import retrofit2.http.GET

private const val API_KEY = "ba22281677c7bc4a0376685b74758ecb"

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}