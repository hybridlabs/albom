package dev.hybridlabs.albom.entity

import dev.hybridlabs.albom.entity.fae.AbstractFaeEntity
import dev.hybridlabs.albom.entity.giant.AbstractGiantEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.level.levelgen.Heightmap

/**
 * Registers spawn restrictions for all entities when initialised.
 */
object SpawnRestrictionRegistry {
    fun registerSpawnRestrictions() {
        // giants
        setOf(
            ALBOMEntityTypes.HILL_GIANT.get(),
        ).forEach { registerGiant(it) }

        // fae
        setOf(
            ALBOMEntityTypes.FAIRY.get(),
            ALBOMEntityTypes.PIXIE.get(),
            ALBOMEntityTypes.SPRITE.get(),
        ).forEach { registerFae(it) }
    }

    private fun <T : AbstractGiantEntity> registerGiant(entityType: EntityType<T>) {
        registerGiant(entityType, AbstractGiantEntity::canSpawn)
    }

    private fun <T : Monster> registerGiant(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>,
    ) {
        register(
            entityType,
            SpawnPlacements.Type.ON_GROUND,
            predicate
        )
    }

    private fun <T : AbstractFaeEntity> registerFae(entityType: EntityType<T>) {
        registerFae(entityType, AbstractFaeEntity::canSpawn)
    }

    private fun <T : Mob> registerFae(
        entityType: EntityType<T>,
        predicate: SpawnPlacements.SpawnPredicate<T>,
    ) {
        register(
            entityType,
            SpawnPlacements.Type.NO_RESTRICTIONS,
            predicate
        )
    }

    private fun <T : Mob> register(
        entityType: EntityType<T>,
        location: SpawnPlacements.Type,
        predicate: SpawnPlacements.SpawnPredicate<T>,
    ) {
        SpawnPlacements.register(entityType, location, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, predicate)
    }
}
