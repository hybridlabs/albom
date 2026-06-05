package dev.hybridlabs.albom.entity.giant

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

open class AbstractGiantEntity(type: EntityType<out AbstractGiantEntity>, world: Level) :
    Monster(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun registerGoals() {
        goalSelector.addGoal(1, MeleeAttackGoal(this, 1.0, true))
        goalSelector.addGoal(1, LookAtPlayerGoal(this, Player::class.java, 8.0f, 0.1f, true))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.5))
        super.registerGoals()
    }

    override fun getMobType(): MobType {
        return MobType.UNDEFINED
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(DefaultAnimations.genericLivingController(this))
        controllerRegistrar.add(DefaultAnimations.genericWalkRunIdleController(this)
            .transitionLength(4)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }
}