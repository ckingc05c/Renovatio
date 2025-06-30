package RenovatioMod.renovatio.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Item> RAW_ORE_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("renovatio", "raw_ore_blocks"));
}
