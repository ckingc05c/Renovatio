package ckingc05c.renovatio.effect.status;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class DamageBoostEffect extends StatusEffect {

    /**
     * Constructor for the Damage Boost status effect.
     */
    public DamageBoostEffect() {
        // 1. Call the parent constructor from StatusEffect.
        //    - StatusEffectCategory.BENEFICIAL makes it show up as a "good" effect.
        //    - 0xFF5555 is the color of the effect in the UI (a reddish color).
        super(StatusEffectCategory.BENEFICIAL, 0xFF5555);

        // 2. Register the attribute modifier.
        //    Minecraft automatically handles adding/removing this when the effect is applied/expires.
        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_DAMAGE, // The attribute we want to modify.
                "22653B89-116E-42DC-95AB-83ABEAF0E24A", // A unique UUID for this specific modifier. You can generate your own online.
                0.0, // The base value. We set this to 0 because adjustModifierAmount will calculate the real value.
                EntityAttributeModifier.Operation.ADDITION // The operation. ADDITION provides a flat bonus.
        );
    }

    /**
     * This method calculates the actual strength of the attribute modifier based on the effect's amplifier.
     * It overrides the base value set in the constructor.
     * @param amplifier The level of the effect (0 for level I, 1 for level II, etc.).
     * @param modifier The modifier being adjusted.
     * @return The calculated damage bonus.
     */
    @Override
    public double adjustModifierAmount(int amplifier, EntityAttributeModifier modifier) {
        // Your desired logic: Base of 4 damage, plus 2 for each additional level.
        // - Level 1 (amplifier 0): 4.0 + (0 * 2.0) = 4.0 damage
        // - Level 2 (amplifier 1): 4.0 + (1 * 2.0) = 6.0 damage
        // - Level 3 (amplifier 2): 4.0 + (2 * 2.0) = 8.0 damage
        return 4.0 + (amplifier * 2.0);
    }
}