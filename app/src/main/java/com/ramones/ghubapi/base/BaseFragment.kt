package com.ramones.ghubapi.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    open fun setUpView() {}
    open fun setUpViewBinding() {}
    open fun setUpViewModelBinding() {}

    private var _binding: VB? = null
    protected val binding
        get() = _binding!!

    @NonNull
    protected abstract fun inflateBinding(): Class<VB>

    @NonNull
    protected abstract fun setContent(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewBinding()
        setUpViewModelBinding()
    }

    private fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        _binding = inflate.invoke(inflater, container, false)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}