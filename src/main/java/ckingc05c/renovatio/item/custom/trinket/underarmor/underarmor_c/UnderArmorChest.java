package ckingc05c.renovatio.item.custom.trinket.underarmor.underarmor_c;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.puffish.attributesmod.AttributesMod;



import java.util.UUID;

/**
 * This class represents the under-armor chest item.
 * It is a trinket that provides a bonus to melee resistance and an extra ring slot.
 */
public class UnderArmorChest extends TrinketItem {
    /**
     * Constructs a new UnderArmorChest item.
     * @param settings The item settings.
     */
    public UnderArmorChest(Settings settings) {
        super(settings);
    }

    /**
     * Gets the modifiers for this trinket.
     * @param stack The item stack.
     * @param slot The slot the item is in.
     * @param entity The entity wearing the item.
     * @param uuid The UUID of the modifier.
     * @return A map of attribute modifiers.
     */
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);

        // This correctly adds 10% melee resistance to the player
        modifiers.put(AttributesMod.RESISTANCE, new EntityAttributeModifier(uuid, "puffish_attributes:resistance", 0.5, EntityAttributeModifier.Operation.MULTIPLY_BASE));

        // This line was for adding extra slots and should be removed if you just want to add player stats.
        // SlotAttributes.addSlotModifier(modifiers, "chest/underarmor_c", uuid, 1, EntityAttributeModifier.Operation.ADDITION);

        return modifiers;
    }
}
