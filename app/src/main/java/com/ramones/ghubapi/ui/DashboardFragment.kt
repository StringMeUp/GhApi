package com.ramones.ghubapi.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.jakewharton.rxbinding4.widget.afterTextChangeEvents
import com.ramones.ghubapi.R
import com.ramones.ghubapi.base.BaseFragment
import com.ramones.ghubapi.databinding.FragmentDashboardBinding
import com.ramones.ghubapi.networking.helper.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    override fun inflateBinding(): Class<FragmentDashboardBinding> =
        FragmentDashboardBinding::class.java

    override fun setContent(): Int = R.layout.fragment_dashboard

    var compositeDisposable: CompositeDisposable? = null
    private val viewModel: DashboardViewModel by viewModels()
    private val dashboardAdapter by lazy { DashboardAdapter() }

    override fun setUpView() {
        super.setUpView()
        viewModel.search()
        compositeDisposable = CompositeDisposable()
    }

    override fun setUpViewBinding() {
        super.setUpViewBinding()
        binding.apply {
            reposRecyclerView.apply {
                adapter = dashboardAdapter
            }

            compositeDisposable?.add(
                searchEditText.afterTextChangeEvents()
                    .skipInitialValue()
                    .debounce(100, TimeUnit.MILLISECONDS)
                    .subscribe {
                        viewModel.setQuery(it.toString())
                    })
        }
    }

    override fun setUpViewModelBinding() {
        super.setUpViewModelBinding()
        viewModel.repositories.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiResponse.Failure -> {}
                is ApiResponse.Loading -> {}
                is ApiResponse.Success -> {
                    dashboardAdapter.repos = it.value!!
                }
            }
        })
    }

    override fun onDestroyView() {
        compositeDisposable?.dispose()
        compositeDisposable = null
        super.onDestroyView()
    }
}