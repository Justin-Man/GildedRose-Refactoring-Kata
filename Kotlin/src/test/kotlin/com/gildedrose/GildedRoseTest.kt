package com.gildedrose

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GildedRoseTest {


    @Test
    fun `decrease quality of regular item as sell-in decreases`() {
        val items = arrayOf(Item("item", 8, 10))
        val app = GildedRose(items)

        app.updateQuality()

        assertThat(items.first().quality).isEqualTo(9)
    }

    @Test
    fun `should decrease regular item quality twice as fast when sell-in value is passed`() {
        val items = arrayOf(Item("item", -1, 10))
        val app = GildedRose(items)

        app.updateQuality()

        assertThat(items.first().quality).isEqualTo(8)
    }

    @Test
    fun `should not decrease item quality when quality is zero`() {
        val items = arrayOf(Item("item", 8, 0))
        val app = GildedRose(items)

        app.updateQuality()

        assertThat(items.first().quality).isEqualTo(0)
    }

    @Test
    fun `increase quality of aged brie when sell-in date decreases`() {
        val items = arrayOf(Item("Aged Brie", 8, 10))
        val app = GildedRose(items)

        app.updateQuality()

        assertThat(items.first().quality).isEqualTo(11)
    }

    @Test
    fun `should not increase item quality more than 50`() {
        val items = arrayOf(Item("Aged Brie", 8, 50))
        val app = GildedRose(items)

        app.updateQuality()

        assertThat(items.first().quality).isEqualTo(50)
    }

    @Test
    fun `should not decrease sulfuras sell-in or quality value`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 8, 80))
        val app = GildedRose(items)

        app.updateQuality()
        assertThat(items.first().sellIn).isEqualTo(8)
        assertThat(items.first().quality).isEqualTo(80)
    }

    @Test
    fun `increase backstage passes quality by one when sell-in value of more than 10 decreases`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertThat(items.first().quality).isEqualTo(21)
    }

    @Test
    fun `increase backstage passes quality by two when sell-in value of 10 or less decreases`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertThat(items.first().quality).isEqualTo(22)
    }

    @Test
    fun `increase backstage passes quality by three when sell-in value of 5 or less decreases`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertThat(items.first().quality).isEqualTo(23)
    }

    @Test
    fun `decrease backstage passes quality to zero when sell-in value is less than 0`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertThat(items.first().quality).isEqualTo(0)
    }
}


