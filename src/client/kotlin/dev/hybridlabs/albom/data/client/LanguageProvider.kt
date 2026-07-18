package dev.hybridlabs.albom.data.client

import dev.hybridlabs.albom.ALBOM
import dev.hybridlabs.albom.data.ALBOMDataGenerator.filterALBOM
import dev.hybridlabs.albom.entity.ALBOMEntityTypes
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob

internal class LanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {
    override fun generateTranslations(builder: TranslationBuilder) {
        builder.add("itemGroup.${ALBOM.MOD_ID}.mobs", ALBOM.MOD_NAME)

        generateEntities(builder)
    }

    private fun generateEntities(builder: TranslationBuilder) {
        // create map of entities to their display names
        val entityNameMap = mapOf(
            ALBOMEntityTypes.HILL_GIANT.get() to "Hill Giant",
            ALBOMEntityTypes.FIRE_GIANT.get() to "Fire Giant",
            ALBOMEntityTypes.FROST_GIANT.get() to "Frost Giant",
            ALBOMEntityTypes.SCARAB.get() to "Scarab",
            ALBOMEntityTypes.PIXIE.get() to "Pixie",
            ALBOMEntityTypes.FAIRY.get() to "Fairy",
            ALBOMEntityTypes.SPRITE.get() to "Sprite",
            ALBOMEntityTypes.GOBLIN_GRUNT.get() to "Goblin Grunt",
            ALBOMEntityTypes.GOBLIN_BOMBER.get() to "Goblin Bomber",
            ALBOMEntityTypes.GOBLIN_RIDER.get() to "Goblin Rider",
            ALBOMEntityTypes.GOBLIN_ARCHER.get() to "Goblin Archer",
            ALBOMEntityTypes.GOBLIN_SCOUT.get() to "Goblin Scout",
            ALBOMEntityTypes.GOBLIN_SHAMAN.get() to "Goblin Shaman",
        )

        // verify display name list is valid
        val nonPresentEntityNames = mutableListOf<EntityType<*>>()

        BuiltInRegistries.ENTITY_TYPE
            .filter(filterALBOM(BuiltInRegistries.ENTITY_TYPE))
            .forEach { type ->
                if (type.baseClass.isAssignableFrom(Mob::class.java)) {
                    if (!entityNameMap.containsKey(type)) {
                        nonPresentEntityNames.add(type)
                    }
                }
            }

        if (nonPresentEntityNames.isNotEmpty()) {
            throw IllegalStateException("Entity to display name map does not contain ${nonPresentEntityNames.joinToString()}. Please modify ${javaClass.simpleName} accordingly.")
        }

        // generate entity and entity spawn egg translations
        entityNameMap.forEach { (entityType, translation) ->
            val id = BuiltInRegistries.ENTITY_TYPE.getKey(entityType)
            val translationKey = entityType.descriptionId
            val namespace = id.namespace
            val path = id.path
            builder.add(translationKey, translation)
            builder.add("item.$namespace.${path}_spawn_egg", "$translation Spawn Egg")
        }
    }
}
