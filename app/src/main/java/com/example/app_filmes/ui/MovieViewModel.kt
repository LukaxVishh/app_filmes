package com.example.app_filmes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_filmes.data.Movie
import com.example.app_filmes.data.MovieRepository
import kotlinx.coroutines.flow.*

class MovieViewModel : ViewModel() {
    private val repo = MovieRepository

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    val filmes: StateFlow<List<Movie>> =
        combine(repo.movies, _query) { lista, q ->
            val filtro = q.trim().lowercase()
            if (filtro.isBlank()) lista
            else lista.filter { it.titulo.lowercase().contains(filtro) || it.genero.lowercase().contains(filtro) }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun setQuery(q: String) { _query.value = q }

    fun salvar(titulo: String, ano: String, genero: String, nota: String) =
        repo.salvar(titulo, ano, genero, nota)

    fun atualizar(id: Long, titulo: String, ano: String, genero: String, nota: String) =
        repo.atualizar(id, titulo, ano, genero, nota)

    fun excluir(id: Long) = repo.excluir(id)

    fun buscarPorId(id: Long): Movie? = repo.buscarPorId(id)
}
