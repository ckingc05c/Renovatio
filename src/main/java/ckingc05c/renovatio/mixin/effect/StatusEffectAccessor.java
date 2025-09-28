package ckingc05c.renovatio.mixin.effect;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

/**
 * This mixin provides access to private fields in the StatusEffect class.
 */
@Mixin(StatusEffect.class)
public interface StatusEffectAccessor {
    /**
     * This provides a "getter" for the private attributeModifiers map.
     * @return The map of attribute modifiers.
     */
    @Accessor("attributeModifiers")
    Map<EntityAttribute, EntityAttributeModifier> getAttributeModifiers();
}