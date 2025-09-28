package ckingc05c.renovatio.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * This class contains all the custom tags used by the mod.
 */
public class ModTags {
    /**
     * A tag for raw ore blocks.
     */
    public static final TagKey<Item> RAW_ORE_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("renovatio", "raw_ore_blocks"));
}
