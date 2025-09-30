package ckingc05c.renovatio.combat;

import ckingc05c.renovatio.combat.toughness.ToughnessEntity;
import java.util.List;
import java.util.Map;

public class ElementalDamageHelper {

    /**
     * Calculates the elemental damage multiplier based on the entity's weaknesses and resistances.
     *
     * @param targetEntity The ToughnessEntity being attacked.
     * @param damageType   The ElementalDamage type of the incoming attack.
     * @return The final damage multiplier.
     */
    public static float getDamageMultiplier(ToughnessEntity targetEntity, ElementalDamage damageType) {
        List<ElementalDamage> baseComponents = damageType.getBaseComponents();
        Map<ElementalDamage, DamageMultiplier> weaknesses = targetEntity.getWeaknesses();

        int weakCount = 0; // x in the formula
        int totalComponents = baseComponents.size(); // z in the formula
        float resistanceMultiplier = 1.0f;

        for (ElementalDamage component : baseComponents) {
            DamageMultiplier multiplier = weaknesses.getOrDefault(component, DamageMultiplier.NEUTRAL);

            if (multiplier == DamageMultiplier.IMMUNITY) {
                return DamageMultiplier.IMMUNITY.getMultiplier(); // Full immunity overrides everything
            }

            if (multiplier.getMultiplier() < 1.0f) {
                // If there are multiple resistances, find the strongest one (lowest multiplier)
                if (multiplier.getMultiplier() < resistanceMultiplier) {
                    resistanceMultiplier = multiplier.getMultiplier();
                }
            } else if (multiplier.getMultiplier() > 1.0f) {
                weakCount++;
            }
        }

        // If any resistance was found, it takes precedence over weaknesses.
        if (resistanceMultiplier < 1.0f) {
            return resistanceMultiplier;
        }

        // If there are weaknesses, apply the scaling formula.
        if (weakCount > 0) {
            // Damage Scaling = (x / (x + 1)) * z
            float scaling = ((float) weakCount / (weakCount + 1)) * totalComponents;
            return Math.max(1.0f, scaling); // Ensure it's at least neutral damage
        }

        // If no weaknesses or resistances are found, return neutral damage.
        return DamageMultiplier.NEUTRAL.getMultiplier();
    }
}