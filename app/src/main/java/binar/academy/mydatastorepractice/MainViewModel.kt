package binar.academy.mydatastorepractice

import androidx.lifecycle.*
import kotlinx.coroutines.launch

/**
 * @author Abika Chairul Yusri created on 3/10/2022 at 10:53 PM.
 */
class MainViewModel(private val pref: CounterDataStoreManager) : ViewModel() {

    val vCounter: MutableLiveData<Int> = MutableLiveData(0)

    fun incrementCount() {
        vCounter.postValue(vCounter.value?.plus(1))
    }

    fun decrementCount() {
        vCounter.value?.let {
            if (it > 0) {
                vCounter.postValue(vCounter.value?.minus(1))
            }
        }
    }

    fun saveDataStore(value: Int) {
        viewModelScope.launch {
            pref.setCounter(value)
        }
    }

    fun getDataStore(): LiveData<Int> {
        return pref.getCounter().asLiveData()
    }
}