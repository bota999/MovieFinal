package kz.movieapp.moviedb.movie

import dagger.Module
import dagger.Provides
import kz.movieapp.moviedb.api.MovieDbApi
import kz.movieapp.moviedb.di.AppScope
import kz.movieapp.moviedb.movie.genre.GenrePartPresenter
import kz.movieapp.moviedb.movie.genre.GenrePartPresenterImpl
import kz.movieapp.moviedb.movie.genrefilter.GenreFilterPresenter
import kz.movieapp.moviedb.movie.genrefilter.GenreFilterPresenterImpl
import kz.movieapp.moviedb.movie.latestmovie.LatestMoviePresenter
import kz.movieapp.moviedb.movie.latestmovie.LatestMoviePresenterImpl
import kz.movieapp.moviedb.movie.moviefilter.MovieFilterPresenter
import kz.movieapp.moviedb.movie.moviefilter.MovieFilterPresenterImpl
import kz.movieapp.moviedb.movie.movies.MoviePartPresenter
import kz.movieapp.moviedb.movie.movies.MoviePartPresenterImpl
import kz.movieapp.moviedb.search_history.SearchHistoryPresenterImpl
import kz.movieapp.moviedb.movie.nowplaying.NowPlaying
import kz.movieapp.moviedb.movie.nowplaying.NowPlayingPresenter
import kz.movieapp.moviedb.movie.nowplaying.NowPlayingPresenterImpl
import kz.movieapp.moviedb.movie.popularmovies.PopularMoviesPresenter
import kz.movieapp.moviedb.movie.popularmovies.PopularMoviesPresenterImpl
import kz.movieapp.moviedb.movie.upcoming.UpcomingPresenter
import kz.movieapp.moviedb.movie.upcoming.UpcomingPresenterImpl
import kz.movieapp.moviedb.search_history.SearchHistoryPresenter

@Module
class MovieModule {
    @Provides
    @AppScope
    fun provideMainInteractor(api : MovieDbApi): MovieInteractor {
        return MovieInteractorImpl(api)
    }

    @Provides
    fun provideUpcomingPresenter(movieInteractor: MovieInteractor): UpcomingPresenter {
        return UpcomingPresenterImpl(movieInteractor, null)
    }

    @Provides
    fun provideNowPlayingPresenter(movieInteractor: MovieInteractor): NowPlayingPresenter {
        return NowPlayingPresenterImpl(movieInteractor, null)
    }
    @Provides
    fun providePopularMoviePresenter(movieInteractor: MovieInteractor): PopularMoviesPresenter {
        return PopularMoviesPresenterImpl(movieInteractor, null)
    }
    @Provides
    fun provideLatestMoviePresenter(movieInteractor: MovieInteractor): LatestMoviePresenter {
        return LatestMoviePresenterImpl(movieInteractor, null)
    }

    @Provides
    fun provideGenreFilterPresenter(movieInteractor: MovieInteractor): GenreFilterPresenter {
        return GenreFilterPresenterImpl(movieInteractor, null)
    }

    @Provides
    fun provideMoviePartFragmentPresenter(movieInteractor: MovieInteractor): MoviePartPresenter {
        return MoviePartPresenterImpl(movieInteractor, null)
    }

    @Provides
    fun provideGenrePartFragmentPresenter(movieInteractor: MovieInteractor): GenrePartPresenter {
        return GenrePartPresenterImpl(movieInteractor, null)
    }

    @Provides
    fun provideMovieFilterPresenter(movieInteractor: MovieInteractor): MovieFilterPresenter {
        return MovieFilterPresenterImpl(movieInteractor, null)
    }
    @Provides
    fun provideSearchHistoryPresenter(movieInteractor: MovieInteractor): SearchHistoryPresenter {
        return SearchHistoryPresenterImpl(movieInteractor, null)
    }

}