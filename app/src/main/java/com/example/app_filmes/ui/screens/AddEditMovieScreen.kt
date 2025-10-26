package com.example.app_filmes.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import com.example.app_filmes.data.Movie



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditMovieScreen(
    titulo: String,
    initial: Movie?,
    onSave: (titulo: String, ano: String, genero: String, nota: String) -> Unit,
    onCancel: () -> Unit
) {
    var t by remember(initial) { mutableStateOf(initial?.titulo ?: "") }
    var a by remember(initial) { mutableStateOf(initial?.ano ?: "") }
    var g by remember(initial) { mutableStateOf(initial?.genero ?: "") }
    var n by remember(initial) { mutableStateOf(initial?.nota ?: "") }

    val podeSalvar = t.isNotBlank() && a.isNotBlank() && g.isNotBlank()

    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text(titulo) }) }) { inner ->
        Column(
            Modifier.padding(inner).padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = t, onValueChange = { t = it }, label = { Text("Título*") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = a,
                onValueChange = { a = it },
                label = { Text("Ano*") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(value = g, onValueChange = { g = it }, label = { Text("Gênero*") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = n, onValueChange = { n = it }, label = { Text("Nota (opcional)") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(enabled = podeSalvar, onClick = { onSave(t, a, g, n) }) { Text("Salvar") }
                OutlinedButton(onClick = onCancel) { Text("Cancelar") }
            }
            if (!podeSalvar) {
                AssistChip(onClick = {}, label = { Text("Preencha Título, Ano e Gênero") })
            }
        }
    }
}

