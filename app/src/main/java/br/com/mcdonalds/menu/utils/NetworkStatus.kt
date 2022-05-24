package br.com.mcdonalds.utils

sealed class NetworkStatus {

    object Loading : NetworkStatus()

    class Success<T>(val data: T) : NetworkStatus()

    class Error(val errorMessage: String) : NetworkStatus()
}
