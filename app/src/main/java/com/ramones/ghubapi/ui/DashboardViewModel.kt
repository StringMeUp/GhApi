package com.ramones.ghubapi.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramones.ghubapi.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    fun getTestData(){
        viewModelScope.launch {
            repository.searchRepositories()
                .collect { data ->
                    Log.d("TestData", ": $data")
                }
        }
    }
}