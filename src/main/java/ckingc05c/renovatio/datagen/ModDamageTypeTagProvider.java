package ckingc05c.renovatio.datagen;

import ckingc05c.renovatio.combat.ModDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagProvider extends FabricTagProvider.DynamicRegistryTagProvider<ModDamageTypes> {
    public ModDamageTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModDamageTypes.IS_FIRE_DAMAGE)
                .add(ModDamageTypes.FIRE_DAMAGE)
                .add(ModDamageTypes.COMBUSTION_DAMAGE)
                .add(ModDamageTypes.LAVA_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_EXPLOSION_DAMAGE)
                .add(ModDamageTypes.EXPLOSION_DAMAGE)
                .add(ModDamageTypes.COMBUSTION_DAMAGE)
                .add(ModDamageTypes.OVERCHARGE_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_LIGHTNING_DAMAGE)
                .add(ModDamageTypes.LIGHTNING_DAMAGE)
                .add(ModDamageTypes.OVERCHARGE_DAMAGE)
                .add(ModDamageTypes.MAGNETIC_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_STONE_DAMAGE)
                .add(ModDamageTypes.STONE_DAMAGE)
                .add(ModDamageTypes.LAVA_DAMAGE)
                .add(ModDamageTypes.MAGNETIC_DAMAGE)
                .add(ModDamageTypes.EARTH_DAMAGE)
                .add(ModDamageTypes.METAL_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_DARK_DAMAGE)
                .add(ModDamageTypes.DARK_DAMAGE)
                .add(ModDamageTypes.ABYSS_DAMAGE)
                .add(ModDamageTypes.HAVOC_DAMAGE)
                .add(ModDamageTypes.DECAY_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_VOID_DAMAGE)
                .add(ModDamageTypes.VOID_DAMAGE)
                .add(ModDamageTypes.ABYSS_DAMAGE)
                .add(ModDamageTypes.HAVOC_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_WATER_DAMAGE)
                .add(ModDamageTypes.WATER_DAMAGE)
                .add(ModDamageTypes.ABYSS_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_SOUL_DAMAGE)
                .add(ModDamageTypes.SOUL_DAMAGE)
                .add(ModDamageTypes.DECAY_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_LIGHT_DAMAGE)
                .add(ModDamageTypes.LIGHT_DAMAGE)
                .add(ModDamageTypes.DIVINE_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_AMPLIFICATION_DAMAGE)
                .add(ModDamageTypes.AMPLIFICATION_DAMAGE)
                .add(ModDamageTypes.MAGNETIC_DAMAGE)
                .add(ModDamageTypes.METAL_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_NATURE_DAMAGE)
                .add(ModDamageTypes.NATURE_DAMAGE)
                .add(ModDamageTypes.EARTH_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_ICE_DAMAGE)
                .add(ModDamageTypes.ICE_DAMAGE);

        getOrCreateTagBuilder(ModDamageTypes.IS_AIR_DAMAGE)
                .add(ModDamageTypes.AIR_DAMAGE);
    }
}
