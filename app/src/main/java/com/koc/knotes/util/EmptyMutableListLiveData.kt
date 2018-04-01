package com.koc.knotes.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class EmptyMutableListLiveData<T> private constructor():MutableLiveData<List<T>>() {
    init {
        this.value = emptyList()
    }

    companion object {
        fun <T> create():MutableLiveData<List<T>>{
            return EmptyMutableListLiveData()
        }
    }
}