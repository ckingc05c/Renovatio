package RenovatioMod.renovatio.client;

import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.item.ItemStack;

public class WeaponAttributeTooltip {

    private static final double BASE_ATTACK_REACH = 3.0;
    private static final double BASE_CRIT_DAMAGE = 1.5; // 100% base multiplier

    public static void initialize() {
        ItemTooltipCallback.EVENT.register((itemStack, context, lines) -> {

            int insertIndex = -1;
            Text attackDamageLine = null;
            Text attackSpeedLine = null;
            Text attackRangeLine = null;
            Text critDamageLine = null;

// Loop through tooltip lines and find relevant attribute lines
            for (int i = 0; i < lines.size(); i++) {
                String lower = lines.get(i).getString().toLowerCase();
                if (lower.contains("attack damage")) {
                    attackDamageLine = lines.remove(i);
                    i--;
                } else if (lower.contains("attack speed")) {
                    attackSpeedLine = lines.remove(i);
                    i--;
                } else if (lower.contains("attack range") || lower.contains("attack reach")) {
                    lines.remove(i);
                    i--;
                } else if (lower.contains("reach")) {
                    lines.remove(i);
                    i--;
                } else if (lower.contains("crit damage")) {
                    lines.remove(i);
                    i--;
                } else if (insertIndex == -1 && lower.contains("when in main hand")) {
                    insertIndex = i + 1;
                }
            }

// Read modifiers from item
            Multimap<EntityAttribute, EntityAttributeModifier> modifiers = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);
            if (modifiers == null || modifiers.isEmpty()) return;

// Default flags for whether to show Reach and Crit
            boolean isWeapon = attackDamageLine != null || attackSpeedLine != null;

// Attack Range (use modifier if exists, otherwise default 3.00 if weapon/tool)
            if (modifiers.containsKey(ReachEntityAttributes.ATTACK_RANGE)) {
                double totalModifier = modifiers.get(ReachEntityAttributes.ATTACK_RANGE)
                        .stream().mapToDouble(EntityAttributeModifier::getValue).sum();
                double effectiveReach = BASE_ATTACK_REACH + totalModifier;
                String reachText = String.format(" %.2f Attack Range", effectiveReach);
                attackRangeLine = Text.literal(reachText).formatted(Formatting.DARK_GREEN);
            } else if (isWeapon) {
                // Default reach if tool/weapon
                attackRangeLine = Text.literal(String.format(" %.2f Attack Range", BASE_ATTACK_REACH))
                        .formatted(Formatting.DARK_GREEN);
            }

// Crit Damage (use modifier if exists, otherwise default 150% if weapon/tool)
            if (modifiers.containsKey(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE)) {
                double totalCritBonus = modifiers.get(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE)
                        .stream().mapToDouble(EntityAttributeModifier::getValue).sum();
                double effectiveCrit = BASE_CRIT_DAMAGE + totalCritBonus;
                int critPercent = (int) Math.round(effectiveCrit * 100.0);
                critDamageLine = Text.literal(" " + critPercent + "% Crit Damage").formatted(Formatting.DARK_GREEN);
            } else if (isWeapon) {
                // Default crit damage if tool/weapon
                critDamageLine = Text.literal(" 150% Crit Damage").formatted(Formatting.DARK_GREEN);
            }

// Re-insert in correct order
            if (insertIndex != -1) {
                if (attackDamageLine != null) lines.add(insertIndex++, attackDamageLine);
                if (attackSpeedLine != null) lines.add(insertIndex++, attackSpeedLine);
                if (attackRangeLine != null) lines.add(insertIndex++, attackRangeLine);
                if (critDamageLine != null) lines.add(insertIndex++, critDamageLine);
            }
        });
    }
}
