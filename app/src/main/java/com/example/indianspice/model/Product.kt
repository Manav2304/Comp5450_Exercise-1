package com.example.indianspice.model

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val description: String,
    val rating: Double,
    val reviewCount: Int,
    val colorHex: String,
    val origin: String = "India",
    val availableWeights: List<String> = listOf("50g", "100g", "250g", "500g"),
    val imageUrl: String = ""
)

data class CartItem(
    val product: Product,
    val quantity: Int = 1,
    val selectedWeight: String = "100g"
) {
    val subtotal: Double get() = product.price * quantity
}