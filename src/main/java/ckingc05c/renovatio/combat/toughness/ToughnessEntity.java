package ckingc05c.renovatio.combat.toughness;

import ckingc05c.renovatio.Renovatio;
import ckingc05c.renovatio.attribute.ModAttributes;
import ckingc05c.renovatio.combat.ModDamageTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.UUID;

/**
 * A wrapper class that associates a {@link LivingEntity} with its current {@link ToughnessState}.
 * <p>
 * This makes it easier to manage and track toughness-related data for each entity,
 * including boss-specific toughness scaling, resistance interactions, and break effects.
 * </p>
 */
public class ToughnessEntity {
    /** The wrapped entity instance being tracked. */
    private final LivingEntity entity;

    /** The entity's current toughness state. */
    private ToughnessState toughnessState;

    /**
     * Constructs a new {@code ToughnessEntity} for the given entity.
     * <p>
     * Boss-type entities (e.g., Ender Dragon, Wither, Elder Guardian, Warden)
     * are automatically initialized with a {@link ToughnessState#BOSS_UNBROKEN} state,
     * while all other entities start as {@link ToughnessState#UNBROKEN}.
     * </p>
     *
     * @param entity The {@link LivingEntity} to wrap.
     */
    public ToughnessEntity(LivingEntity entity) {
        this.entity = entity;
        Renovatio.LOGGER.info("Created new TougnessEnitiy");
        // Determine initial toughness state based on entity type
        EntityType<?> type = entity.getType();
        if (type == EntityType.ENDER_DRAGON
                || type == EntityType.WITHER
                || type == EntityType.ELDER_GUARDIAN
                || type == EntityType.WARDEN) {
            this.toughnessState = ToughnessState.BOSS_UNBROKEN;
        } else {
            this.toughnessState = ToughnessState.UNBROKEN;
        }
    }

    public boolean isBoss(){
        EntityType<?> type = entity.getType();

        return (type == EntityType.ENDER_DRAGON
                || type == EntityType.WITHER
                || type == EntityType.ELDER_GUARDIAN
                || type == EntityType.WARDEN);
    }

    /** @return The wrapped {@link LivingEntity}. */
    public LivingEntity getEntity() {
        return entity;
    }

    /** @return The current {@link ToughnessState}. */
    public ToughnessState getToughnessState() {
        return toughnessState;
    }

    /**
     * Updates the toughness state of this entity.
     * <p>
     * Optional logic can be added to prevent invalid transitions,
     * e.g., from a boss state to a non-boss state.
     * </p>
     *
     * @param toughnessState The new {@link ToughnessState} to assign.
     */
    public void setToughnessState(ToughnessState toughnessState) {
        if (isBoss()){
            this.toughnessState = toughnessState.getBossState();
        } else {
            this.toughnessState = toughnessState;
        }
    }

    /** @return The unique identifier ({@link UUID}) of the entity. */
    public UUID getUuid(){
        return entity.getUuid();
    }

    /**
     * Gets the current value of a specified attribute on the entity.
     *
     * @param attribute The {@link EntityAttribute} to query.
     * @return The attribute value.
     */
    public double getAttributeValue(EntityAttribute attribute) {
        return entity.getAttributeValue(attribute);
    }

    /** @return The maximum toughness value of this entity. */
    public double getMaxToughness() {
        return entity.getAttributeValue(ModAttributes.GENERIC_MAX_TOUGHNESS);
    }

    /** @return The maximum health of this entity. */
    public float getMaxHealth(){
        return entity.getMaxHealth();
    }

    /** @return The {@link World} the entity is in. */
    public World getWorld(){
        return entity.getWorld();
    }

    /** @return The {@link DamageSources} instance for the entity. */
    public DamageSources getDamageSources(){
        return entity.getDamageSources();
    }

    /**
     * Determines the effective resistance level for this entity
     * against a given damage source.
     *
     * @param source The {@link DamageSource} being checked.
     * @return The effective resistance level (0 if none).
     */
    private int getEffectiveResistanceLevel(DamageSource source){
        StatusEffectInstance resistance = entity.getStatusEffect(StatusEffects.RESISTANCE);
        if (resistance != null && !source.isIn(DamageTypeTags.BYPASSES_RESISTANCE)) {
            return resistance.getAmplifier() + 1;
        } else {
            return 0;
        }
    }

    /**
     * Calculates the effective toughness multiplier applied to resistance scaling.
     *
     * @param source The {@link DamageSource} being considered.
     * @return A multiplier affecting toughness against this damage source.
     */
    public float getResistanceEffectiveToughnessModifier(DamageSource source){
        int effectLevel = getEffectiveResistanceLevel(source);
        float adjustedResistanceScaling = 2 - toughnessState.getResistanceScaling();
        return (float) Math.pow(adjustedResistanceScaling, effectLevel);
    }

    /**
     * Calculates the effective toughness multiplier applied to resistance scaling.
     *
     * @param source The {@link DamageSource} being considered.
     * @return A multiplier affecting toughness against this damage source.
     */
    public float getResistanceToughnessDamageReductionModifier(DamageSource source){
        int effectLevel = getEffectiveResistanceLevel(source);
        float adjustedResistanceScaling = toughnessState.getResistanceScaling();
        return (float) Math.pow(adjustedResistanceScaling, effectLevel);
    }

    /**
     * Calculates the damage reduction multiplier from resistance effects.
     * @param source The {@link DamageSource} being considered.
     * @return A multiplier reducing damage taken.
     */
    public float getResistanceDamageModifier(DamageSource source){
        int effectLevel = getEffectiveResistanceLevel(source);
        float adjustedResistanceScaling = 1 - toughnessState.getResistanceScaling();
        return (float) Math.pow(adjustedResistanceScaling, effectLevel);
    }

    /**
     * Calculates how much damage bypasses toughness and goes directly to health
     * due to the Resistance effect.
     *
     * @param source The {@link DamageSource} being considered.
     * @return The direct health damage value.
     */
    public float getDamageToHealthThrough(DamageSource source){
        int effectLevel = getEffectiveResistanceLevel(source);
        return Math.max(0,(effectLevel * toughnessState.getResistanceHealthDamageModifier()));
    }

    /**
     * Triggers special effects when an entity's toughness breaks.
     * <p>
     * Includes:
     * <ul>
     *   <li>Applying custom {@link ModDamageTypes#TOUGHNESS_BREAK} damage.</li>
     *   <li>Playing the {@link SoundEvents#ITEM_TOTEM_USE} sound.</li>
     *   <li>Pushing the entity away from its attacker.</li>
     * </ul>
     * </p>
     *
     * @param damage The amount of break damage to apply.
     * @param originalSource The original {@link DamageSource} that caused the break.
     */
    public void dealBreakEffects(float damage, DamageSource originalSource){
        DamageSource breakDamageSource = getDamageSources().create(ModDamageTypes.TOUGHNESS_BREAK, originalSource.getAttacker());
        entity.playSound(SoundEvents.ITEM_TOTEM_USE, 3 , 0.75f);
        if (originalSource.getAttacker() != null) {
            entity.pushAwayFrom(originalSource.getAttacker());
        }
        entity.damage(breakDamageSource, damage);
    }
}