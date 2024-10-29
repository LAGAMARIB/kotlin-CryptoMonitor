package com.livrokotlin.cryptomonitor.state

import com.livrokotlin.cryptomonitor.services.Ticker

sealed class ScreenState {
    object Loading: ScreenState()
    data class Success(val data: Ticker): ScreenState()
    data class Error(val exception: Throwable): ScreenState()
}