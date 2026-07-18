package dev.hybridlabs.albom.entity.faction

import dev.hybridlabs.albom.ALBOM
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType

object FactionTags {
    val ELF = tag("faction/elf")
    val DWARF = tag("faction/dwarf")
    val GOBLIN = tag("faction/goblin")
    val DEMON = tag("faction/demon")

    /** Entity types exempt from alliance (their members attack each other). */
    val FRIENDLY_FIRE = tag("faction_friendly_fire")

    /** All membership tags, in resolution order. Excludes FRIENDLY_FIRE. */
    val FACTIONS: List<TagKey<EntityType<*>>> = listOf(ELF, DWARF, GOBLIN, DEMON)

    private fun tag(path: String): TagKey<EntityType<*>> = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation(ALBOM.MOD_ID, path))
}
