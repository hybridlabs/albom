package dev.hybridlabs.albom.client.render.entity.scarab

import dev.hybridlabs.albom.client.model.entity.scarab.ScarabEntityModel
import dev.hybridlabs.albom.entity.insect.ScarabEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class ScarabEntityRenderer(context: Context) :
    GeoEntityRenderer<ScarabEntity>(context, ScarabEntityModel())