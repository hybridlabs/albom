package dev.hybridlabs.albom.entity.fae

import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.control.FlyingMoveControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
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

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(DefaultAnimations.genericLivingController(this))
        controllerRegistrar.add(DefaultAnimations.genericFlyIdleController(this)
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