package com.example.covidappcompose

import android.app.Application
import com.example.covidappcompose.net.Api
import com.example.covidappcompose.net.DataRepository
import com.example.covidappcompose.net.DataRepositoryService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

class MyApp : Application() {

    companion object {
        lateinit var kodein: Kodein
    }

    private val netModule = Kodein.Module {
        bind<Retrofit>() with singleton {
            Api.getApi()
        }

        bind<DataRepository>() with singleton {
            DataRepository(instance<Retrofit>().create(DataRepositoryService::class.java))
        }
    }

    override fun onCreate() {
        super.onCreate()

        kodein = Kodein {
            import(netModule)
        }
    }
}