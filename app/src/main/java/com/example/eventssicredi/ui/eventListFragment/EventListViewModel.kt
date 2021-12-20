package com.example.eventssicredi.ui.eventListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eventssicredi.model.EventEntity
import com.example.eventssicredi.service.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventListViewModel(private val repository: EventRepository) : ViewModel() {

    private val _events = MutableLiveData<List<EventEntity>>()
    val events : LiveData<List<EventEntity>>
    get() = _events

    val loading = MutableLiveData<Boolean>()

    fun loadEvents() {
        var eventList: List<EventEntity>? = null
        loading.value = true
        CoroutineScope(Dispatchers.Main).launch {
            val response = repository.getApiData()
            withContext(Dispatchers.Default) {
                if (response.isSuccessful){
                    eventList = response.body() as List<EventEntity>?
                }
            }
            loading.value = false
            _events.value = eventList?: listOf()
        }
    }

    class EventListViewModelFactory(
        private val repository: EventRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EventListViewModel(repository) as T
        }
    }

}
