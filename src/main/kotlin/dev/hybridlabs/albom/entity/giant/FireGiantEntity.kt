package dev.hybridlabs.albom.entity.giant

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

open class FireGiantEntity(type: EntityType<out AbstractGiantEntity>, world: Level) :
    AbstractGiantEntity(type, world) {

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.MOVEMENT_SPEED, 0.8)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.3)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }
    }
}