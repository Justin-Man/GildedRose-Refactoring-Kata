package com.gildedrose

interface AgeItemStrategy {
    fun age(item: Item)
}

class QualityDegradesStrategy : AgeItemStrategy {
    override fun age(item: Item) {
        item.sellIn -= 1

        if(item.quality > 0) {
            item.quality -=1

            if (item.sellIn < 0) {
                item.quality -=1
            }
        }
    }
}

class FastDegradingStrategy : AgeItemStrategy {
    override fun age(item: Item) {
        item.sellIn -= 1
        item.quality -= 2

        if (item.quality < 0) {
            item.quality = 0
        }

        if (item.sellIn < 0) {
            item.quality -= 2
        }
    }
}

class QualityIncreasesStrategy : AgeItemStrategy {
    override fun age(item: Item) {
        item.sellIn -= 1
        item.quality += 1

        if (item.quality > 50) {
            item.quality = 50
        }
    }
}

class NeverChangesStrategy : AgeItemStrategy {
    override fun age(item: Item) {}
}

class HigherDemandStrategy : AgeItemStrategy {
    override fun age(item: Item) {
        if (item.sellIn < 0) {
            item.quality = 0
        }
        else if(item.sellIn <= 5) {
            item.quality += 3
        }
        else if(item.sellIn <= 10) {
            item.quality += 2
        } else {
            item.quality += 1
        }

        item.sellIn -= 1
    }
}
