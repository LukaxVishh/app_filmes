package com.example.app_filmes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_filmes.ui.MovieViewModel
import com.example.app_filmes.ui.screens.AddEditMovieScreen
import com.example.app_filmes.ui.screens.MovieListScreen

object Routes {
    const val LIST = "list"
    const val ADD = "add"
    const val EDIT = "edit/{id}"
}

@Composable
fun AppNav(modifier: Modifier = Modifier, vm: MovieViewModel) {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Routes.LIST, modifier = modifier) {
        composable(Routes.LIST) {
            MovieListScreen(
                vm = vm,
                onAdd = { nav.navigate(Routes.ADD) },
                onEdit = { id -> nav.navigate("edit/$id") }
            )
        }
        composable(Routes.ADD) {
            AddEditMovieScreen(
                titulo = "Salvar Filme",
                initial = null,
                onSave = { t, a, g, n ->
                    vm.salvar(t, a, g, n)
                    nav.popBackStack()
                },
                onCancel = { nav.popBackStack() }
            )
        }
        composable(
            route = Routes.EDIT,
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: -1
            AddEditMovieScreen(
                titulo = "Atualizar Filme",
                initial = vm.buscarPorId(id),
                onSave = { t, a, g, n ->
                    vm.atualizar(id, t, a, g, n)
                    nav.popBackStack()
                },
                onCancel = { nav.popBackStack() }
            )
        }
    }
}
