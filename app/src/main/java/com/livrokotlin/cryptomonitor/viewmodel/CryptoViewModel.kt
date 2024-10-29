package com.livrokotlin.cryptomonitor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.livrokotlin.cryptomonitor.services.CryptoService
import com.livrokotlin.cryptomonitor.state.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoViewModel(
    private val service: CryptoService
): ViewModel() {

    private val _tickerLiveData = MutableLiveData<ScreenState>()
    val tickerLiveData: LiveData<ScreenState> = _tickerLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetch()
        }
    }

    private suspend fun fetch() {
        _tickerLiveData.postValue(ScreenState.Loading)
        try {
            val response = service.fetchCoinTicker()
            _tickerLiveData.postValue(ScreenState.Success(response.ticker))
        } catch (exception: Throwable) {
            _tickerLiveData.postValue(ScreenState.Error(exception))
        }
    }

}