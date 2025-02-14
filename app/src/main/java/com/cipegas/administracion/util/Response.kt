package com.cipegas.administracion.util

sealed class Response <out T>{

    object Loading : Response<Nothing>()

    data class Succes<out T>(
        val data: T
    ) : Response<T>()

    data class Error(
        val message : String
    ) : Response<Nothing>()


}