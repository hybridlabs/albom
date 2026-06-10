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
        registerSpawnEgg("hill_giant_spawn_egg", ALBOMEntityTypes.HILL_GIANT, 0xc6d5f9, 0xf38135)
    val FIRE_GIANT_SPAWN_EGG =
        registerSpawnEgg("fire_giant_spawn_egg", ALBOMEntityTypes.FIRE_GIANT, 0xc6d5f9, 0xf38135)
    val FROST_GIANT_SPAWN_EGG =
        registerSpawnEgg("frost_giant_spawn_egg", ALBOMEntityTypes.FROST_GIANT, 0xc6d5f9, 0xf38135)
    val SCARAB_SPAWN_EGG =
        registerSpawnEgg("scarab_spawn_egg", ALBOMEntityTypes.SCARAB, 0xc6d5f9, 0xf38135)
    val FAIRY_SPAWN_EGG =
        registerSpawnEgg("fairy_spawn_egg", ALBOMEntityTypes.FAIRY, 0xc615f9, 0xf31135)
    val PIXIE_SPAWN_EGG =
        registerSpawnEgg("pixie_spawn_egg", ALBOMEntityTypes.PIXIE, 0xc615f9, 0xf31135)
    val SPRITE_SPAWN_EGG =
        registerSpawnEgg("sprite_spawn_egg", ALBOMEntityTypes.SPRITE, 0xc615f9, 0xf31135)

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