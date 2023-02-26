package com.bignerdranch.android.photogallery.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bignerdranch.android.photogallery.api.FlickrApi
import com.bignerdranch.android.photogallery.api.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import kotlin.math.max

private const val STARTING_PAGE = 1

class PhotoPagingSource : PagingSource<Int, GalleryItem>() {
    private val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        flickrApi = retrofit.create<FlickrApi>()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        val page = params.key ?: STARTING_PAGE

        return LoadResult.Page(
            data = flickrApi.fetchPhotos(page).photos.galleryItems,
            prevKey = when (page) {
                STARTING_PAGE -> null
                else -> max(page - 1, STARTING_PAGE)
            },
            nextKey = page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        return max(STARTING_PAGE, (anchorPosition / state.config.pageSize) + 1)
    }
}