package ckingc05c.renovatio.datagen;

import ckingc05c.renovatio.combat.ModDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class ModDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public ModDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        // --- Existing Damage Types ---
        entries.add(ModDamageTypes.TOUGHNESS_BREAK, new DamageType("toughness_break", DamageScaling.NEVER, 0.1f));
        entries.add(ModDamageTypes.WEAKNESS_BREAK, new DamageType("weakness_break", DamageScaling.NEVER, 0.1f));

        // --- CORRECTED: Only register BASE elemental damage types ---
        entries.add(ModDamageTypes.WATER_DAMAGE, new DamageType("water_damage", 0.1f));
        entries.add(ModDamageTypes.ICE_DAMAGE, new DamageType("ice_damage", 0.1f));
        entries.add(ModDamageTypes.FIRE_DAMAGE, new DamageType("fire_damage", 0.1f));
        entries.add(ModDamageTypes.AIR_DAMAGE, new DamageType("air_damage", 0.1f));
        entries.add(ModDamageTypes.NATURE_DAMAGE, new DamageType("nature_damage", 0.1f));
        entries.add(ModDamageTypes.STONE_DAMAGE, new DamageType("stone_damage", 0.1f));
        entries.add(ModDamageTypes.LIGHT_DAMAGE, new DamageType("light_damage", 0.1f));
        entries.add(ModDamageTypes.DARK_DAMAGE, new DamageType("dark_damage", 0.1f));
        entries.add(ModDamageTypes.LIGHTNING_DAMAGE, new DamageType("lightning_damage", 0.1f));
        entries.add(ModDamageTypes.SOUL_DAMAGE, new DamageType("soul_damage", 0.1f));
        entries.add(ModDamageTypes.VOID_DAMAGE, new DamageType("void_damage", 0.1f));
        entries.add(ModDamageTypes.AMPLIFICATION_DAMAGE, new DamageType("amplification_damage", 0.1f));
        entries.add(ModDamageTypes.EXPLOSION_DAMAGE, new DamageType("explosion_damage", 0.1f));
    }

    @Override
    public String getName() {
        return "Renovatio Dynamic Registries";
    }
}