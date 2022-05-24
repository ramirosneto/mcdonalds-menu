package br.com.mcdonalds.menu.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mcdonalds.menu.data.api.MenuRepository
import br.com.mcdonalds.menu.extensions.handleThrowable
import br.com.mcdonalds.utils.NetworkStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(private val repository: MenuRepository) : ViewModel() {

    val progressLiveStatus = MutableLiveData<NetworkStatus>()

    fun getMenu() {
        viewModelScope.launch {
            progressLiveStatus.postValue(NetworkStatus.Loading)
            withContext(Dispatchers.IO) {
                try {
                    val result = repository.getMenu()
                    progressLiveStatus.postValue(NetworkStatus.Success(result))
                } catch (throwable: Throwable) {
                    val handledError = throwable.handleThrowable()
                    progressLiveStatus.postValue(handledError)
                }
            }
        }
    }
}
