package ckingc05c.renovatio.item.custom.equipment.weapon.ranged;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

/**
 * Defines the statistics for various ranged weapons.
 * The enum holds values for tier, draw speed, damage, velocity, and durability modifier.
 */
public enum RangedWeaponStats {

    // Tier I Bows
    // Projectile Count: 1
    SHORTBOW(1, 13, 4, 2.5f, 256, RangedWeaponType.BOW, ArrowPattern.SINGLE),
    // Projectile Count: 1
    BOW(1, 20, 6, 3.0f, 384, RangedWeaponType.BOW, ArrowPattern.SINGLE),
    // Projectile Count: 1
    LONGBOW(1, 30, 9, 3.5f, 512, RangedWeaponType.BOW, ArrowPattern.SINGLE),

    // Tier II Bows
    // Projectile Count: 1
    GREATBOW(2, 40, 16, 4.0f, 640, RangedWeaponType.BOW, ArrowPattern.SINGLE),
    // Projectile Count: 2
    TWINBOW(2, 30, 6, 2.75f, 480, RangedWeaponType.BOW, ArrowPattern.DOUBLE_VERTICAL),

    // Tier III Bows
    // Projectile Count: 1
    COMPOUND_BOW(3, 20, 10, 3.5f, 576, RangedWeaponType.BOW, ArrowPattern.SINGLE),

    // Tier I Crossbows
    // Projectile Count: 1
    PISTOL_CROSSBOW(1, 17, 6, 2.75f, 310, RangedWeaponType.CROSSBOW, ArrowPattern.SINGLE),
    // Projectile Count: 1
    CROSSBOW(1, 25, 9, 3.15f, 465, RangedWeaponType.CROSSBOW, ArrowPattern.SINGLE),
    // Projectile Count: 1
    HEAVY_CROSSBOW(1, 33, 12, 3.75f, 620, RangedWeaponType.CROSSBOW, ArrowPattern.SINGLE),

    // Tier II Crossbows
    // Projectile Count: 1
    ARABLEST(2, 40, 18, 4.25f, 775, RangedWeaponType.CROSSBOW, ArrowPattern.SINGLE),
    // Projectile Count: 3
    SCATTER_CROSSBOW(2, 33, 5, 2.75f, 582, RangedWeaponType.CROSSBOW, ArrowPattern.TRIANGLE_TRIPLE),

    // Tier III Crossbows
    // Projectile Count: 3
    BURST_CROSSBOW(3, 42, 9, 3.5f, 678, RangedWeaponType.CROSSBOW, ArrowPattern.SINGLE);


    private final int tier;
    private final int drawspeed; // In ticks
    private final int damage;
    private final float velocity;
    private final int durability;
    private final RangedWeaponType type;
    private final ArrowPattern pattern;


    private static final ToolMaterials BASE_MATERIAL = ToolMaterials.IRON;

    /**
     * Constructor for the ranged weapon stats.
     *
     * @param tier               The tier of the weapon (I, II, III).
     * @param drawspeed          The time it takes to fully draw the weapon, in ticks.
     * @param damage             The base damage dealt by the weapon's projectile.
     * @param velocity           The multiplier for the projectile's speed.
     * @param durability         The weapon's durability.
     * @param type               The Type of Ranged Weapon
     * @param pattern            The Arrows Pattern when weapon is fired
     */
    RangedWeaponStats(int tier, int drawspeed, int damage, float velocity, int durability, RangedWeaponType type, ArrowPattern pattern) {
        this.tier = tier;
        this.drawspeed = drawspeed;
        this.damage = damage;
        this.velocity = velocity;
        this.durability = durability;
        this.type = type;
        this.pattern = pattern;
    }

    /**
     * @return The tier of the weapon.
     */
    public int getTier() {
        return tier;
    }

    /**
     * @return The draw speed in ticks.
     */
    public int getDrawspeed() {
        return drawspeed;
    }

    /**
     * @return The base damage of the weapon.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return The projectile velocity multiplier.
     */
    public float getVelocity() {
        return velocity;
    }

    /**
     * @return The durability modifier for the weapon.
     */
    public int getDurability() {
        return durability;
    }
    /**
     * @return The type of ranged weapon.
     */
    public RangedWeaponType getType() {
        return type;
    }
    /**
     * @return The arrow pattern for the weapon.
     */
    public ArrowPattern getPattern() {
        return pattern;
    }
    /**
     * Calculates the final durability of a ranged weapon based on the provided tool material.
     * The durability is scaled relative to a base material (usually Iron) and the weapon type's base durability.
     *
     * @param material the ToolMaterial used to craft the weapon
     * @return the final durability value, scaled and rounded
     */
    public int getFinalDurability(ToolMaterial material) {
        // Normalize the material's durability as a ratio of the base material (Iron)
        double normalized = (double) material.getDurability() / BASE_MATERIAL.getDurability();

        // Multiply that ratio by the bow type's base durability and round to the nearest integer
        return (int) Math.round(normalized * getDurability());
    }

    /**
     * Calculates the final damage of a ranged weapon based on the provided tool material.
     * The damage is scaled relative to the attack damage of swords made from the same material.
     *
     * Since the material's attack damage value represents the "bonus" (e.g. Iron = 2),
     * we add 4 to match the way Minecraft calculates full sword damage (2 from material + 3 from the sword + 1 from base ).
     * This allows bows to scale similarly to swords in damage progression.
     *
     * @param material the ToolMaterial used to craft the weapon
     * @return the final scaled and rounded damage value
     */
    public float getDamage(ToolMaterial material) {
        // Add 4 to both to match full sword damage (e.g., Iron = 2 + 4 = 6)
        float ratio = (material.getAttackDamage() + 4) / (BASE_MATERIAL.getAttackDamage() + 4);

        // Scale the bow's base damage by this ratio
        float scaled = ratio * getDamage();

        // Round the final damage to the nearest 0.25 (¼) and return
        return Math.round(scaled * 4.0f) / 4.0f;
    }





}
