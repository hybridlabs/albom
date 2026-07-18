package dev.hybridlabs.albom.data

import dev.hybridlabs.albom.ALBOM
import dev.hybridlabs.albom.Constants
import dev.hybridlabs.albom.data.client.LanguageProvider
import dev.hybridlabs.albom.data.client.ModelProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.core.Registry
import org.slf4j.LoggerFactory

internal object ALBOMDataGenerator : DataGeneratorEntrypoint {
    private val logger = LoggerFactory.getLogger("${ALBOM.MOD_ID}-data")

    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        logger.info("Initializing ${ALBOM.MOD_NAME} (Data)")

        val pack = generator.createPack()

        pack.addProvider(::LanguageProvider)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::FactionTagProvider)
    }

    fun <T> filterALBOM(registry: Registry<T>): (T & Any) -> Boolean {
        return { o ->
            val id = registry.getKey(o)
            id!!.namespace == Constants.MOD_ID
        }
    }
}