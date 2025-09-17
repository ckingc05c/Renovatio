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

public class UnderArmorChest extends TrinketItem {
    public UnderArmorChest(Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        // +10% movement speed
        modifiers.put(AttributesMod.MELEE_RESISTANCE, new EntityAttributeModifier(uuid, "puffish_attributes:melee_resistance", 0.1, EntityAttributeModifier.Operation.ADDITION));
        // If the player has access to ring slots, this will give them an extra one
        SlotAttributes.addSlotModifier(modifiers, "chest/underarmor_c", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        return modifiers;
    }
}
