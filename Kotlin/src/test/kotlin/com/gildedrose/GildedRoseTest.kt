package com.gildedrose

import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test

class GildedRoseTest {

    @Test
    fun `decrease quality of regular item as sell-in decreases`() {
        val item = Item("item", 8, 10)
        val items = arrayOf(item)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().quality).isEqualTo(9)
    }

    @Test
    fun `should decrease regular item quality twice as fast when sell-in value is passed`() {
        val item = Item("item", -1, 10)
        val items = arrayOf(item)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().quality).isEqualTo(8)
    }

    @Test
    fun `decreases conjured item quality four times as fast when sell-in passed`() {
        val conjuredItem = Item("Conjured", 0, 4)

        val items = arrayOf(conjuredItem)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().quality).isEqualTo(0)
    }

    @Test
    fun `should not decrease quality below zero for items that degrade`() {
        val regularItem = Item("item", 8, 0)
        val conjuredItem = Item("Conjured", 8, 0)
        val items = arrayOf(regularItem, conjuredItem)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertTrue(items.all { it.quality == 0 })
    }

    @Test
    fun `increase quality of aged brie when sell-in date decreases`() {
        val item = Item(name = "Aged Brie", sellIn = 8, quality = 10)
        val items = arrayOf(item)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().quality).isEqualTo(11)
    }

    @Test
    fun `should not increase item quality more than 50`() {
        val item = Item(name = "Aged Brie", sellIn = 8, quality = 50)
        val items = arrayOf(item)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().quality).isEqualTo(50)
    }

    @Test
    fun `should not decrease sulfuras sell-in or quality value`() {
        val item = Item(name = "Sulfuras", sellIn = 8, quality = 80)
        val items = arrayOf(item)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().sellIn).isEqualTo(8)
        assertThat(items.first().quality).isEqualTo(80)
    }

    @Test
    fun `increase backstage passes quality by one when sell-in value of more than 10 decreases`() {
        val item = Item(name = "Backstage passes", sellIn = 11, quality = 20)
        val items = arrayOf(item)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().quality).isEqualTo(21)
    }

    @Test
    fun `increase backstage passes quality by two when sell-in value of 10 or less decreases`() {
        val item = Item(name = "Backstage passes", sellIn = 10, quality = 20)
        val items = arrayOf(item)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().quality).isEqualTo(22)
    }

    @Test
    fun `increase backstage passes quality by three when sell-in value of 5 or less decreases`() {
        val item = Item(name = "Backstage passes", sellIn = 5, quality = 20)
        val items = arrayOf(item)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().quality).isEqualTo(23)
    }

    @Test
    fun `decrease backstage passes quality to zero when sell-in value is less than 0`() {
        val item = Item(name = "Backstage passes", sellIn = -1, quality = 20)
        val items = arrayOf(item)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().quality).isEqualTo(0)
    }

    @Test
    fun `decrease quality of conjured item twice as fast as regular item`() {
        val item = Item(name = "Conjured", sellIn = 10, quality = 20)
        val items = arrayOf(item)
        val gildedRose = GildedRose(items)

        gildedRose.updateQuality()

        assertThat(items.first().quality).isEqualTo(18)
    }
}


