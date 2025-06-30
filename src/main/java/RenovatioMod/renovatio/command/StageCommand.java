package RenovatioMod.renovatio.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.server.world.ServerWorld;

import RenovatioMod.renovatio.stage.Stage;
import RenovatioMod.renovatio.stage.StageManager;
import com.mojang.brigadier.CommandDispatcher;
import static net.minecraft.server.command.CommandManager.*;

import java.util.Arrays;

public class StageCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("stage")
                        .requires(source -> source.hasPermissionLevel(2)) // Optional permission level check
                        .then(argument("stage", StringArgumentType.word())
                                .suggests((context, builder) -> {
                                    for (Stage stage : Stage.values()) {
                                        builder.suggest(stage.name().toLowerCase());
                                    }
                                    return builder.buildFuture();
                                })
                                .executes(ctx -> execute(ctx, StringArgumentType.getString(ctx, "stage")))
                        )
                        .executes(ctx -> {
                            ServerWorld world = ctx.getSource().getWorld();
                            Stage currentStage = StageManager.get(world).getStage();
                            ctx.getSource().sendFeedback(() ->
                                    Text.literal("Current stage: " + currentStage.getDisplayName()), false);
                            return 1;
                        })
        );
    }

    private static int execute(CommandContext<ServerCommandSource> ctx, String stageName) throws CommandSyntaxException {
        ServerWorld world = ctx.getSource().getWorld();

        // Find matching stage (case insensitive)
        Stage targetStage = Arrays.stream(Stage.values()).filter(s -> s.name().equalsIgnoreCase(stageName)).findFirst().orElse(null);

        if (targetStage == null) {
            ctx.getSource().sendError(Text.literal("Unknown stage: " + stageName));
            return 0;
        }

        StageManager manager = StageManager.get(world);
        manager.setStage(targetStage);
        ctx.getSource().sendFeedback(() -> Text.literal("Stage set to " + targetStage.getDisplayName()), true);

        return 1;
    }
}
