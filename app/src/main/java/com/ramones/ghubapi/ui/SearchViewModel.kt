package com.ramones.ghubapi.ui

import androidx.lifecycle.*
import com.ramones.ghubapi.db.dbmodels.Repository
import com.ramones.ghubapi.networking.helper.ApiResponse
import com.ramones.ghubapi.networking.helper.SortType
import com.ramones.ghubapi.repository.SearchRepository
import com.ramones.ghubapi.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repository: SearchRepository
) : ViewModel() {

    private var _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    private var _repositories = MutableLiveData<ApiResponse<ArrayList<Repository>>>()
    val repositories: LiveData<ApiResponse<ArrayList<Repository>>>
        get() = _repositories

    private var _page = MutableLiveData<Int>()
    val page: LiveData<Int>
        get() = _page

    private var _type = MutableLiveData<SortType>()
    val type: LiveData<SortType>
        get() = _type

    private var defaultPage = 0

    fun search(query: String? = null) {
        viewModelScope.launch {
            repository.deleteRepositories()
            repository.searchRepositories(
                query = query ?: Constants.DEFAULT_QUERY,
                page = page.value ?: let { 0 },
                itemsPerPage = Constants.DEFAULT_PAYLOAD,
                sort = type.value?.name ?: let { SortType.STARS.name })
                .catch { /** todo*/ }
                .collect {
                    _repositories.postValue(it)
                }
        }
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
}