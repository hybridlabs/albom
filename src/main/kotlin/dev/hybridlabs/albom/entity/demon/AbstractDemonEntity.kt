package dev.hybridlabs.albom.entity.demon

import dev.hybridlabs.albom.entity.faction.AbstractFactionEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level

open class AbstractDemonEntity(type: EntityType<out AbstractDemonEntity>, world: Level) : AbstractFactionEntity(type, world)
