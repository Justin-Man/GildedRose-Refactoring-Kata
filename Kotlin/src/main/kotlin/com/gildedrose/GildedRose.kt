package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun createProduct(item: Item) : Product? {
        return when (item) {
            is RegularItem -> RegularProduct(item)
            is ConjuredItem -> ConjuredProduct(item)
            is BackstageConcertPasses -> BackstageConcertPassesProduct(item)
            is AgedBrie -> AgedBrieProduct(item)
            is Sulfuras -> SulfurasProduct(item)
            else -> null
        }
    }

    fun updateQuality() {
        items.forEach { item ->
            val product = createProduct(item)

            product?.age()
        }
    }
}

