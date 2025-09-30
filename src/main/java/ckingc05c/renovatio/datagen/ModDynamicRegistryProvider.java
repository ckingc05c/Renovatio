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

        // --- Register ALL Elemental Damage Types with the CORRECT constructor ---
        DamageScaling scaling = DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER;
        float exhaustion = 0.1f;

        // Base
        entries.add(ModDamageTypes.WATER_DAMAGE, new DamageType("water_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.ICE_DAMAGE, new DamageType("ice_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.FIRE_DAMAGE, new DamageType("fire_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.AIR_DAMAGE, new DamageType("air_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.NATURE_DAMAGE, new DamageType("nature_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.STONE_DAMAGE, new DamageType("stone_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.LIGHT_DAMAGE, new DamageType("light_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.DARK_DAMAGE, new DamageType("dark_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.LIGHTNING_DAMAGE, new DamageType("lightning_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.SOUL_DAMAGE, new DamageType("soul_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.VOID_DAMAGE, new DamageType("void_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.AMPLIFICATION_DAMAGE, new DamageType("amplification_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.EXPLOSION_DAMAGE, new DamageType("explosion_damage", scaling, exhaustion));

        // Combo
        entries.add(ModDamageTypes.COMBUSTION_DAMAGE, new DamageType("combustion_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.OVERCHARGE_DAMAGE, new DamageType("overcharge_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.LAVA_DAMAGE, new DamageType("lava_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.ABYSS_DAMAGE, new DamageType("abyss_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.HAVOC_DAMAGE, new DamageType("havoc_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.DECAY_DAMAGE, new DamageType("decay_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.DIVINE_DAMAGE, new DamageType("divine_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.MAGNETIC_DAMAGE, new DamageType("magnetic_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.EARTH_DAMAGE, new DamageType("earth_damage", scaling, exhaustion));
        entries.add(ModDamageTypes.METAL_DAMAGE, new DamageType("metal_damage", scaling, exhaustion));
    }

    @Override
    public String getName() {
        return "Renovatio Dynamic Registries";
    }
}