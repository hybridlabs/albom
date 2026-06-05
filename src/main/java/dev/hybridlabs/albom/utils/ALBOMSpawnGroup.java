package dev.hybridlabs.albom.utils;

import dev.hybridlabs.albom.Constants;
import net.minecraft.world.entity.MobCategory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ALBOMSpawnGroup {
    ALBOM_DRAGON("dragon", 10, true, false, 64),
    ALBOM_GIANT("giant", 10, true, false, 64);

    public MobCategory spawnGroup;
    public final String gName;
    public final int spawnCap;
    public final boolean peaceful;
    public final boolean rare;
    public final int immediateDespawnRange;

    ALBOMSpawnGroup(String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        this.gName = Constants.MOD_ID + ":" + name;
        this.spawnCap = spawnCap;
        this.peaceful = peaceful;
        this.rare = rare;
        this.immediateDespawnRange = immediateDespawnRange;
    }

    public static final Map<String, MobCategory> BY_NAME = new ConcurrentHashMap<>();

    public static MobCategory byName(String name) {
        return BY_NAME.get(name);
    }
}