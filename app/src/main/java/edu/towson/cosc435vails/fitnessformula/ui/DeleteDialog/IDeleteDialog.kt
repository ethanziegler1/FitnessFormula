package edu.towson.cosc435vails.fitnessformula.ui.DeleteDialog

import androidx.compose.runtime.State

interface IDeleteDialog {

    val showDeleteDialog: State<Boolean>
    val isLoading: State<Boolean>

    fun showDialog()

    fun hideDialog()

    fun onCleared()

}