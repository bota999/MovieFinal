package kz.movieapp.moviedb.detail

import dagger.Subcomponent
import kz.movieapp.moviedb.di.AppScope

@AppScope
@Subcomponent(modules = [(DetailModule::class)])
interface DetailComponent {
    fun inject(target: DetailActivity)
}