package com.ramones.ghubapi.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VDBinding : ViewDataBinding>(private val inflate: Inflate<VDBinding>) : Fragment() {

    open fun setUpView() {}
    open fun setUpViewBinding() {}
    open fun setUpViewModelBinding() {}

    private var _binding: VDBinding? = null
    protected val viewBinding
        get() = _binding!!

    @NonNull
    protected abstract fun inflateBinding(): Class<VDBinding>

    @NonNull
    protected abstract fun setContent(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initViewBinding(inflater, container)
        return viewBinding.root
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