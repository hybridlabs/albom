package dev.hybridlabs.albom.client.render.entity.giant

import dev.hybridlabs.albom.client.model.entity.giant.FireGiantEntityModel
import dev.hybridlabs.albom.client.model.entity.giant.FrostGiantEntityModel
import dev.hybridlabs.albom.entity.giant.HillGiantEntity
import dev.hybridlabs.albom.client.model.entity.giant.HillGiantEntityModel
import dev.hybridlabs.albom.entity.giant.FireGiantEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class FireGiantEntityRenderer(context: Context) :
    GeoEntityRenderer<FireGiantEntity>(context, FireGiantEntityModel())