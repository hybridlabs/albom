package dev.hybridlabs.albom.item

import dev.hybridlabs.albom.CommonClass
import dev.hybridlabs.albom.entity.ALBOMEntityTypes
import dev.hybridlabs.albom.platform.Services.PLATFORM
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.item.Item
import net.minecraft.world.item.SpawnEggItem
import java.util.function.Supplier

object ALBOMItems {

    val HILL_GIANT_SPAWN_EGG =
        registerSpawnEgg("sea_angel_spawn_egg", ALBOMEntityTypes.HILL_GIANT, 0xc6d5f9, 0xf38135)

    fun register(id: String, item: Supplier<Item>): Supplier<Item> {
        return CommonClass.ITEMS.register(id, item)
    }

    private fun <T : Mob> registerSpawnEgg(
        id: String,
        type: Supplier<EntityType<T>>,
        primaryColor: Int,
        secondaryColor: Int,
    ): Supplier<SpawnEggItem> {
        return PLATFORM.registerSpawnEggItem(
            id,
            { type.get() },
            primaryColor,
            secondaryColor
        )
    }
}