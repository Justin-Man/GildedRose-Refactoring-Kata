package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun createProduct(item: Item) : Product? {
        return when (item.name) {
            "Conjured" -> ConjuredProduct(item)
            "Backstage passes" -> BackstageConcertPassesProduct(item)
            "Aged Brie" -> AgedBrieProduct(item)
            "Sulfuras" -> SulfurasProduct(item)
            else -> RegularProduct(item)
        }
    }

    fun updateQuality() {
        items.forEach { item ->
            val product = createProduct(item)

            product?.age()
        }
    }
}

