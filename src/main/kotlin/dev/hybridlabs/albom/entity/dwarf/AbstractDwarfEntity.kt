package dev.hybridlabs.albom.entity.dwarf

import dev.hybridlabs.albom.entity.faction.AbstractFactionEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level

open class AbstractDwarfEntity(type: EntityType<out AbstractDwarfEntity>, world: Level) : AbstractFactionEntity(type, world)
