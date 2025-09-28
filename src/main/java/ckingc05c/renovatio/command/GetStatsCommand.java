package ckingc05c.renovatio.command;

import ckingc05c.renovatio.attribute.ModAttributes;
import ckingc05c.renovatio.combat.toughness.ToughnessEntity;
import ckingc05c.renovatio.combat.toughness.ToughnessEntityManager;
import ckingc05c.renovatio.combat.toughness.ToughnessManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * Implements the /getstats command to view an entity's health and toughness.
 */
public class GetStatsCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("getstats")
                .requires(source -> source.hasPermissionLevel(2))
                .then(argument("target", EntityArgumentType.entity())
                        .executes(GetStatsCommand::execute)));
    }

    private static int execute(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Entity target = EntityArgumentType.getEntity(context, "target");
        ServerCommandSource source = context.getSource();
        if (!(target instanceof LivingEntity livingTarget)) {
            source.sendError(Text.literal("Target is not a living entity and does not have stats."));
            return 0;
        }
        ToughnessEntity toughnessEntity = ToughnessEntityManager.get(livingTarget);

        // --- Health ---
        float currentHealth = livingTarget.getHealth();
        float maxHealth = livingTarget.getMaxHealth();
        Text healthText = Text.literal(String.format("%s's Health: %.2f / %.2f",
                        livingTarget.getName().getString(), currentHealth, maxHealth))
                .formatted(Formatting.GREEN);
        source.sendFeedback(() -> healthText, false);


        // --- Toughness ---
        float currentToughness = ToughnessManager.getToughness(toughnessEntity);
        double maxToughness = toughnessEntity.getMaxToughness();
        Text toughnessText = Text.literal(String.format("%s's Toughness: %.2f / %.2f",
                        livingTarget.getName().getString(), currentToughness, maxToughness))
                .formatted(Formatting.AQUA);
        source.sendFeedback(() -> toughnessText, false);


        // --- Toughness State ---
        String currentState = toughnessEntity.getToughnessState().name();
        Text toughnessStateText = Text.literal(String.format("%s's Toughness State is %s",
                        livingTarget.getName().getString(), currentState)).formatted(Formatting.BLUE);
        source.sendFeedback(() -> toughnessStateText, false);
        return 1;
    }
}
