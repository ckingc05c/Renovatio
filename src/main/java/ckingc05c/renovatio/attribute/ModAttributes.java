package ckingc05c.renovatio.attribute;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ckingc05c.renovatio.Renovatio;

public class ModAttributes {
    public static final EntityAttribute GENERIC_MAX_TOUGHNESS = new ClampedEntityAttribute(
            "attribute.name.generic.max_toughness",
            20.0, // Default value
            0.0,   // Min value
            1024.0 // Max value
    ).setTracked(true);

    public static void registerAttributes() {
        // We only need to register the attribute itself now.
        // The mixin will handle adding it to entities.
        Registry.register(Registries.ATTRIBUTE, new Identifier(Renovatio.MOD_ID, "generic.max_toughness"), GENERIC_MAX_TOUGHNESS);
    }
}