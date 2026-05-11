package dev.hybridlabs.albom

import dev.hybridlabs.albom.item.ALBOMItemGroups
import net.fabricmc.api.ModInitializer
import net.minecraft.core.Registry
import org.slf4j.LoggerFactory

object ALBOM : ModInitializer {
    const val MOD_ID = "albom"
    const val MOD_NAME = "A Little Bit Of Mobs"

    private val logger = LoggerFactory.getLogger(MOD_ID)

    @Suppress("UnusedExpression")
    override fun onInitialize() {
        logger.info("Initializing $MOD_NAME")

        ALBOMItemGroups
    }

    fun <T> Registry<T>.filterAlbomMod(): List<T> {
        return filter { obj ->
            val location = getKey(obj)
            location?.namespace == MOD_ID
        }
    }
}
