package com.ramones.ghubapi.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramones.ghubapi.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getData()
                .collect { data ->
                    Log.d("TESTDATA", ": $data")
                }
        }
    }
}