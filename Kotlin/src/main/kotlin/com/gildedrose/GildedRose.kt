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

            product?.updateQuality()

            decreaseSellInValue(item)

            if (item.sellIn < 0) {
                when {
                    item is AgedBrie -> increaseQuality(item)
                    item is BackstageConcertPasses -> item.quality = 0
                    item !is Sulfuras && item.quality > 0 -> item.quality--
                }
            }
        }
    }

    private fun increaseQuality(item: Item) {
        if (item.quality < 50) item.quality++
    }

    private fun decreaseSellInValue(item: Item) {
        if (item !is Sulfuras) item.sellIn--
    }
}

