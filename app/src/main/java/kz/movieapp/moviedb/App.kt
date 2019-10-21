package kz.movieapp.moviedb

import android.app.Application
import kz.movieapp.moviedb.detail.DetailComponent
import kz.movieapp.moviedb.detail.DetailModule
import kz.movieapp.moviedb.di.AppComponent
import kz.movieapp.moviedb.di.AppModule
import kz.movieapp.moviedb.di.DaggerAppComponent
import kz.movieapp.moviedb.movie.MovieComponent
import kz.movieapp.moviedb.movie.MovieModule
import kz.movieapp.moviedb.network.NetworkModule

class App : Application(){
    lateinit var appComponent : AppComponent
    lateinit var movieComponent: MovieComponent
    lateinit var detailComponent : DetailComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .appModule(AppModule(this)).build()
    }

    fun createMainComponent(): MovieComponent {
        movieComponent = appComponent.plus(MovieModule())
        return movieComponent
    }

    fun createDetailComponent(): DetailComponent {
        detailComponent = appComponent.plus(DetailModule())
        return detailComponent
    }


}