package dev.hybridlabs.albom.client.render

import dev.hybridlabs.albom.client.render.entity.giant.FireGiantEntityRenderer
import dev.hybridlabs.albom.client.render.entity.giant.FrostGiantEntityRenderer
import dev.hybridlabs.albom.entity.ALBOMEntityTypes
import dev.hybridlabs.albom.platform.ClientServices
import dev.hybridlabs.albom.client.render.entity.giant.HillGiantEntityRenderer
import dev.hybridlabs.albom.client.render.entity.scarab.ScarabEntityRenderer

@Suppress("unused")
object ALBOMEntityRenderers {
    val HILL_GIANT =
        ClientServices.PLATFORM.registerEntityRenderer(
            ALBOMEntityTypes.HILL_GIANT,
            ::HillGiantEntityRenderer
        )

    val FIRE_GIANT =
        ClientServices.PLATFORM.registerEntityRenderer(
            ALBOMEntityTypes.FIRE_GIANT,
            ::FireGiantEntityRenderer
        )

    val FROST_GIANT =
        ClientServices.PLATFORM.registerEntityRenderer(
            ALBOMEntityTypes.FROST_GIANT,
            ::FrostGiantEntityRenderer
        )

    val SCARAB =
        ClientServices.PLATFORM.registerEntityRenderer(
            ALBOMEntityTypes.SCARAB,
            ::ScarabEntityRenderer
        )
}