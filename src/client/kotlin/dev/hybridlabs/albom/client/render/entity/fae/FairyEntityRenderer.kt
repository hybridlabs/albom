package dev.hybridlabs.albom.client.render.entity.fae

import dev.hybridlabs.albom.client.model.entity.fae.FairyEntityModel
import dev.hybridlabs.albom.entity.fae.FairyEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class FairyEntityRenderer(context: Context) :
    GeoEntityRenderer<FairyEntity>(context, FairyEntityModel())