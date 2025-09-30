package ckingc05c.renovatio.combat;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ElementalDamage {
    // Base Elements
    WATER(true),
    ICE(true),
    FIRE(true),
    AIR(true),
    NATURE(true),
    STONE(true),
    LIGHT(true),
    DARK(true),
    LIGHTNING(true),
    SOUL(true),
    VOID(true),
    AMPLIFICATION(true),
    EXPLOSION(true),

    // Combo Elements
    COMBUSTION(false, EXPLOSION, FIRE),
    OVERCHARGE(false, EXPLOSION, LIGHTNING),
    LAVA(false, FIRE, STONE),
    ABYSS(false, DARK, VOID, WATER),
    HAVOC(false, VOID, DARK), // Magic is a category, not an element itself
    DECAY(false, DARK, SOUL),   // Magic is a category, not an element itself
    DIVINE(false, LIGHT),       // Magic is a category, not an element itself
    MAGNETIC(false, STONE, AMPLIFICATION, LIGHTNING),
    EARTH(false, STONE, NATURE),
    METAL(false, STONE, AMPLIFICATION);

    private final boolean isBase;
    private final List<ElementalDamage> components;

    ElementalDamage(boolean isBase, ElementalDamage... components) {
        this.isBase = isBase;
        if (components.length > 0) {
            this.components = Arrays.asList(components);
        } else {
            this.components = Collections.singletonList(this);
        }
    }

    public boolean isBaseElement() {
        return isBase;
    }

    public List<ElementalDamage> getBaseComponents() {
        return components;
    }
    /**
     * Gets the ElementalDamage enum constant corresponding to a given DamageType RegistryKey.
     * @param key The RegistryKey of the DamageType.
     * @return The matching ElementalDamage, or null if no match is found.
     */
    @Nullable
    public static ElementalDamage getFromRegistryKey(RegistryKey<DamageType> key) {
        if (key == null) return null;
        String keyName = key.getValue().getPath().replace("_damage", "").toUpperCase();
        try {
            return ElementalDamage.valueOf(keyName);
        } catch (IllegalArgumentException e) {
            return null; // No matching enum constant found
        }
    }
}