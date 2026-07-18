package dev.hybridlabs.albom.entity.faction

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.ai.goal.FloatGoal
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

open class AbstractFactionEntity(type: EntityType<out AbstractFactionEntity>, world: Level) :
    Monster(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun registerGoals() {
        goalSelector.addGoal(0, FloatGoal(this))
        goalSelector.addGoal(2, MeleeAttackGoal(this, 1.0, true))
        goalSelector.addGoal(6, WaterAvoidingRandomStrollGoal(this, 0.6))
        goalSelector.addGoal(7, LookAtPlayerGoal(this, Player::class.java, 8.0f))
        goalSelector.addGoal(8, RandomLookAroundGoal(this))

        targetSelector.addGoal(1, FactionHurtByTargetGoal(this))
        super.registerGoals()
    }

    /**
     * Suppresses targeting of faction allies (revenge included) and is the future
     * hook point for per-player reputation. Consulted by TargetingConditions.
     */
    override fun canAttack(target: LivingEntity): Boolean = super.canAttack(target) && !FactionUtil.areAllied(this, target)

    override fun isAlliedTo(other: Entity): Boolean = super.isAlliedTo(other) || (other is LivingEntity && FactionUtil.areAllied(this, other))

    override fun getMobType(): MobType = MobType.UNDEFINED

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(DefaultAnimations.genericWalkRunIdleController(this))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache = factory
}
