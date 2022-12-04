package com.juacodev.fullmovies.ui.activities.fragments.listeners

import com.juacodev.fullmovies.domain.models.DMovie

interface MovieSelected {
    fun onMovieSelected(movie: DMovie)
}