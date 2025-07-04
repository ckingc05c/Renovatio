package RenovatioMod.renovatio.event;

import RenovatioMod.renovatio.mixin.StatusEffectAccessor;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.fabric_extras.ranged_weapon.api.EntityAttributes_RangedWeapon;

import java.util.Map;
import java.util.UUID;

public class AttributeEffectHandler {

    private static final UUID STRENGTH_UUID = UUID.fromString("648D7064-6A60-4F59-8ABE-C2C23A6DD7A9");
    private static final UUID WEAKNESS_UUID = UUID.fromString("22653B89-116E-49DC-9B6B-9971489B5BE5");

    public static void initialize() {
        // --- Modify Strength ---
        StatusEffect strength = StatusEffects.STRENGTH;
        Map<EntityAttribute, EntityAttributeModifier> strengthModifiers = ((StatusEffectAccessor) strength).getAttributeModifiers();
        strengthModifiers.clear();
        strength.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, STRENGTH_UUID.toString(), 0.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        strength.addAttributeModifier(EntityAttributes_RangedWeapon.DAMAGE.attribute, STRENGTH_UUID.toString(), 0.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

        // --- Modify Weakness ---
        StatusEffect weakness = StatusEffects.WEAKNESS;
        Map<EntityAttribute, EntityAttributeModifier> weaknessModifiers = ((StatusEffectAccessor) weakness).getAttributeModifiers();
        weaknessModifiers.clear();
        weakness.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, WEAKNESS_UUID.toString(), 0.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        weakness.addAttributeModifier(EntityAttributes_RangedWeapon.DAMAGE.attribute, WEAKNESS_UUID.toString(), 0.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}