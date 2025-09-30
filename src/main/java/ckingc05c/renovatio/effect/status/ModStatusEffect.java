package ckingc05c.renovatio.effect.status;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static ckingc05c.renovatio.Renovatio.MOD_ID;

public class ModStatusEffect {
    public static final StatusEffect DAMAGE_BOOST_EFFECT = new DamageBoostEffect();
    public static final StatusEffect FORTIFICATION_EFFECT = new FortificationEffect(); // Added

    public static void registerStatusEffects() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID,"damage_boost"), DAMAGE_BOOST_EFFECT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "fortification"), FORTIFICATION_EFFECT); // Added
    }
}
