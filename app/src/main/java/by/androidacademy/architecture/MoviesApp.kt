package by.androidacademy.architecture

import android.app.Application

class MoviesApp : Application() {

    companion object {
        lateinit var instance: MoviesApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}