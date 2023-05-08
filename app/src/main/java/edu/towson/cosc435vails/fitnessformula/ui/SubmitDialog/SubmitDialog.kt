package edu.towson.cosc435vails.fitnessformula.ui.SubmitDialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ConfirmDialog(
    title: String,
    text: String,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
    submitViewModel: ISubmitViewModel
) {
    if(submitViewModel.showSubmitDialog.value) {
        AlertDialog(
            onDismissRequest = { submitViewModel.hideDialog() },
            title = {
                Text(title)
            },
            text = {
                Text(text)
            },
            confirmButton = {
                TextButton(onClick = {
                    onSubmit()
                    submitViewModel.hideDialog()
                }) {
                    Text("Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onCancel()
                    submitViewModel.hideDialog()
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}