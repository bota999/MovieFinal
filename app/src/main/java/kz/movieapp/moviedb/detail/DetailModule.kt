package kz.movieapp.moviedb.detail

import dagger.Module
import dagger.Provides
import kz.movieapp.moviedb.api.MovieDbApi
import kz.movieapp.moviedb.di.AppScope

@Module
class DetailModule {
    @Provides
    fun provideDetailPresenter(detailInteractor: DetailInteractor): DetailPresenter{
        return DetailPresenterImpl(detailInteractor, null)
    }

    @Provides
    @AppScope
    fun provideDetailInteractor(api : MovieDbApi): DetailInteractor{
        return DetailInteractorImpl(api)
    }
}