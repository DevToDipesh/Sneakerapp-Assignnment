package com.cricbuzz.assignment.dipesh.sneakerapp.home.ui.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.cricbuzz.assignment.dipesh.sneakerapp.R
import com.cricbuzz.assignment.dipesh.sneakerapp.home.data.SneakerRepository
import com.cricbuzz.assignment.dipesh.sneakerapp.home.model.SneakerItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SneakerViewModel(
    private val sneakerRepository: SneakerRepository,
    private val stateHandle: SavedStateHandle
) : ViewModel() {
    private val items_ = MutableLiveData<ArrayList<SneakerItem>>()
    var sneakersItems: LiveData<ArrayList<SneakerItem>> = items_

    fun loadSneakers() {
        items_.postValue(sneakerRepository.getAllSneakers())
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
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return SneakerViewModel(
                    SneakerRepository(),
                    savedStateHandle
                ) as T
            }
        }
    }
}