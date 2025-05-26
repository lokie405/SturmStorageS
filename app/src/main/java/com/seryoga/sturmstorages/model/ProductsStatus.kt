package com.seryoga.sturmstorages.model

//enum class ProductsStatus(val label: String) {
//    EMPTY_ALL("Products empty"),
//    NEW_FULL("NewProducts full"),
//    NEW_EMPTY("NewProducts empty"),
//    CURRENT_FULL("CurrentProducts full"),
//    CURRENT_EMPTY("CurrentProducts empty"),
//    OLD_FULL("OldProducts full"),
//    OLD_EMPTY("Old empty"),
//}

enum class ProductState {
    FULL, EMPTY
}

data class ProductsStatus(
    val newProduct: ProductState = ProductState.EMPTY,
    val currentProduct: ProductState = ProductState.EMPTY,
    val oldProduct: ProductState = ProductState.EMPTY
)