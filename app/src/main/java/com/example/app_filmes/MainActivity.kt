package com.example.app_filmes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_filmes.ui.MovieViewModel
import com.example.app_filmes.ui.navigation.AppNav
import com.example.app_filmes.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val vm: MovieViewModel = viewModel()
                AppNav(vm = vm)
            }
        }
    }
}
