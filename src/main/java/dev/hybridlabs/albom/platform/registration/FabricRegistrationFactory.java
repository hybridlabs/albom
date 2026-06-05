package dev.hybridlabs.albom.platform.registration;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class FabricRegistrationFactory implements RegistrationProvider.Factory {

    @Override
    public <T> RegistrationProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
        return new Provider<>(modId, resourceKey);
    }

    @SuppressWarnings("unchecked")
    private static <T> Registry<T> getRegistry(ResourceKey<? extends Registry<T>> key) {
        return (Registry<T>) Objects.requireNonNull(
                BuiltInRegistries.REGISTRY.get(key.location()),
                "No registry found for " + key.location());
    }

    private static class Provider<T> implements RegistrationProvider<T> {
        private final String modId;
        private final ResourceKey<? extends Registry<T>> registryKey;
        private final Set<RegistryObject<T>> entries = new LinkedHashSet<>();

        Provider(String modId, ResourceKey<? extends Registry<T>> registryKey) {
            this.modId = modId;
            this.registryKey = registryKey;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <I extends T> RegistryObject<I> register(String name, Supplier<? extends I> supplier) {
            Registry<T> registry = getRegistry(registryKey);
            ResourceLocation id = new ResourceLocation(modId, name);
            I value = Registry.register(registry, id, supplier.get());
            ResourceKey<I> key = ResourceKey.create(
                    (ResourceKey<? extends Registry<I>>) (ResourceKey<?>) registryKey, id);
            Holder.Reference<I> holder = ((Registry<I>) registry).getHolderOrThrow(key);
            RegistryObject<I> obj = new FabricRegistryObject<>(id, key, value, holder);
            entries.add((RegistryObject<T>) obj);
            return obj;
        }

        @Override
        public Collection<RegistryObject<T>> getEntries() {
            return Collections.unmodifiableSet(entries);
        }

        @Override
        public String getModId() {
            return modId;
        }
    }

    private static final class FabricRegistryObject<T> implements RegistryObject<T> {
        private final ResourceLocation id;
        private final ResourceKey<T> key;
        private final T value;
        private final Holder.Reference<T> holder;

        FabricRegistryObject(ResourceLocation id, ResourceKey<T> key, T value, Holder.Reference<T> holder) {
            this.id = id;
            this.key = key;
            this.value = value;
            this.holder = holder;
        }

        @Override public ResourceKey<T> getResourceKey() { return key; }
        @Override public ResourceLocation getId() { return id; }
        @Override public T get() { return value; }
        @Override public Holder<T> asHolder() { return holder; }
    }
}
