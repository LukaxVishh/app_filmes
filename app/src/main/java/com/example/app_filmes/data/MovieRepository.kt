package com.example.app_filmes.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repositório em MEMÓRIA (singleton).
 * Ao fechar o app, os dados somem — como pedido.
 */
object MovieRepository {
    private var nextId = 1L
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()

    fun listar(): List<Movie> = _movies.value

    fun buscarPorId(id: Long): Movie? = _movies.value.find { it.id == id }

    fun salvar(titulo: String, ano: String, genero: String, nota: String) {
        val novo = Movie(nextId++, titulo.trim(), ano.trim(), genero.trim(), nota.trim())
        _movies.value = _movies.value + novo
    }

    fun atualizar(id: Long, titulo: String, ano: String, genero: String, nota: String) {
        _movies.value = _movies.value.map {
            if (it.id == id) it.copy(titulo = titulo.trim(), ano = ano.trim(), genero = genero.trim(), nota = nota.trim())
            else it
        }
    }

    fun excluir(id: Long) {
        _movies.value = _movies.value.filterNot { it.id == id }
    }
}
