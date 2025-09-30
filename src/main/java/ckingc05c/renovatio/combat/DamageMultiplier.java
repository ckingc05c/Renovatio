package ckingc05c.renovatio.combat;

public enum DamageMultiplier {
    IMMUNITY(0.0f),
    MAJOR_RESISTANCE(0.5f),
    MINOR_RESISTANCE(0.75f),
    NEUTRAL(1.0f),
    MINOR_WEAKNESS(1.5f),
    MAJOR_WEAKNESS(2.0f),
    SEVERE_WEAKNESS(3.0f);

    private final float multiplier;

    DamageMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    public float getMultiplier() {
        return multiplier;
    }
}