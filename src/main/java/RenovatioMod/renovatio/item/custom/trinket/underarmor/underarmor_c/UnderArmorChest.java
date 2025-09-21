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

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);

        // This correctly adds 10% melee resistance to the player
        modifiers.put(AttributesMod.RESISTANCE, new EntityAttributeModifier(uuid, "puffish_attributes:resistance", 0.5, EntityAttributeModifier.Operation.MULTIPLY_BASE));

        // This line was for adding extra slots and should be removed if you just want to add player stats.
        // SlotAttributes.addSlotModifier(modifiers, "chest/underarmor_c", uuid, 1, EntityAttributeModifier.Operation.ADDITION);

        return modifiers;
    }
}
