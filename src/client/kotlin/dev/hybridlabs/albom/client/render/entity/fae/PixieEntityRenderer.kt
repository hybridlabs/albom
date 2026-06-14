package dev.hybridlabs.albom.client.render.entity.fae

import dev.hybridlabs.albom.client.model.entity.fae.PixieEntityModel
import dev.hybridlabs.albom.entity.fae.PixieEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class PixieEntityRenderer(context: Context) :
    GeoEntityRenderer<PixieEntity>(context, PixieEntityModel()) {

    init {
        this.shadowRadius = 0.3f
    }
}