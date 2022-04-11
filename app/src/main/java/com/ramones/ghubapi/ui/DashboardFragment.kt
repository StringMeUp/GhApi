package com.ramones.ghubapi.ui

import androidx.fragment.app.viewModels
import com.ramones.ghubapi.R
import com.ramones.ghubapi.base.BaseFragment
import com.ramones.ghubapi.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    override fun inflateBinding(): Class<FragmentDashboardBinding> = FragmentDashboardBinding::class.java

    override fun setContent(): Int = R.layout.fragment_dashboard

    private val model: DashboardViewModel by viewModels()

    override fun setUpView() {
        super.setUpView()
    }

    override fun setUpViewBinding() {
        super.setUpViewBinding()
    }

    override fun setUpViewModelBinding() {
        super.setUpViewModelBinding()
    }
}