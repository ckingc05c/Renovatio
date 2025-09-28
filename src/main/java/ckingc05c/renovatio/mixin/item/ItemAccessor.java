package ckingc05c.renovatio.mixin.item;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.util.UUID;

/**
 * This mixin provides access to private fields in the Item class.
 */
@Mixin(Item.class)
public interface ItemAccessor {
    /**
     * Gets the attack damage modifier ID.
     * @return The attack damage modifier ID.
     */
    @Accessor("ATTACK_DAMAGE_MODIFIER_ID")
    static UUID getAttackDamageModifierId() {
        throw new AssertionError();
    }

    /**
     * Gets the attack speed modifier ID.
     * @return The attack speed modifier ID.
     */
    @Accessor("ATTACK_SPEED_MODIFIER_ID")
    static UUID getAttackSpeedModifierId() {
        throw new AssertionError();
    }
}