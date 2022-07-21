package com.picpay.desafio.android.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserListAdapter
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onResume() {
        super.onResume()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collectLatest {
                handleState(it)
            }
        }
    }

    private fun setupAdapter() {
        adapter = UserListAdapter()
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun handleState(state: MainActivityState) {
        when(state) {
            is MainActivityState.LoadingState -> handleLoadingState()
            is MainActivityState.SuccessListState -> handleSuccessState(state)
        }
    }

    private fun handleSuccessState(state: MainActivityState.SuccessListState) {
        hideLoading()
        adapter.users = state.users
    }

    private fun handleLoadingState() {
        showLoading()
    }

    private fun showLoading() {
        binding.pbList.isVisible = true
        binding.rvList.isVisible = false
    }

    private fun hideLoading() {
        binding.pbList.isVisible = false
        binding.rvList.isVisible = true
    }
}
