package RenovatioMod.renovatio.client;

import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class WeaponAttributeTooltip {

    private static final double BASE_ATTACK_REACH = 3.0;
    private static final double BASE_CRIT_DAMAGE = 1.5;

    public static void initialize() {
        ItemTooltipCallback.EVENT.register((itemStack, context, lines) -> {

            // --- NEW, ROBUST CHECK ---
            // First, check if the item is a tool or weapon. If not, do nothing.
            if (!(itemStack.getItem() instanceof ToolItem) && !(itemStack.getItem() instanceof SwordItem)) {
                return;
            }

            int insertIndex = -1;
            Text attackDamageLine = null;
            Text attackSpeedLine = null;
            Text attackRangeLine = null;
            Text critDamageLine = null;

            // Loop through tooltip lines and find relevant attribute lines
            for (int i = 0; i < lines.size(); i++) {
                String lower = lines.get(i).getString().toLowerCase();
                // We now check more carefully for the "When in main hand:" text to avoid errors
                if (lower.contains("when in main hand")) {
                    insertIndex = i + 1;
                    // Remove all default attribute lines that follow
                    while (i + 1 < lines.size() && lines.get(i + 1).getString().startsWith(" ")) {
                        String line = lines.get(i + 1).getString().toLowerCase();
                        if (line.contains("attack damage")) {
                            attackDamageLine = lines.remove(i + 1);
                        } else if (line.contains("attack speed")) {
                            attackSpeedLine = lines.remove(i + 1);
                        } else {
                            // Remove any other attribute lines like attack range or crit that might be present
                            lines.remove(i + 1);
                        }
                    }
                    break; // Exit loop once we've found and processed the attribute section
                }
            }

            // If we didn't find the "When in main hand" section, do nothing.
            if (insertIndex == -1) return;

            // Read modifiers from item
            Multimap<EntityAttribute, EntityAttributeModifier> modifiers = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

            // Attack Range
            if (modifiers.containsKey(ReachEntityAttributes.ATTACK_RANGE)) {
                double totalModifier = modifiers.get(ReachEntityAttributes.ATTACK_RANGE).stream().mapToDouble(EntityAttributeModifier::getValue).sum();
                attackRangeLine = Text.literal(String.format(" %.2f Attack Range", BASE_ATTACK_REACH + totalModifier)).formatted(Formatting.DARK_GREEN);
            } else {
                attackRangeLine = Text.literal(String.format(" %.2f Attack Range", BASE_ATTACK_REACH)).formatted(Formatting.DARK_GREEN);
            }

            // Crit Damage
            if (modifiers.containsKey(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE)) {
                double totalCritBonus = modifiers.get(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE).stream().mapToDouble(EntityAttributeModifier::getValue).sum();
                int critPercent = (int) Math.round((BASE_CRIT_DAMAGE + totalCritBonus) * 100.0);
                critDamageLine = Text.literal(" " + critPercent + "% Crit Damage").formatted(Formatting.DARK_GREEN);
            } else {
                critDamageLine = Text.literal(" 150% Falling Crit Damage").formatted(Formatting.DARK_GREEN);
            }

            // Re-insert in correct order
            if (attackDamageLine != null) lines.add(insertIndex++, attackDamageLine);
            if (attackSpeedLine != null) lines.add(insertIndex++, attackSpeedLine);
            if (attackRangeLine != null) lines.add(insertIndex++, attackRangeLine);
            if (critDamageLine != null) lines.add(insertIndex, critDamageLine);
        });
    }
}