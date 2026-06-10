package dev.hybridlabs.albom.client.render.entity.fae

import dev.hybridlabs.albom.client.model.entity.fae.SpriteEntityModel
import dev.hybridlabs.albom.entity.fae.SpriteEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class SpriteEntityRenderer(context: Context) :
    GeoEntityRenderer<SpriteEntity>(context, SpriteEntityModel())