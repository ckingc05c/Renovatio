package ckingc05c.renovatio.combat;

import ckingc05c.renovatio.Renovatio;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModDamageTypes {
    // --- Registry Keys for Custom Damage Types ---
    public static final RegistryKey<DamageType> TOUGHNESS_BREAK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Renovatio.MOD_ID, "toughness_break"));
    public static final RegistryKey<DamageType> WEAKNESS_BREAK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Renovatio.MOD_ID, "weakness_break"));

    // --- Tag Key for Grouping Break Damage ---
    public static final TagKey<DamageType> IS_BREAK_DAMAGE = TagKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Renovatio.MOD_ID, "is_break_damage"));

    /**
     * Creates a DamageSource for one of our custom types.
     * This is a helper method to simplify applying damage elsewhere in the code.
     *
     * @param world The world where the damage is occurring.
     * @param key   The RegistryKey of the custom damage type.
     * @return A new DamageSource instance.
     */
    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

    /**
     * Creates a DamageSource for one of our custom types, attributed to a specific attacker.
     *
     * @param world    The world where the damage is occurring.
     * @param key      The RegistryKey of the custom damage type.
     * @param attacker The entity that caused the damage. Can be null.
     * @return A new DamageSource instance with an attacker.
     */
    public static DamageSource of(World world, RegistryKey<DamageType> key, @Nullable Entity attacker) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key), attacker);
    }
}
