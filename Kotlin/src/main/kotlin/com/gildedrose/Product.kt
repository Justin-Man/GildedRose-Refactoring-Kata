package com.gildedrose

abstract class Product(protected val item: Item) {

   abstract fun updateQuality(): Unit
}

class RegularProduct(item: Item) : Product(item) {
    override fun updateQuality() {
        if (item.quality > 0) item.quality--
    }
}

class ConjuredProduct(item: Item) : Product(item) {
    override fun updateQuality() {
        if (item.quality > 0) item.quality -= 2
    }
}

class BackstageConcertPassesProduct(item: Item): Product(item) {
    override fun updateQuality() {
        if(item.quality < 50) {
            when {
                item.sellIn > 10 -> item.quality++
                item.sellIn in 6..10 -> item.quality += 2
                item.sellIn < 6 -> item.quality += 3
            }
        }
    }
}

class AgedBrieProduct(item: Item) : Product(item) {
    override fun updateQuality() {
        if (item.quality < 50) item.quality++
    }
}

class SulfurasProduct(item: Item) : Product(item) {
    override fun updateQuality() {}
}