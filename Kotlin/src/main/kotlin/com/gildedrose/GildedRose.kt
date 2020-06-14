package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            when (item) {
                is RegularItem -> if (item.quality > 0) item.quality-- // items degrade in quality value except aged brie, passes and Sulfuras
                is BackstageConcertPasses ->
                    when(item.quality < 50) {
                        item.sellIn > 10 -> item.quality++
                        item.sellIn in 6..10 -> item.quality += 2
                        item.sellIn < 6 -> item.quality += 3
                    }

                else -> if (item.quality < 50) item.quality++
            }

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

