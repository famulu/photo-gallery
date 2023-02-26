package com.bignerdranch.android.photogallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel() {
    private val photoRepository = PhotoRepository()

    val items = Pager(
        config = PagingConfig(pageSize = 100),
        pagingSourceFactory = { photoRepository.pagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)

}