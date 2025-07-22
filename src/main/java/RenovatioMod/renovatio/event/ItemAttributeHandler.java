package RenovatioMod.renovatio.event;

import RenovatioMod.renovatio.mixin.item.ItemAccessor;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;

public class ItemAttributeHandler {

    public static void register() {
        ModifyItemAttributeModifiersCallback.EVENT.register(ItemAttributeHandler::modifyAttributes);
    }

    private static void modifyAttributes(ItemStack stack, EquipmentSlot slot, Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers) {
        // We only care about the main hand
        if (slot != EquipmentSlot.MAINHAND) {
            return;
        }

        if (stack.getItem().getClass() == AxeItem.class ) {
            // Remove the default attributes
            attributeModifiers.removeAll(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            attributeModifiers.removeAll(EntityAttributes.GENERIC_ATTACK_SPEED);

            // Add our new, custom attributes
            // New Damage: +5 over material's base
            float damage = ((ToolItem) stack.getItem()).getMaterial().getAttackDamage() + 5.0f;
            attributeModifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(ItemAccessor.getAttackDamageModifierId(), "Weapon modifier", damage, EntityAttributeModifier.Operation.ADDITION));
            // New Speed: 1.0
            attributeModifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED,
                    new EntityAttributeModifier(ItemAccessor.getAttackSpeedModifierId(), "Weapon modifier", -3.0, EntityAttributeModifier.Operation.ADDITION));

        } else if (stack.getItem().getClass() == HoeItem.class) {
            // Remove the default attributes
            attributeModifiers.removeAll(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            attributeModifiers.removeAll(EntityAttributes.GENERIC_ATTACK_SPEED);

            // Add our new, custom attributes
            // New Damage: +0 over material's base
            float damage = ((ToolItem) stack.getItem()).getMaterial().getAttackDamage() + 0.0f;
            attributeModifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(ItemAccessor.getAttackDamageModifierId(), "Weapon modifier", damage, EntityAttributeModifier.Operation.ADDITION));
            // New Speed: 2.25
            attributeModifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED,
                    new EntityAttributeModifier(ItemAccessor.getAttackSpeedModifierId(), "Weapon modifier", -1.75, EntityAttributeModifier.Operation.ADDITION));
        }
    }
}