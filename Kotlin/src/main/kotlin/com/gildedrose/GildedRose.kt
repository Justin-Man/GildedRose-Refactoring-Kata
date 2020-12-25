package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun getStrategy(item: Item) : AgeItemStrategy {
        return when(item.name) {
            "Aged Brie" -> QualityIncreasesStrategy()
            "Sulfuras" -> NeverChangesStrategy()
            "Backstage passes" -> HigherDemandStrategy()
            "Conjured" -> FastDegradingStrategy()
            else -> QualityDegradesStrategy()
        }
    }

    fun updateQuality() {
        items.forEach { item ->
            getStrategy(item).age(item)
        }
    }
}

