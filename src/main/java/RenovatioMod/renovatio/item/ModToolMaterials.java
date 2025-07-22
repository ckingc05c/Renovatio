package RenovatioMod.renovatio.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

import static RenovatioMod.renovatio.item.ModMaterialItems.*;

public enum ModToolMaterials implements ToolMaterial {
    // Values updated to match the table
    BLACKSTONE(131, 4.5F, 1.5F, 1, 10, () -> Ingredient.ofItems(Items.BLACKSTONE)),
    DEEPSLATE(192, 4.5F, 1.5F, 1, 12, () -> Ingredient.ofItems(Items.COBBLED_DEEPSLATE)),
    HELLSLATE(192, 4.5F, 1.5F, 1, 15, () -> Ingredient.ofItems(Items.NETHERRACK)),
    COPPER(212, 5.0F, 1.5F, 1, 12, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    ROSE_GOLD(324, 8.0F, 2.0F, 2, 20, () -> Ingredient.ofItems(ROSE_GOLD_INGOT)),
    REFINED_IRON(768, 6.5F, 2.5F, 2, 18, () -> Ingredient.ofItems(REFINED_IRON_INGOT)),
    NETHER_ROSE(512, 9.0F, 2.5F, 2, 18, () -> Ingredient.ofItems(NETHER_ROSE_INGOT)),
    BRONZE(1152, 7.0F, 3.0F, 2, 15, () -> Ingredient.ofItems(BRONZE_INGOT, BRONZE_NUGGET)),
    STEEL(1536, 8.0F, 3.5F, 3, 16, () -> Ingredient.ofItems(STEEL_INGOT, STEEL_NUGGET)),
    ROSE_STEEL(1872, 10.0F, 4.0F, 3, 20, () -> Ingredient.ofItems(ROSE_STEEL_INGOT)),
    OBSIDIAN_STEEL(3072, 12.0F, 6.0F, 4, 18, () -> Ingredient.ofItems(OBSIDIAN_STEEL_INGOT)),
    ENDER_STEEL(3440, 14.0F, 7.0F, 4, 20, () -> Ingredient.ofItems(ENDER_STEEL_INGOT)),
    VOID_CRYSTAL(3840, 16.0F, 9.0F, 5, 22, () -> Ingredient.ofItems(VOID_CRYSTAL_GEM)),
    ELEMENTAL_VOID_CRYSTAL(4096, 18.0F, 11.0F, 6, 25, () -> Ingredient.ofItems(ELEMENTAL_VOID_CRYSTAL_GEM)),
    PRIMORDIAL_VOID_CRYSTAL(5120, 20.0F, 14.0F, 7, 30, () -> Ingredient.ofItems(PRIMORDIAL_VOID_CRYSTAL_GEM));

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