package br.com.mcdonalds.menu.extensions

import br.com.mcdonalds.utils.NetworkStatus
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.handleThrowable(): NetworkStatus.Error {
    return if (this is UnknownHostException) {
        NetworkStatus.Error(errorMessage = "Check your internet connection and try again.")
    } else if (this is HttpException && this.code() == 403) {
        NetworkStatus.Error(errorMessage = "Authentication error. Try again later.")
    } else if (this is SocketTimeoutException) {
        NetworkStatus.Error(errorMessage = "Check your internet connection and try again.")
    } else if (this.message.isNullOrEmpty().not()) {
        NetworkStatus.Error(errorMessage = this.message ?: "")
    } else {
        NetworkStatus.Error(errorMessage = "Unknown error. Try again later.")
    }
}
