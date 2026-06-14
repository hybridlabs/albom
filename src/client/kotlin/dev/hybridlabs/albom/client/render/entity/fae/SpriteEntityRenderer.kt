package dev.hybridlabs.albom.client.render.entity.fae

import dev.hybridlabs.albom.client.model.entity.fae.SpriteEntityModel
import dev.hybridlabs.albom.entity.fae.SpriteEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class SpriteEntityRenderer(context: Context) :
    GeoEntityRenderer<SpriteEntity>(context, SpriteEntityModel()) {

    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
        this.shadowRadius = 0.3f
    }
}