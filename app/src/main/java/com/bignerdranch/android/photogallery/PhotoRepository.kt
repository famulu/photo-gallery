package com.bignerdranch.android.photogallery

import com.bignerdranch.android.photogallery.paging.PhotoPagingSource

class PhotoRepository {
    fun pagingSource() = PhotoPagingSource()
}