package ckingc05c.renovatio.combat.toughness;

/**
 * Defines the different toughness states for an entity.
 * Each state has a damage modifier, a flag for recovery, and a broken status.
 */
public enum ToughnessState {
    FORTIFIED(0.75f, true, false, 0.625f, 1.5f, 0.10f),
    REINFORCED(0.8f, true, false, 0.75f, 1.25f, 0.075f),
    UNBROKEN(0.9f, true, false, 0.8f, 1.0f, 0.05f),
    BROKEN(1.0f, true, true, 0.875f, 0.0f, 0.0f),
    FRACTURED(1.25f, true, true, 0.9375f, 0.0f, 0.0f),
    SHATTERED(1.75f, false, true, 1.0f, 0.0f, 0.0f),

    // Boss variants with different modifiers
    BOSS_FORTIFIED(0.625f, true, false, 0.625f, 1.5f, 0.1f),
    BOSS_REINFORCED(0.7f, true, false, 0.75f, 1.25f, 0.075f),
    BOSS_UNBROKEN(0.85f, true, false, 0.8f, 1.0f, 0.05f),
    BOSS_BROKEN(1.0f, true, true, 0.875f, 0.0f, 0.0f),
    BOSS_FRACTURED(1.125f, true, true, 0.9375f, 0.0f, 0.0f),
    BOSS_SHATTERED(1.375f, true, true, 1.0f, 0.0f, 0.0f);

    private final float damageModifier;
    private final boolean canRecover;
    private final boolean isBroken;
    private final float resistanceScaling;
    private final float effectiveToughnessModifier;
    private final float resistanceHealthDamageModifier;

    ToughnessState(float damageModifier, boolean canRecover, boolean isBroken, float resistanceScaling, float effectiveToughnessModifier, float resistanceHealthDamageModifier) {
        this.damageModifier = damageModifier;
        this.canRecover = canRecover;
        this.isBroken = isBroken;
        this.resistanceScaling = resistanceScaling;
        this.effectiveToughnessModifier = effectiveToughnessModifier;
        this.resistanceHealthDamageModifier = resistanceHealthDamageModifier;
    }

    /**
     * Gets the multiplier applied to incoming damage.
     * @return The damage modifier.
     */
    public float getDamageModifier() {
        return damageModifier;
    }

    public float getResistanceScaling() {
        return resistanceScaling;
    }

    public float getEffectiveToughnessModifier() {
        return effectiveToughnessModifier;
    }

    public float getResistanceHealthDamageModifier() {
        return resistanceHealthDamageModifier;
    }

    /**
     * Checks if an entity can recover from this state naturally.
     * @return True if recovery is possible, false otherwise.
     */
    public boolean canRecover() {
        return canRecover;
    }

    /**
     * Checks if the entity's toughness is considered "broken" in this state.
     * @return True if the state is a broken state, false otherwise.
     */
    public boolean isBroken() {
        return isBroken;
    }

    /**
     * Checks if this state is a boss variant.
     * @return True if it's a boss state.
     */
    public boolean isBoss() {
        return this.name().startsWith("BOSS_");
    }

    /**
     * Returns the non-boss equivalent of this state.
     * If it's already a normal state, it just returns itself.
     * @return The base (non-bosas) toughness state.
     */
    public ToughnessState getBaseState() {
        if (!isBoss()) {
            return this;
        }
        // Strips "BOSS_" prefix to find the corresponding normal state
        String baseName = this.name().substring(5);
        return ToughnessState.valueOf(baseName);
    }

    /**
     * Returns the boss equivalent of this state.
     * If it's already a boss state, it just returns itself.
     * @return The boss variant of the toughness state.
     */
    public ToughnessState getBossState() {
        if (isBoss()) {
            return this;
        }
        return ToughnessState.valueOf("BOSS_" + this.name());
    }
    /**
     * Returns the string representation of the base toughness state with only the first letter capitalized.
     * For example, both FORTIFIED and BOSS_FORTIFIED will be represented as "Fortified".
     * @return The name of the base state in title case.
     */
    @Override
    public String toString() {
        // Get the base state's name, e.g., "FORTIFIED"
        String baseName = this.getBaseState().name();

        // Return an empty string if the name is invalid
        if (baseName == null || baseName.isEmpty()) {
            return "";
        }

        // Convert the entire string to lower case, e.g., "fortified"
        String lowerCaseName = baseName.toLowerCase();

        // Capitalize the first letter and append the rest of the string
        return Character.toUpperCase(lowerCaseName.charAt(0)) + lowerCaseName.substring(1);
    }
}
