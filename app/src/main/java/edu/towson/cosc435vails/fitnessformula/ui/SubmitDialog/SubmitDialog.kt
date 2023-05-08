package edu.towson.cosc435vails.fitnessformula.ui.SubmitDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SubmitDialog(
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
                if (submitViewModel.isLoading.value) {
                    Column() {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            strokeWidth = 5.dp
                        )
                        Text(text = "Loading...",
                        modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                } else {
                    Text(text)
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    submitViewModel.displayLoading()
                    onSubmit()
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1000L)
                        submitViewModel.hideDialog()
                    }
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