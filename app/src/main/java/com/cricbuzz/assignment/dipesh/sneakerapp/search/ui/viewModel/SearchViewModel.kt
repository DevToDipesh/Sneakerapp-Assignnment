package com.cricbuzz.assignment.dipesh.sneakerapp.search.ui.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.cricbuzz.assignment.dipesh.sneakerapp.R
import com.cricbuzz.assignment.dipesh.sneakerapp.home.data.SneakerRepository
import com.cricbuzz.assignment.dipesh.sneakerapp.home.model.SneakerItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val sneakerRepository: SneakerRepository,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val item_ = MutableLiveData<List<SneakerItem>>()
    val sneakerItems: LiveData<List<SneakerItem>> = item_
    fun loadSneakers() {
        item_.postValue(sneakerRepository.getAllSneakers())
    }

    fun searchItem(query: String) {
        viewModelScope.launch {
            item_.postValue(
                sneakerRepository.getAllSneakers()
                    .filter { it.name.lowercase().contains(query.lowercase()) })
        }
    }

    fun addToCart(mContext: Context, item: SneakerItem) {
        viewModelScope.launch {
            val result = sneakerRepository.addItemToCart(mContext, item)
            if (result != -1L) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        mContext,
                        mContext.getString(R.string.item_added, item.title),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return SearchViewModel(
                    SneakerRepository(),
                    savedStateHandle
                ) as T
            }
        }
    }

}