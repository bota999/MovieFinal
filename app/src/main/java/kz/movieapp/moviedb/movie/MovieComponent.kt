package kz.movieapp.moviedb.movie

import dagger.Subcomponent
import kz.movieapp.moviedb.di.AppScope
import kz.movieapp.moviedb.movie.genre.GenreFragment
import kz.movieapp.moviedb.movie.genrefilter.GenreFilter
import kz.movieapp.moviedb.movie.latestmovie.LatestMovie
import kz.movieapp.moviedb.movie.moviefilter.MovieFilterFragment
import kz.movieapp.moviedb.movie.movies.MovieFragment
import kz.movieapp.moviedb.movie.nowplaying.NowPlaying
import kz.movieapp.moviedb.movie.popularmovies.PopularMovies
import kz.movieapp.moviedb.movie.upcoming.UpcomingFragment
import kz.movieapp.moviedb.search_history.SearchHistoryFragment

@AppScope
@Subcomponent(modules = [(MovieModule::class)])
interface MovieComponent {
    fun inject(target: UpcomingFragment)
    fun inject(target: NowPlaying)
    fun inject(target: PopularMovies)
    fun inject(target: LatestMovie)
    fun inject(target: GenreFilter)
    fun inject(target: MovieFragment)
    fun inject(target: GenreFragment)
    fun inject(target: MovieFilterFragment)
    fun inject(target: SearchHistoryFragment)
}