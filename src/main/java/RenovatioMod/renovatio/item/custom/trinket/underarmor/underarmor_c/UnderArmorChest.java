package RenovatioMod.renovatio.item.custom.trinket.underarmor.underarmor_c;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
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
        // +10% movement speed
        modifiers.put(AttributesMod.MELEE_RESISTANCE, new EntityAttributeModifier(uuid, "puffish_attributes:melee_resistance", 0.1, EntityAttributeModifier.Operation.ADDITION));
        // If the player has access to ring slots, this will give them an extra one
        SlotAttributes.addSlotModifier(modifiers, "chest/underarmor_c", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        return modifiers;
    }
}
