package ckingc05c.renovatio.datagen;

import ckingc05c.renovatio.Renovatio;
import ckingc05c.renovatio.combat.ModDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public ModDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        // Here we add all of our custom damage types to the dynamic registry
        entries.add(ModDamageTypes.TOUGHNESS_BREAK, new DamageType("toughness_break", DamageScaling.NEVER, 0.1f));
        entries.add(ModDamageTypes.WEAKNESS_BREAK, new DamageType("weakness_break", DamageScaling.NEVER, 0.1f));
    }

    @Override
    public String getName() {
        return "Renovatio Dynamic Registries";
    }
}
