package com.example.yonetimerchant.di

import com.example.yonetimerchant.BuildConfig
import com.example.yonetimerchant.utils.api.Endpoints
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun getRetrofit(gson: Gson, BASE_URL:String) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL
    @Singleton
    @Provides
    fun services(retrofit:Retrofit):Endpoints = retrofit.create(Endpoints::class.java)

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun getJSON():JSONObject= JSONObject()

 @Provides
    fun getJSONArray():JSONArray= JSONArray()

}