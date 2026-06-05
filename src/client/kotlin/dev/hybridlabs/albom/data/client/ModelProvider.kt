package dev.hybridlabs.albom.data.client

import dev.hybridlabs.albom.data.ALBOMDataGenerator.filterALBOM
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelLocationUtils
import net.minecraft.world.item.SpawnEggItem

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {

    override fun generateBlockStateModels(generator: BlockModelGenerators) {
        BuiltInRegistries.ITEM
            .filter(filterALBOM(BuiltInRegistries.ITEM))
            .filterIsInstance<SpawnEggItem>()
            .forEach { item ->
                generator.delegateItemModel(
                    item,
                    ModelLocationUtils.decorateItemModelLocation("template_spawn_egg")
                )
            }
    }

    override fun generateItemModels(generator: ItemModelGenerators) {

    }
}