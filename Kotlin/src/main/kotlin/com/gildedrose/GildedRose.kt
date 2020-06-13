package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            if (items[i] is RegularItem) {
                if (items[i].quality > 0) items[i].quality-- // items degrade in quality value except aged brie, passes and Sulfuras
            }
            else {
                if (items[i].quality < 50) { // The Quality of an item is never more than 50
                    items[i].quality++ // Therefore keep increasing quality value

                    if (items[i] is BackstageConcertPasses) {
                        if (items[i].sellIn < 11) { // When there are less than 12 days to sell the pass
                            if (items[i].quality < 50) { // As long as quality is not over 50
                                items[i].quality++ // "Backstage passes", increases in Quality as its SellIn value approaches
                            }
                        }

                        if (items[i].sellIn < 6) { // When there are less than 7 days to sell the item
                            if (items[i].quality < 50) {
                                items[i].quality++ // increase quality if its not more than 50
                            }
                        }
                    }
                }
            }

            decreaseSellInValue(items[i])

            if (items[i].sellIn < 0) { // if past the sell by date
                if (items[i] !is AgedBrie) {
                    if (items[i] !is BackstageConcertPasses) {
                        if (items[i].quality > 0) { // The Quality of an item is never negative
                            if (items[i] !is Sulfuras) {
                                items[i].quality--
                            }
                        }
                    } else {
                        items[i].quality = 0 // Quality of Backstage passes drops to 0 after the concert
                    }
                }
                else { // For Aged brie, backstage passes and Sulfuras, increase quality
                    if (items[i].quality < 50) {
                        items[i].quality++
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

