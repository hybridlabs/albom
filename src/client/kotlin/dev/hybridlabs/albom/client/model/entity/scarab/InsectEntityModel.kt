package dev.hybridlabs.albom.client.model.entity.scarab

import dev.hybridlabs.albom.CommonClass
import dev.hybridlabs.albom.entity.insect.ScarabEntity
import dev.hybridlabs.albom.entity.giant.AbstractGiantEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

abstract class InsectEntityModel<T : ScarabEntity>(private val id: String) :
    GeoModel<T>() {

    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/insect/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/insect/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/entity/insect/$id/$id.animation.json")
    }
}