package com.cricbuzz.assignment.dipesh.sneakerapp.cart.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.cricbuzz.assignment.dipesh.sneakerapp.R
import com.cricbuzz.assignment.dipesh.sneakerapp.home.data.SneakerRepository
import com.cricbuzz.assignment.dipesh.sneakerapp.roomdb.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(
    private val sneakerRepository: SneakerRepository,
    private val stateHandle: SavedStateHandle
) : ViewModel() {

    private val items_ = MutableLiveData<List<CartItem>>()
    var sneakersItems: LiveData<List<CartItem>> = items_

    private val refresh = MutableLiveData<Boolean>()
    var onRemove: LiveData<Boolean> = refresh
    fun loadCartItems(context: Context) {
        viewModelScope.launch {
            items_.postValue(sneakerRepository.getAllCartItems(context = context))
        }
    }

    fun removeFromCart(mContext: Context, item: CartItem) {
        viewModelScope.launch {
            sneakerRepository.removeItemFromCart(mContext, item)

            withContext(Dispatchers.Main) {
                Toast.makeText(
                    mContext,
                    mContext.getString(R.string.item_removed, item.title),
                    Toast.LENGTH_SHORT
                ).show()
                loadCartItems(context = mContext)
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

                return CartViewModel(
                    SneakerRepository(),
                    savedStateHandle
                ) as T
            }
        }
    }
}