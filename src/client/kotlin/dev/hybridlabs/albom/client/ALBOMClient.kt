package dev.hybridlabs.albom.client

import dev.hybridlabs.albom.ALBOM
import dev.hybridlabs.albom.client.render.ALBOMEntityRenderers
import net.fabricmc.api.ClientModInitializer
import org.slf4j.LoggerFactory

object ALBOMClient : ClientModInitializer {
    private val logger = LoggerFactory.getLogger("${ALBOM.MOD_ID}-client")

    override fun onInitializeClient() {
        ALBOMEntityRenderers

        logger.info("Initializing ${ALBOM.MOD_NAME} (Client)")
    }
}
