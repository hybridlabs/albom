package dev.hybridlabs.albom.entity.fae

import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.FlyingMoveControl
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.pathfinder.BlockPathTypes
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

open class AbstractFaeEntity(type: EntityType<out AbstractFaeEntity>, world: Level) :
    PathfinderMob(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, -1.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = FlyingMoveControl(this, 20, true)

        return FlyingPathNavigation(this, level)
    }

    override fun registerGoals() {
        goalSelector.addGoal(1, LookAtPlayerGoal(this, Player::class.java, 8.0f, 0.1f, true))
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(3, WaterAvoidingRandomFlyingGoal(this, 0.5))
        super.registerGoals()
    }

    override fun getMobType(): MobType {
        return MobType.UNDEFINED
    }

    override fun getMaxHeadXRot(): Int {
        return 45
    }

    override fun checkFallDamage(d: Double, bl: Boolean, blockState: BlockState, blockPos: BlockPos) {
    }

    override fun getAmbientSound(): SoundEvent? {
        return SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM
    }

    override fun getHurtSound(damageSource: DamageSource): SoundEvent? {
        return SoundEvents.ALLAY_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.ALLAY_DEATH
    }

    override fun getSoundVolume(): Float {
        return 0.2f
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(DefaultAnimations.genericLivingController(this))
        controllerRegistrar.add(DefaultAnimations.genericWalkFlyIdleController(this)
            .transitionLength(4)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    companion object {
        fun canSpawn(
            type: EntityType<out AbstractFaeEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            return world.getBlockState(pos.below()).isSolid &&
                    world.isEmptyBlock(pos) &&
                    world.canSeeSky(pos)
        }
    }
}