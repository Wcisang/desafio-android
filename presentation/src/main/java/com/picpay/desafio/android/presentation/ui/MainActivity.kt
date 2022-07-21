package com.picpay.desafio.android.presentation.ui

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.presentation.R
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val viewModel: MainViewModel by viewModel()

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collectLatest {
                handleState(it)
            }
        }
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
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}
