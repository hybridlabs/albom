package dev.hybridlabs.albom.entity.elf

import dev.hybridlabs.albom.entity.faction.AbstractFactionEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level

open class AbstractElfEntity(type: EntityType<out AbstractElfEntity>, world: Level) : AbstractFactionEntity(type, world)
