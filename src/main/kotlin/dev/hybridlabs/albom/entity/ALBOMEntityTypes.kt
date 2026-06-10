package dev.hybridlabs.albom.entity

import dev.hybridlabs.albom.CommonClass
import dev.hybridlabs.albom.entity.fae.FairyEntity
import dev.hybridlabs.albom.entity.fae.PixieEntity
import dev.hybridlabs.albom.entity.fae.SpriteEntity
import dev.hybridlabs.albom.entity.giant.FireGiantEntity
import dev.hybridlabs.albom.entity.giant.FrostGiantEntity
import dev.hybridlabs.albom.entity.giant.HillGiantEntity
import dev.hybridlabs.albom.entity.insect.ScarabEntity
import dev.hybridlabs.albom.platform.Services
import dev.hybridlabs.albom.platform.registration.RegistryObject
import net.minecraft.world.entity.EntityDimensions
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import java.util.concurrent.Callable

object ALBOMEntityTypes {

    val SCARAB = registerMonster(
        "scarab",
        ::ScarabEntity,
        EntityDimensions.fixed(0.5f, 0.3f),
        ScarabEntity::createMobAttributes
    )

    val HILL_GIANT = registerMonster(
        "hill_giant",
        ::HillGiantEntity,
        EntityDimensions.fixed(2.0f, 4.0f),
        HillGiantEntity::createMobAttributes
    )

    val FIRE_GIANT = registerMonster(
        "fire_giant",
        ::FireGiantEntity,
        EntityDimensions.fixed(3.0f, 6.0f),
        FireGiantEntity::createMobAttributes
    )

    val FROST_GIANT = registerMonster(
        "frost_giant",
        ::FrostGiantEntity,
        EntityDimensions.fixed(3.0f, 6.0f),
        FrostGiantEntity::createMobAttributes
    )

    val FAIRY = registerFae(
        "fairy",
        ::FairyEntity,
        EntityDimensions.fixed(0.5f, 0.75f),
        FairyEntity::createMobAttributes
    )

    val PIXIE = registerFae(
        "pixie",
        ::PixieEntity,
        EntityDimensions.fixed(0.5f, 0.75f),
        PixieEntity::createMobAttributes
    )

    val SPRITE = registerFae(
        "sprite",
        ::SpriteEntity,
        EntityDimensions.fixed(0.5f, 0.75f),
        SpriteEntity::createMobAttributes
    )

    private fun <T : LivingEntity> registerMonster(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 6,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id, entityFactory, dimensions, attributeContainer,
            MobCategory.MONSTER,
            trackingRange,
        )
    }

    private fun <T : LivingEntity> registerFae(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 6,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id, entityFactory, dimensions, attributeContainer,
            MobCategory.CREATURE,
            trackingRange,
        )
    }

    private fun <T : LivingEntity> registerHumanoid(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        trackingRange: Int = 6,
    ): RegistryObject<EntityType<T>> {
        return registerCustomSpawnGroup(
            id, entityFactory, dimensions, attributeContainer,
            MobCategory.CREATURE,
            trackingRange,
        )
    }

    /**
     * Registers a living entity to the entity type registry with an ALBOM spawn group.
     */
    private fun <T : LivingEntity> registerCustomSpawnGroup(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        albomSpawnGroup: MobCategory,
        trackingRange: Int = 5,
        updateInterval: Int = 3,
        canSpawnFarFromPlayer: Boolean = false,
    ): RegistryObject<EntityType<T>> {
        return registerLiving(
            id,
            entityFactory,
            dimensions,
            attributeContainer,
            albomSpawnGroup,
            trackingRange,
            updateInterval,
            canSpawnFarFromPlayer
        )
    }

    /**
     * Registers a living entity to the entity type registry.
     */
    private fun <T : LivingEntity> registerLiving(
        id: String,
        entityFactory: EntityType.EntityFactory<T>,
        dimensions: EntityDimensions,
        attributeContainer: Callable<AttributeSupplier.Builder>,
        spawnGroup: MobCategory,
        clientTrackingRange: Int = 5,
        updateInterval: Int = 3,
        canSpawnFarFromPlayer: Boolean = false,
    ): RegistryObject<EntityType<T>> {
        val entityType = EntityType.Builder
            .of(entityFactory, spawnGroup)
            .sized(dimensions.width, dimensions.height)
            .clientTrackingRange(clientTrackingRange)
            .updateInterval(updateInterval)

        if (canSpawnFarFromPlayer) {
            entityType.canSpawnFarFromPlayer()
        }

        return register(id, entityType, attributeContainer)
    }

    private fun <T : LivingEntity> register(
        id: String,
        entity: EntityType.Builder<T>,
        attributeContainer: Callable<AttributeSupplier.Builder>,
    ): RegistryObject<EntityType<T>> {
        return CommonClass.ENTITY_TYPES.register(id) {
            val entityType = entity.build(id)
            Services.PLATFORM.registerAttributes(id, entityType, attributeContainer)
            entityType
        }
    }
}