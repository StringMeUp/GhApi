package com.ramones.ghubapi.ui

import androidx.lifecycle.*
import com.ramones.ghubapi.db.dbmodels.Repository
import com.ramones.ghubapi.networking.helper.ApiResponse
import com.ramones.ghubapi.networking.helper.SortType
import com.ramones.ghubapi.repository.SearchRepository
import com.ramones.ghubapi.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val repository: SearchRepository
) : ViewModel() {

    private var _searchQuery = MutableLiveData<String>()
    private val searchQuery: LiveData<String>
        get() = _searchQuery

    private var _repositories = MutableLiveData<ApiResponse<List<Repository>>>()
    val repositories: LiveData<ApiResponse<List<Repository>>>
        get() = _repositories

    private var _page = MutableLiveData<Int>()
    val page: LiveData<Int>
        get() = _page

    private var _type = MutableLiveData<SortType>()
    val type: LiveData<SortType>
        get() = _type

    private var defaultPage = 0

    fun initializeDefaultSearch() {
        _repositories.postValue(
            repository.searchRepositories(searchQuery.value ?: Constants.DEFAULT_QUERY).asLiveData().value
        )
    }

    fun setQuery(query: String) {
        _searchQuery.postValue(query)
    }

    fun setPage() {
        defaultPage.inc()
        _page.postValue(defaultPage)
    }

    fun setFilter(type: SortType) {
        _type.postValue(type)
    }

    fun search() {
        viewModelScope.launch {
            repository.deleteRepositories()
            defaultPage = 0
            repository.searchRepositories(
                query = searchQuery.value!!,
                page = page.value!!,
                itemsPerPage = Constants.DEFAULT_PAYLOAD,
                sort = type.value!!.name
            ).asLiveData().value
        }
    }
}