package dev.hybridlabs.albom.client.render.entity.fae

import dev.hybridlabs.albom.client.model.entity.fae.PixieEntityModel
import dev.hybridlabs.albom.entity.fae.PixieEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class PixieEntityRenderer(context: Context) :
    GeoEntityRenderer<PixieEntity>(context, PixieEntityModel())