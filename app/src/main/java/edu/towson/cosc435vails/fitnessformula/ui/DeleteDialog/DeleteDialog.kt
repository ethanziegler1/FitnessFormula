package edu.towson.cosc435vails.fitnessformula.ui.DeleteDialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable


@Composable
fun DeleteDialog(
    title: String,
    text: String,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
    deleteViewModel: IDeleteDialog
) {
    if(deleteViewModel.showDeleteDialog.value) {
        AlertDialog(
            onDismissRequest = { deleteViewModel.hideDialog() },
            title = {
                Text(title)
            },
            text = {
                Text(text)
            },
            confirmButton = {
                // When user clicks ok, display load boar, submit exercises to new workout, pause and remove dialog if user navigates back.
                TextButton(onClick = {
                    onSubmit()
                    deleteViewModel.hideDialog()
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onCancel()
                    deleteViewModel.hideDialog()
                }) {
                    Text("Cancel")
                }
            },
        )
    }
}