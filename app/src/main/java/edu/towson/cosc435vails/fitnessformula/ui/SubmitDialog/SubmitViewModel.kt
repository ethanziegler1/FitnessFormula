package edu.towson.cosc435vails.fitnessformula.ui.SubmitDialog

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SubmitViewModel : ViewModel(), ISubmitViewModel {

    private val _showSubmitDialog: MutableState<Boolean> = mutableStateOf(false)
    override val showSubmitDialog: State<Boolean> = _showSubmitDialog

    override fun showDialog() {
        _showSubmitDialog.value = true
    }

    override fun hideDialog() {
        _showSubmitDialog.value = false
    }
}