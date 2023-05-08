package edu.towson.cosc435vails.fitnessformula.ui.SubmitDialog

import androidx.compose.runtime.State

interface ISubmitViewModel {

    val showSubmitDialog: State<Boolean>
    val isLoading: State<Boolean>

    fun showDialog()

    fun hideDialog()

    fun switchLoading()

    fun displayLoading()

    fun onCleared()

}