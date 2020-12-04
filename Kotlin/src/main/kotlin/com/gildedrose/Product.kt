package com.gildedrose

abstract class Product(protected val item: Item) {

   abstract fun age()
}

class RegularProduct(item: Item) : Product(item) {
    override fun age() {
        item.sellIn--
        var degradeRate = 1
        if (item.quality > 0) {
            if(item.sellIn < 0) {
                degradeRate = 2
            }
            item.quality -= degradeRate
        }
    }
}

class ConjuredProduct(item: Item) : Product(item) {
    override fun age() {
        if (item.quality > 0) item.quality -= 2
        item.sellIn--
    }
}

class BackstageConcertPassesProduct(item: Item): Product(item) {
    override fun age() {
        if(item.quality < 50) {
            when {
                item.sellIn > 10 -> item.quality++
                item.sellIn in 6..10 -> item.quality += 2
                item.sellIn < 6 -> item.quality += 3
            }
        }
        item.sellIn--
        if (item.sellIn < 0) item.quality = 0
    }
}

class AgedBrieProduct(item: Item) : Product(item) {
    override fun age() {
        if (item.quality < 50) item.quality++
        item.sellIn--
    }
}

class SulfurasProduct(item: Item) : Product(item) {
    override fun age() {}
}