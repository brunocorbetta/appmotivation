package com.corbetta.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val security: SharedPreferences =
       context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    // esse medtodo salva os dados
    fun storeString(key: String, str: String) {
        security.edit().putString(key, str).apply()
    }

    //o ?: e que se o primeiro nao for ele retorno o outro
    // esse metodo pega as informações
    fun getString(key: String): String {
        return security.getString(key, "") ?: ""
    }
}