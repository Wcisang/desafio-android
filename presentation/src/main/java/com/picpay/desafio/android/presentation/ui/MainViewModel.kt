package com.picpay.desafio.android.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetListUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: GetListUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainActivityState>(MainActivityState.SuccessListState(emptyList()))
    val uiState: StateFlow<MainActivityState> = _uiState

    init {
        handleLoading()
        getUsersList()
    }

    private fun getUsersList() {
        viewModelScope.launch {
            useCase.execute().run {
                handleSuccess(this)
            }
        }
    }

    private fun handleSuccess(users: List<User>) {
        _uiState.value = MainActivityState.SuccessListState(users)
    }

    private fun handleLoading() {
        _uiState.value = MainActivityState.LoadingState
    }
}