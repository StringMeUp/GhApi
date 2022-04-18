package com.ramones.ghubapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ramones.ghubapi.repository.SearchRepository
import com.ramones.ghubapi.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    repository: SearchRepository
) : ViewModel() {

    var _searchQuery = MutableLiveData<String>()
    private val searchQuery: LiveData<String>
    get() = _searchQuery

    fun setQuery(query: String){
        _searchQuery.postValue(query)
    }

    val repositories = repository.searchRepositories(searchQuery.value ?: Constants.DEFAULT_QUERY).asLiveData()
}