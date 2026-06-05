package dev.hybridlabs.albom.client.model.entity.giant

import dev.hybridlabs.albom.CommonClass
import dev.hybridlabs.albom.entity.giant.AbstractGiantEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib.model.GeoModel

abstract class AbstractGiantEntityModel<T : AbstractGiantEntity>(private val id: String) :
    GeoModel<T>() {

    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/giant/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/giant/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/entity/giant/$id/$id.animation.json")
    }
}