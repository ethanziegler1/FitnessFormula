package edu.towson.cosc435vails.fitnessformula.ui.DeleteDialog

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


// View model for delete Dialog when deleting a workout
class DeleteViewModel : ViewModel(), IDeleteDialog {

    private val _showDeleteDialog: MutableState<Boolean> = mutableStateOf(false)
    override val showDeleteDialog: State<Boolean> = _showDeleteDialog

    private var _isLoading: MutableState<Boolean> = mutableStateOf(false)
    override val isLoading: MutableState<Boolean> = _isLoading

    override fun showDialog() {
        _showDeleteDialog.value = true
    }

    override fun hideDialog() {
        _showDeleteDialog.value = false
    }

    override fun onCleared() {
        super.onCleared()
        _showDeleteDialog.value = false
    }

}