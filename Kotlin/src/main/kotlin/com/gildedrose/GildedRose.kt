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

            if (item.sellIn < 0) { // if past the sell by date
                if (item !is AgedBrie) {
                    if (item !is BackstageConcertPasses) {
                        if (item.quality > 0) { // The Quality of an item is never negative
                            if (item !is Sulfuras) {
                                item.quality--
                            }
                        }
                    } else {
                        item.quality = 0 // Quality of Backstage passes drops to 0 after the concert
                    }
                }
                else { // For Aged brie, backstage passes and Sulfuras, increase quality
                    if (item.quality < 50) {
                        item.quality++
                    }
                }
            }
        }
    }

    private fun decreaseSellInValue(item: Item) {
        if (item is Sulfuras) return
        item.sellIn--
    }

}

