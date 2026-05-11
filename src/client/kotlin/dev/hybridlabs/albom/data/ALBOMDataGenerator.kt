package dev.hybridlabs.albom.data

import dev.hybridlabs.albom.ALBOM
import dev.hybridlabs.albom.data.client.LanguageProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import org.slf4j.LoggerFactory

internal object ALBOMDataGenerator : DataGeneratorEntrypoint {
    private val logger = LoggerFactory.getLogger("${ALBOM.MOD_ID}-data")

    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        logger.info("Initializing ${ALBOM.MOD_NAME} (Data)")

        val pack = generator.createPack()

        pack.addProvider(::LanguageProvider)
    }
}
