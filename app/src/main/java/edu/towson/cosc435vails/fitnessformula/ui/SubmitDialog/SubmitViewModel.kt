package edu.towson.cosc435vails.fitnessformula.ui.SubmitDialog

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class SubmitViewModel : ViewModel(), ISubmitViewModel {

    private val _showSubmitDialog: MutableState<Boolean> = mutableStateOf(false)
    override val showSubmitDialog: State<Boolean> = _showSubmitDialog

    private var _isLoading: MutableState<Boolean> = mutableStateOf(false)
    override val isLoading: MutableState<Boolean> = _isLoading

    override fun showDialog() {
        _showSubmitDialog.value = true
    }

    override fun hideDialog() {
        _showSubmitDialog.value = false
    }

    override fun switchLoading() {
        _isLoading.value = !_isLoading.value
    }

    override fun displayLoading() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d("SubmitViewModel", "isLoading: ${isLoading.value}")
                switchLoading()
                delay(2000L)
                switchLoading()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _showSubmitDialog.value = false
    }

}