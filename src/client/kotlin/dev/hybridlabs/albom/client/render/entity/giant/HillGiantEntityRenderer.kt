package dev.hybridlabs.albom.client.render.entity.giant

import dev.hybridlabs.albom.entity.giant.HillGiantEntity
import dev.hybridlabs.albom.client.model.entity.giant.HillGiantEntityModel
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class HillGiantEntityRenderer(context: Context) :
    GeoEntityRenderer<HillGiantEntity>(context, HillGiantEntityModel())