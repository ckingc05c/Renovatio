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

    // --- Helper Methods ---
    private static RegistryKey<DamageType> createKey(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Renovatio.MOD_ID, name));
    }

    private static TagKey<DamageType> createTag(String name) {
        return TagKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Renovatio.MOD_ID, name));
    }

    // --- Existing Damage Types ---
    public static final RegistryKey<DamageType> TOUGHNESS_BREAK = createKey("toughness_break");
    public static final RegistryKey<DamageType> WEAKNESS_BREAK = createKey("weakness_break");
    public static final TagKey<DamageType> IS_BREAK_DAMAGE = createTag("is_break_damage");

    // --- Base Elemental Damage Types ---
    public static final RegistryKey<DamageType> WATER_DAMAGE = createKey("water_damage");
    public static final RegistryKey<DamageType> ICE_DAMAGE = createKey("ice_damage");
    public static final RegistryKey<DamageType> FIRE_DAMAGE = createKey("fire_damage");
    public static final RegistryKey<DamageType> AIR_DAMAGE = createKey("air_damage");
    public static final RegistryKey<DamageType> NATURE_DAMAGE = createKey("nature_damage");
    public static final RegistryKey<DamageType> STONE_DAMAGE = createKey("stone_damage");
    public static final RegistryKey<DamageType> LIGHT_DAMAGE = createKey("light_damage");
    public static final RegistryKey<DamageType> DARK_DAMAGE = createKey("dark_damage");
    public static final RegistryKey<DamageType> LIGHTNING_DAMAGE = createKey("lightning_damage");
    public static final RegistryKey<DamageType> SOUL_DAMAGE = createKey("soul_damage");
    public static final RegistryKey<DamageType> VOID_DAMAGE = createKey("void_damage");
    public static final RegistryKey<DamageType> AMPLIFICATION_DAMAGE = createKey("amplification_damage");
    public static final RegistryKey<DamageType> EXPLOSION_DAMAGE = createKey("explosion_damage");

    // --- Combo Elemental Damage Types ---
    public static final RegistryKey<DamageType> COMBUSTION_DAMAGE = createKey("combustion_damage");
    public static final RegistryKey<DamageType> OVERCHARGE_DAMAGE = createKey("overcharge_damage");
    public static final RegistryKey<DamageType> LAVA_DAMAGE = createKey("lava_damage");
    public static final RegistryKey<DamageType> ABYSS_DAMAGE = createKey("abyss_damage");
    public static final RegistryKey<DamageType> HAVOC_DAMAGE = createKey("havoc_damage");
    public static final RegistryKey<DamageType> DECAY_DAMAGE = createKey("decay_damage");
    public static final RegistryKey<DamageType> DIVINE_DAMAGE = createKey("divine_damage");
    public static final RegistryKey<DamageType> MAGNETIC_DAMAGE = createKey("magnetic_damage");
    public static final RegistryKey<DamageType> EARTH_DAMAGE = createKey("earth_damage");
    public static final RegistryKey<DamageType> METAL_DAMAGE = createKey("metal_damage");

    // --- Elemental Tags ---
    public static final TagKey<DamageType> IS_WATER_DAMAGE = createTag("is_water_damage");
    public static final TagKey<DamageType> IS_ICE_DAMAGE = createTag("is_ice_damage");
    public static final TagKey<DamageType> IS_FIRE_DAMAGE = createTag("is_fire_damage");
    public static final TagKey<DamageType> IS_AIR_DAMAGE = createTag("is_air_damage");
    public static final TagKey<DamageType> IS_NATURE_DAMAGE = createTag("is_nature_damage");
    public static final TagKey<DamageType> IS_STONE_DAMAGE = createTag("is_stone_damage");
    public static final TagKey<DamageType> IS_LIGHT_DAMAGE = createTag("is_light_damage");
    public static final TagKey<DamageType> IS_DARK_DAMAGE = createTag("is_dark_damage");
    public static final TagKey<DamageType> IS_LIGHTNING_DAMAGE = createTag("is_lightning_damage");
    public static final TagKey<DamageType> IS_SOUL_DAMAGE = createTag("is_soul_damage");
    public static final TagKey<DamageType> IS_VOID_DAMAGE = createTag("is_void_damage");
    public static final TagKey<DamageType> IS_AMPLIFICATION_DAMAGE = createTag("is_amplification_damage");
    public static final TagKey<DamageType> IS_EXPLOSION_DAMAGE = createTag("is_explosion_damage");


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