package dev.hybridlabs.albom.entity.demon

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.level.Level
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

open class AbstractDemonEntity(type: EntityType<out AbstractDemonEntity>, world: Level) :
    Monster(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun getMobType(): MobType {
        return MobType.UNDEFINED
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(DefaultAnimations.genericWalkRunIdleController(this))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }
}