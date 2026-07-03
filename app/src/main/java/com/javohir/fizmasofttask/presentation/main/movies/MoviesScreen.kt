package com.javohir.fizmasofttask.presentation.main.movies

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.javohir.fizmasofttask.core.ui.Background
import com.javohir.fizmasofttask.core.ui.TextSecondary
import com.javohir.fizmasofttask.presentation.main.movies.component.MovieDetailDialog
import com.javohir.fizmasofttask.presentation.main.movies.component.MovieItem

/**
 * Created by: Javohir Oromov macos
 * Project: Fizmasoft task
 * Package: com.javohir.fizmasofttask.presentation.main.movies
 * Description: Kinolar tab (Screen + Content + Preview)
 */
@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is MoviesEvent.ShowError ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    MoviesContent(state = state, onAction = viewModel::onAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MoviesContent(
    state: MoviesState,
    onAction: (MoviesIntent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
    ) {
        when {
            state.isLoading && state.movies.isEmpty() ->
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            state.movies.isEmpty() ->
                Text(
                    text = "Kino topilmadi",
                    color = TextSecondary,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp),
                )

            else ->
                PullToRefreshBox(
                    isRefreshing = state.isLoading,
                    onRefresh = { onAction(MoviesIntent.Refresh) },
                    modifier = Modifier.fillMaxSize(),
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 20.dp),
                    ) {
                        items(state.movies, key = { it.id }) { movie ->
                            MovieItem(
                                movie = movie,
                                onClick = { onAction(MoviesIntent.MovieClicked(movie)) },
                            )
                            HorizontalDivider(color = Color(0xFFE1E7DF))
                        }
                    }
                }
        }
        state.selectedMovie?.let { movie ->
            MovieDetailDialog(
                movie = movie,
                onDismiss = { onAction(MoviesIntent.DismissDetail) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoviesPreview() {
    MoviesContent(state = MoviesState(isLoading = true), onAction = {})
}