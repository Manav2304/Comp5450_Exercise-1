package com.example.indianspice.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.indianspice.model.CartItem
import com.example.indianspice.model.Product

class CartViewModel : ViewModel() {

    private val _items = mutableStateListOf<CartItem>()
    val items: List<CartItem> get() = _items

    var total by mutableStateOf(0.0)
        private set

    val itemCount: Int get() = _items.sumOf { it.quantity }

    fun addToCart(product: Product, weight: String = "100g") {
        val existingIndex = _items.indexOfFirst {
            it.product.id == product.id && it.selectedWeight == weight
        }
        if (existingIndex >= 0) {
            val current = _items[existingIndex]
            _items[existingIndex] = current.copy(quantity = current.quantity + 1)
        } else {
            _items.add(CartItem(product = product, quantity = 1, selectedWeight = weight))
        }
        recalculateTotal()
    }

    fun removeItem(index: Int) {
        if (index in _items.indices) {
            _items.removeAt(index)
            recalculateTotal()
        }
    }

    fun increaseQuantity(index: Int) {
        if (index in _items.indices) {
            _items[index] = _items[index].copy(quantity = _items[index].quantity + 1)
            recalculateTotal()
        }
    }

    fun decreaseQuantity(index: Int) {
        if (index in _items.indices) {
            val current = _items[index]
            if (current.quantity <= 1) _items.removeAt(index)
            else _items[index] = current.copy(quantity = current.quantity - 1)
            recalculateTotal()
        }
    }

    fun clear() {
        _items.clear()
        recalculateTotal()
    }

    private fun recalculateTotal() {
        total = _items.sumOf { it.subtotal }
    }
}