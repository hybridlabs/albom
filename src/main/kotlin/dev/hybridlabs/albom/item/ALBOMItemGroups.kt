package dev.hybridlabs.albom.item

import dev.hybridlabs.albom.ALBOM
import dev.hybridlabs.albom.ALBOM.filterAlbomMod
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

object ALBOMItemGroups {
    val MOBS = register("albom",
        FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.${ALBOM.MOD_ID}.mobs"))
            .icon { ItemStack(Items.BAT_SPAWN_EGG) }
            .displayItems { _, entries ->
                BuiltInRegistries.BLOCK.filterAlbomMod().forEach(entries::accept)
            }
            .build()
    )

    private fun register(id: String, group: CreativeModeTab): CreativeModeTab {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation(ALBOM.MOD_ID, id), group)
    }
}
