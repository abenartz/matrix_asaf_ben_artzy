package com.example.matrix_asaf_ben_artzy.di.main

import android.content.Context
import com.example.matrix_asaf_ben_artzy.api.FakeMainServiceApi
import com.example.matrix_asaf_ben_artzy.reopsitory.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
object MainModule {

    @ActivityRetainedScoped
    @Provides
    fun provideMainServiceApi(
        @ApplicationContext context: Context
        ): FakeMainServiceApi {
        return FakeMainServiceApi(context)
    }


    @ActivityRetainedScoped
    @Provides
    fun provideMainRepository(
        @ApplicationContext context: Context,
        mainServiceApi: FakeMainServiceApi
    ): MainRepository {
        return MainRepository(
            mainServiceApi
        )
    }

}