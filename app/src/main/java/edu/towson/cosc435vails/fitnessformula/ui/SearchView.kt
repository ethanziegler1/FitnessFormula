package edu.towson.cosc435vails.fitnessformula.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*

@Composable
fun SearchBar(onFilter: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var searchText: String by remember { mutableStateOf("") }
        OutlinedTextField(
            value = searchText,
            onValueChange = { v: String ->
                searchText = v
            },
            placeholder = {
                Text("Search")
            },
            singleLine = true
        )
        Button(onClick = {
            onFilter(searchText)
        }, modifier = Modifier.padding(start = 10.dp)) {
            Text(text = "Search")
        }
    }
}