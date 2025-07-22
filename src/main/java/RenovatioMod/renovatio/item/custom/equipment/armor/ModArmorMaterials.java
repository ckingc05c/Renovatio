package RenovatioMod.renovatio.item.custom.equipment.armor;

import RenovatioMod.renovatio.item.ModItems; // Make sure you have this import for your custom items
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import static RenovatioMod.renovatio.Renovatio.MOD_ID;
import static RenovatioMod.renovatio.item.ModMaterialItems.*;
import java.util.function.Supplier;



public enum ModArmorMaterials implements ArmorMaterial {
    // Note: Protection values are rounded and ordered as {Boots, Leggings, Chestplate, Helmet}
    REINFORCED_LEATHER("reinforced_leather", 240, new int[]{1, 2, 3, 1}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5f, 0.0f, () -> Ingredient.ofItems(Items.LEATHER)),

    COPPER("copper", 212, new int[]{2, 5, 6, 3}, 12,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f, () -> Ingredient.ofItems(Items.COPPER_INGOT)),

    ROSE_GOLD("rose_gold", 324, new int[]{2, 5, 6, 3}, 20,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.5f, 0.025f, () -> Ingredient.ofItems(ROSE_GOLD_INGOT)),

    REFINED_IRON("refined_iron", 768, new int[]{2, 5, 6, 3}, 16,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f, 0.0f, () -> Ingredient.ofItems(REFINED_IRON_INGOT)),

    HARDENED_CHAIN("hardened_chain", 384, new int[]{2, 5, 6, 3}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.5f, 0.0f, () -> Ingredient.ofItems(Items.IRON_INGOT)),

    NETHER_ROSE("nether_rose", 512, new int[]{3, 6, 7, 4}, 18,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.5f, 0.05f, () -> Ingredient.ofItems(NETHER_ROSE_INGOT)),

    BRONZE("bronze", 1152, new int[]{3, 6, 7, 4}, 15,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.5f, 0.0f, () -> Ingredient.ofItems(BRONZE_INGOT)),

    STEEL("steel", 1536, new int[]{3, 6, 7, 4}, 16,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0f, 0.05f, () -> Ingredient.ofItems(STEEL_INGOT)),

    ROSE_STEEL("rose_steel", 1872, new int[]{4, 7, 8, 5}, 20,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.5f, 0.075f, () -> Ingredient.ofItems(ROSE_STEEL_INGOT)),

    OBSIDIAN_STEEL("obsidian_steel", 3072, new int[]{5, 8, 9, 6}, 18,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0f, 0.125f, () -> Ingredient.ofItems(OBSIDIAN_STEEL_INGOT)),

    ENDER_STEEL("ender_steel", 3440, new int[]{5, 8, 9, 6}, 20,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 6.0f, 0.15f, () -> Ingredient.ofItems(ENDER_STEEL_INGOT)),

    VOID_CRYSTAL("void_crystal", 3840, new int[]{6, 10, 12, 8}, 22,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 6.0f, 0.175f, () -> Ingredient.ofItems(VOID_CRYSTAL_GEM)),

    ABYSSAL_VOID_CRYSTAL("abyssal_void_crystal", 4096, new int[]{7, 11, 13, 9}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 8.0f, 0.20f, () -> Ingredient.ofItems(ELEMENTAL_VOID_CRYSTAL_GEM)),

    UMBRAL_VOID_CRYSTAL("umbral_void_crystal", 4096, new int[]{7, 11, 13, 9}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 8.0f, 0.20f, () -> Ingredient.ofItems(ELEMENTAL_VOID_CRYSTAL_GEM)),

    INFERNAL_VOID_CRYSTAL("infernal_void_crystal", 4096, new int[]{7, 11, 13, 9}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 8.0f, 0.20f, () -> Ingredient.ofItems(ELEMENTAL_VOID_CRYSTAL_GEM)),

    ETHEREAL_VOID_CRYSTAL("ethereal_void_crystal", 4096, new int[]{7, 11, 13, 9}, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 8.0f, 0.20f, () -> Ingredient.ofItems(ELEMENTAL_VOID_CRYSTAL_GEM)),

    PRIMORDIAL_VOID_CRYSTAL("primordial_void_crystal", 5120, new int[]{9, 13, 15, 11}, 30,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10.0f, 0.225f, () -> Ingredient.ofItems(PRIMORDIAL_VOID_CRYSTAL_GEM));

    private final String name;
    private final int toolDurability;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    /**
     * Base values derived from ingot cost (ingots * 5), ordered by ArmorItem.Type's ordinal:
     * Boots (20), Leggings (35), Chestplate (40), Helmet (25).
     */
    private static final int[] PIECE_BASE_VALUES = new int[]{20, 35, 40, 25};

    ModArmorMaterials(String name, int toolDurability, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.toolDurability = toolDurability;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        // Your durability formula using the PIECE_BASE_VALUES array above
        double durabilityPortion = (double) this.toolDurability / 120.0; // The sum of PIECE_BASE_VALUES is 120
        int pieceBaseValue = PIECE_BASE_VALUES[type.ordinal()];
        double finalDurability = durabilityPortion * pieceBaseValue;
        return (int) Math.round(finalDurability);
    }


    @Override
    public int getProtection(ArmorItem.Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}