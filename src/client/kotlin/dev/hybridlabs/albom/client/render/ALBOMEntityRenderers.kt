package dev.hybridlabs.albom.client.render

import dev.hybridlabs.albom.entity.ALBOMEntityTypes
import dev.hybridlabs.albom.platform.ClientServices
import dev.hybridlabs.albom.client.render.entity.giant.HillGiantEntityRenderer

object ALBOMEntityRenderers {
    val HILL_GIANT =
        ClientServices.PLATFORM.registerEntityRenderer(
            ALBOMEntityTypes.HILL_GIANT,
            ::HillGiantEntityRenderer
        )
}