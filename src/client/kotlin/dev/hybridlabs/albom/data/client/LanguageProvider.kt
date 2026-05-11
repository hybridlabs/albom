package dev.hybridlabs.albom.data.client

import dev.hybridlabs.albom.ALBOM
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider

internal class LanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {
    override fun generateTranslations(builder: TranslationBuilder) {
        builder.add("itemGroup.${ALBOM.MOD_ID}.mobs", ALBOM.MOD_NAME)
    }
}
