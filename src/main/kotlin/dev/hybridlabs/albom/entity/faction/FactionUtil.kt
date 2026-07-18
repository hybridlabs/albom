package dev.hybridlabs.albom.entity.faction

import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity

object FactionUtil {

    /** The first faction tag this entity's type belongs to, or null if factionless. */
    fun factionTagOf(entity: LivingEntity): TagKey<EntityType<*>>? {
        val holder = entity.type.builtInRegistryHolder()
        return FactionTags.FACTIONS.firstOrNull { holder.`is`(it) }
    }

    /** True if both entities resolve to the same (non-null) faction. */
    fun sameFaction(a: LivingEntity, b: LivingEntity): Boolean {
        val fa = factionTagOf(a) ?: return false
        return fa == factionTagOf(b)
    }

    /** True if this entity's type is flagged friendly-fire (e.g. demons). */
    fun isFriendlyFire(entity: LivingEntity): Boolean = entity.type.builtInRegistryHolder().`is`(FactionTags.FRIENDLY_FIRE)

    /**
     * Allied = same faction AND neither side is friendly-fire.
     * Allies never target or rally against each other.
     */
    fun areAllied(a: LivingEntity, b: LivingEntity): Boolean = sameFaction(a, b) && !isFriendlyFire(a) && !isFriendlyFire(b)
}
