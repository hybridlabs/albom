package dev.hybridlabs.albom.entity.faction

import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.phys.AABB

/**
 * Retaliates like vanilla HurtByTargetGoal, but rallies ALL nearby same-faction
 * mobs onto the attacker regardless of their concrete class.
 */
class FactionHurtByTargetGoal(mob: PathfinderMob) : HurtByTargetGoal(mob) {

    override fun alertOthers() {
        val attacker = mob.lastHurtByMob ?: return
        val range = mob.getAttributeValue(Attributes.FOLLOW_RANGE)
        val box: AABB = mob.boundingBox.inflate(range, 10.0, range)

        val allies = mob.level().getEntitiesOfClass(Mob::class.java, box) { candidate ->
            candidate !== mob &&
                candidate.target == null &&
                FactionUtil.sameFaction(mob, candidate) &&
                !candidate.isAlliedTo(attacker)
        }
        for (ally in allies) {
            ally.target = attacker
        }
    }
}
