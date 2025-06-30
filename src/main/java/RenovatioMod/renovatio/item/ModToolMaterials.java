package RenovatioMod.renovatio.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import RenovatioMod.renovatio.item.ModItems;


import java.util.function.Supplier;

import static RenovatioMod.renovatio.item.ModItems.*;

public enum ModToolMaterials implements ToolMaterial {
    BLACKSTONE(160, 4.5F, 1.5F, 1, 10, () -> Ingredient.ofItems(Items.BLACKSTONE)),
    DEEPSLATE(192, 4.5F, 1.5F, 1, 12, () -> Ingredient.ofItems(Items.COBBLED_DEEPSLATE)),
    HELLSLATE(192, 4.5F, 1.5F, 1, 15, () -> Ingredient.ofItems(Items.NETHERRACK)), // Replace with real item if needed
    COPPER(184, 5.0F, 1.5F, 1, 12, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    ROSE_GOLD(324, 7.5F, 2.0F, 2, 20, () -> Ingredient.ofItems(ROSE_GOLD_INGOT)), // Replace with rose gold item
    REFINED_IRON(675, 6.5F, 2.5F, 2, 18, () -> Ingredient.ofItems(REFINED_IRON_INGOT)), // Replace if custom
    NETHER_ROSE(512, 8.5F, 2.5F, 2, 18, () -> Ingredient.ofItems(NETHER_ROSE_INGOT)), // Replace with real item
    BRONZE(1248, 7.0F, 3.0F, 2, 15, () -> Ingredient.ofItems(BRONZE_INGOT)), // Replace with bronze ingot
    STEEL(1624, 7.5F, 3.5F, 3, 16, () -> Ingredient.ofItems(STEEL_INGOT)), // Replace with steel ingot
    OBSIDIAN_STEEL(3072, 10.0F, 6.0F, 5, 18, () -> Ingredient.ofItems(OBSIDIAN_STEEL_INGOT)), // Replace with custom ingot
    VOID_CRYSTAL(3840, 11.0F, 8.0F, 6, 20, () -> Ingredient.ofItems(VOID_CRYSTAL_GEM)); // Replace with void crystal item

    private final int durability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int miningLevel;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(int durability, float miningSpeed, float attackDamage, int miningLevel, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.miningLevel = miningLevel;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}