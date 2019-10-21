package kz.movieapp.moviedb.di

import dagger.Component
import kz.movieapp.moviedb.api.ApiModule
import kz.movieapp.moviedb.detail.DetailComponent
import kz.movieapp.moviedb.detail.DetailModule
import kz.movieapp.moviedb.movie.MovieComponent
import kz.movieapp.moviedb.movie.MovieModule
import kz.movieapp.moviedb.network.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (ApiModule::class)])
interface AppComponent{
       fun plus(movieModule: MovieModule): MovieComponent
       fun plus(detailModule: DetailModule): DetailComponent
}