package dev.hybridlabs.albom.data

import dev.hybridlabs.albom.entity.ALBOMEntityTypes
import dev.hybridlabs.albom.entity.faction.FactionTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.EntityType
import java.util.concurrent.CompletableFuture

internal class FactionTagProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
) : FabricTagProvider<EntityType<*>>(output, Registries.ENTITY_TYPE, registriesFuture) {

    override fun addTags(wrapperLookup: HolderLookup.Provider) {
        // Goblin faction — the only registered members today.
        getOrCreateTagBuilder(FactionTags.GOBLIN)
            .add(ALBOMEntityTypes.GOBLIN_GRUNT.get())
            .add(ALBOMEntityTypes.GOBLIN_BOMBER.get())
            .add(ALBOMEntityTypes.GOBLIN_RIDER.get())
            .add(ALBOMEntityTypes.GOBLIN_ARCHER.get())
            .add(ALBOMEntityTypes.GOBLIN_SCOUT.get())
            .add(ALBOMEntityTypes.GOBLIN_SHAMAN.get())

        // Empty faction tags so the files exist and future mobs drop in.
        getOrCreateTagBuilder(FactionTags.ELF)
        getOrCreateTagBuilder(FactionTags.DWARF)
        getOrCreateTagBuilder(FactionTags.DEMON)

        // Demons attack each other: mark the whole demon tag friendly-fire.
        getOrCreateTagBuilder(FactionTags.FRIENDLY_FIRE)
            .addTag(FactionTags.DEMON)
    }
}
