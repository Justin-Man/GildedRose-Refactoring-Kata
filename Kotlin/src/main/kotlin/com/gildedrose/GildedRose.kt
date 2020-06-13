package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            if (item is RegularItem) {
                if (item.quality > 0) item.quality-- // items degrade in quality value except aged brie, passes and Sulfuras
            }
            else {
                if (item.quality < 50) { // The Quality of an item is never more than 50
                    item.quality++ // Therefore keep increasing quality value

                    if (item is BackstageConcertPasses) {
                        if (item.sellIn < 11) { // When there are less than 12 days to sell the pass
                            if (item.quality < 50) { // As long as quality is not over 50
                                item.quality++ // "Backstage passes", increases in Quality as its SellIn value approaches
                            }
                        }

                        if (item.sellIn < 6) { // When there are 5 days or less to sell the pass
                            if (item.quality < 50) {
                                item.quality++ // increase quality if its not more than 50
                            }
                        }
                    }
                }
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

