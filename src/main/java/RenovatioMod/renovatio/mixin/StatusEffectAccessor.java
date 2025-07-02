package RenovatioMod.renovatio.mixin;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(StatusEffect.class)
public interface StatusEffectAccessor {
    // This provides a "getter" for the private attributeModifiers map.
    @Accessor("attributeModifiers")
    Map<EntityAttribute, EntityAttributeModifier> getAttributeModifiers();
}