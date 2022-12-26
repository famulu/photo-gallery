package com.bignerdranch.android.photogallery

import com.bignerdranch.android.photogallery.api.FlickrApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject

class PhotoRepository @Inject constructor(private val flickrApi: FlickrApi) {
    suspend fun fetchPhotos() = flickrApi.fetchPhotos().photos.galleryItems
}