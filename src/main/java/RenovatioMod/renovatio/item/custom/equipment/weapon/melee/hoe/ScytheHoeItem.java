package RenovatioMod.renovatio.item.custom.equipment.weapon.melee.hoe;

import RenovatioMod.renovatio.item.custom.equipment.weapon.melee.MeleeWeaponStats;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

import java.util.UUID;

public class ScytheHoeItem extends HoeItem {
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    private static final MeleeWeaponStats STATS = HoeWeaponStats.SCYTHE;

    // Static UUIDs per attribute type
    private static final UUID REACH_MODIFIER_ID = UUID.fromString("aaaa1111-2222-3333-4444-555566667777");
    private static final UUID CRIT_MODIFIER_ID = UUID.fromString("bbbb1111-2222-3333-4444-555566667777");

    public ScytheHoeItem(ToolMaterial material, Settings settings) {
        super(material, (int) STATS.getAttackDamageBonus(), STATS.getTotalAttackSpeed(), settings);

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

        // Attack Damage
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier",
                        material.getAttackDamage() + STATS.getAttackDamageBonus(),
                        EntityAttributeModifier.Operation.ADDITION));

        // Attack Speed (offset from 4.0 base)
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier",
                        STATS.getAttackSpeedOffset(), EntityAttributeModifier.Operation.ADDITION));

        // Reach bonus
        builder.put(ReachEntityAttributes.ATTACK_RANGE,
                new EntityAttributeModifier(REACH_MODIFIER_ID, "Weapon reach bonus",
                        STATS.getReachOffset(), EntityAttributeModifier.Operation.ADDITION));
        // Reach bonus
        builder.put(ReachEntityAttributes.REACH,
                new EntityAttributeModifier(REACH_MODIFIER_ID, "Weapon reach bonus",
                        STATS.getReachOffset(), EntityAttributeModifier.Operation.ADDITION));

        // Optional: Critical bonus damage
        builder.put(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE,
                new EntityAttributeModifier(CRIT_MODIFIER_ID, "Critical Bonus",
                        STATS.getCritDamage(), EntityAttributeModifier.Operation.ADDITION));

        this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
}
