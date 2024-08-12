package com.firman.brightontest.data

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.firman.brightontest.data.model.GetMovieResponse
import com.firman.brightontest.domain.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.internal.toImmutableList

class LocalDataSource(context: Context) {
    companion object {
        const val key_favorite = "key_favorite"
    }

    private val spec = KeyGenParameterSpec.Builder(
        MasterKey.DEFAULT_MASTER_KEY_ALIAS,
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    )
        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
        .build()

    private val masterKey = MasterKey.Builder(context)
        .setKeyGenParameterSpec(spec)
        .build()

    private val pref = EncryptedSharedPreferences.create(
        context,
        "Encrypted_Shared_Preferences",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    internal fun getFavoriteMovie(): Resource<MutableList<GetMovieResponse.Search>> {

        val json = pref.getString(key_favorite, null)
        val type = object : TypeToken<ArrayList<GetMovieResponse.Search>>() {}.type

        return Resource.Success(Gson().fromJson(json, type))
    }

    internal fun setFavorite(data: GetMovieResponse.Search): MutableList<GetMovieResponse.Search>? {
        val editor = pref.edit()
        var movieData =
            ((getFavoriteMovie() as Resource.Success).data ?: emptyList()).toImmutableList()
                .toMutableList()
        val json: String
        val type = object : TypeToken<ArrayList<GetMovieResponse.Search>>() {}.type

        if (movieData.isEmpty()) {
            movieData = mutableListOf()
            movieData.add(data)
            json = Gson().toJson(movieData)
            editor.apply()
            editor.putString(key_favorite, json)
            editor.apply()
        } else {
            val movie = movieData.find { it.imdbID == data.imdbID }

            if (movie == null)
                movieData.add(data)
            else
                movieData.remove(movie)

            json = Gson().toJson(movieData)
            editor.putString(key_favorite, json)
            editor.apply()
        }

        return Gson().fromJson(json, type)
    }

}