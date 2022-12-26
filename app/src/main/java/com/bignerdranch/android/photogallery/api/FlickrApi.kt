package com.bignerdranch.android.photogallery.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
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

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun providesFlickrApi(): FlickrApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create<FlickrApi>()
    }
}
