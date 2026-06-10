package dev.hybridlabs.albom.client.model.entity.fae

import dev.hybridlabs.albom.CommonClass
import dev.hybridlabs.albom.entity.fae.AbstractFaeEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

abstract class AbstractFaeEntityModel<T : AbstractFaeEntity>(private val id: String) :
    GeoModel<T>() {

    override fun getModelResource(animatable: T): ResourceLocation {
        return CommonClass.locate("geo/fae/$id/$id.geo.json")
    }

    override fun getTextureResource(animatable: T): ResourceLocation {
        return CommonClass.locate("textures/entity/fae/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): ResourceLocation {
        return CommonClass.locate("animations/entity/fae/$id/$id.animation.json")
    }

    override fun setCustomAnimations(
        animatable: T,
        instanceId: Long,
        animationState: AnimationState<T>,
    ) {
        val head = animationProcessor.getBone("head")

        if (head != null) {
            val entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA)

            head.rotX = entityData.headPitch() * Mth.DEG_TO_RAD
            head.rotY = entityData.netHeadYaw() * Mth.DEG_TO_RAD
        }
    }
}