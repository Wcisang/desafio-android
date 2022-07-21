package com.picpay.desafio.android.presentation.ui

import com.picpay.desafio.android.domain.model.User

sealed class MainActivityState {
    object LoadingState: MainActivityState()
    data class SuccessListState(val users: List<User>): MainActivityState()
    object ErrorGettingList : MainActivityState()
}