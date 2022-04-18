package com.ramones.ghubapi.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.jakewharton.rxbinding4.widget.afterTextChangeEvents
import com.ramones.ghubapi.R
import com.ramones.ghubapi.base.BaseFragment
import com.ramones.ghubapi.databinding.FragmentSearchBinding
import com.ramones.ghubapi.networking.helper.ApiResponse
import com.ramones.ghubapi.util.hide
import com.ramones.ghubapi.util.show
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun inflateBinding(): Class<FragmentSearchBinding> =
        FragmentSearchBinding::class.java

    override fun setContent(): Int = R.layout.fragment_search

    var compositeDisposable: CompositeDisposable? = null
    private val viewModel: SearchViewModel by viewModels()
    private val dashboardAdapter by lazy { SearchAdapter() }

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
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .subscribe {
                        viewModel.setQuery(it.editable.toString())
                    })
        }
    }

    override fun setUpViewModelBinding() {
        super.setUpViewModelBinding()
        viewModel.repositories.observe(viewLifecycleOwner, Observer { repositories ->
            repositories?.let {
                when (it) {
                    is ApiResponse.Failure -> {
                        binding.progressCircular.hide()
                    }
                    is ApiResponse.Loading -> {
                        binding.progressCircular.show()
                    }
                    is ApiResponse.Success -> {
                        /** At times, even though it is very rare, the code is simply too good and you have to delay stuff. */
                        dashboardAdapter.repos = it.value!!
                        lifecycleScope.launch {
                            delay(500)
                            binding.progressCircular.hide()
                        }
                    }
                }
            }
        })

        viewModel.searchQuery.observe(viewLifecycleOwner, Observer { query ->
            query?.let {
                viewModel.search(it)
            }
        })
    }

    override fun onDestroyView() {
        compositeDisposable?.dispose()
        compositeDisposable = null
        super.onDestroyView()
    }
}