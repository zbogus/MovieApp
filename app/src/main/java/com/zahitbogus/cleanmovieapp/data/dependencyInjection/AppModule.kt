package com.zahitbogus.cleanmovieapp.data.dependencyInjection

import com.zahitbogus.cleanmovieapp.data.remote.MovieAPI
import com.zahitbogus.cleanmovieapp.data.repository.MovieRepositoryImpl
import com.zahitbogus.cleanmovieapp.domain.repository.MovieRepository
import com.zahitbogus.cleanmovieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 object AppModule {

     @Provides
     @Singleton
     fun provideMovieApi(): MovieAPI{
         return Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(MovieAPI::class.java)
     }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI): MovieRepository {
        return MovieRepositoryImpl(api = api)

    }
}