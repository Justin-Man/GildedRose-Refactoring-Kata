package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].quality > 0) {
                    if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                        items[i].quality = items[i].quality - 1 // items degrade in quality value except aged brie, passes and Sulfuras
                    }
                }
            }
            else {
                if (items[i].quality < 50) { // The Quality of an item is never more than 50
                    items[i].quality = items[i].quality + 1 // Therefore keep increasing quality value

                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sellIn < 11) { // When there are less than 12 days to sell the pass
                            if (items[i].quality < 50) { // As long as quality is not over 50
                                items[i].quality = items[i].quality + 1 // "Backstage passes", increases in Quality as its SellIn value approaches
                            }
                        }

                        if (items[i].sellIn < 6) { // When there are less than 7 days to sell the item
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1 // increase quality if its not more than 50
                            }
                        }
                    }
                }
            }

            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                items[i].sellIn = items[i].sellIn - 1 // decrease the number of days to sell the item
            }

            if (items[i].sellIn < 0) { // if past the sell by date
                if (items[i].name != "Aged Brie") {
                    if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].quality > 0) { // The Quality of an item is never negative
                            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality // Quality of Backstage passes drops to 0 after the concert
                    }
                } else { // For Aged brie, backstage passes and Sulfuras, increase quality
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }

}

