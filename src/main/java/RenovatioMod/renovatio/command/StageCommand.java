package RenovatioMod.renovatio.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import RenovatioMod.renovatio.stage.Stage;
import RenovatioMod.renovatio.stage.StageManager;
import java.util.Arrays;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class StageCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("stage")
                .requires(source -> source.hasPermissionLevel(2))
                .then(literal("set")
                        .then(argument("stage", StringArgumentType.word())
                                .suggests((context, builder) -> {
                                    for (Stage stage : Stage.values()) {
                                        builder.suggest(stage.name().toLowerCase());
                                    }
                                    return builder.buildFuture();
                                })
                                .executes(ctx -> executeSet(ctx, StringArgumentType.getString(ctx, "stage"), null))
                                .then(argument("lock_status", StringArgumentType.word())
                                        .suggests((context, builder) -> {
                                            builder.suggest("lock");
                                            builder.suggest("unlock");
                                            return builder.buildFuture();
                                        })
                                        .executes(ctx -> executeSet(ctx, StringArgumentType.getString(ctx, "stage"), StringArgumentType.getString(ctx, "lock_status")))
                                )
                        )
                )
                .then(literal("lock")
                        .executes(StageCommand::executeLock)
                )
                .then(literal("unlock")
                        .executes(StageCommand::executeUnlock)
                )
                .then(literal("get")
                        .executes(StageCommand::executeGet)
                )
        );
    }

    private static int executeSet(CommandContext<ServerCommandSource> ctx, String stageName, String lockStatus) throws CommandSyntaxException {
        ServerWorld world = ctx.getSource().getWorld();
        StageManager manager = StageManager.get(world);

        Stage targetStage = Arrays.stream(Stage.values())
                .filter(s -> s.name().equalsIgnoreCase(stageName))
                .findFirst()
                .orElse(null);

        if (targetStage == null) {
            ctx.getSource().sendError(Text.literal("Unknown stage: " + stageName));
            return 0;
        }

        manager.setStage(targetStage);
        ctx.getSource().sendFeedback(() -> Text.literal("Stage set to " + targetStage.getDisplayName()), true);

        if (lockStatus != null) {
            if (lockStatus.equalsIgnoreCase("lock")) {
                manager.setLocked(true);
                ctx.getSource().sendFeedback(() -> Text.literal("Stage progression is now locked."), true);
            } else if (lockStatus.equalsIgnoreCase("unlock")) {
                manager.setLocked(false);
                ctx.getSource().sendFeedback(() -> Text.literal("Stage progression is now unlocked."), true);
            }
        }

        return 1;
    }

    private static int executeLock(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerWorld world = ctx.getSource().getWorld();
        StageManager manager = StageManager.get(world);
        manager.setLocked(true);
        ctx.getSource().sendFeedback(() -> Text.literal("Stage progression is now locked."), true);
        return 1;
    }

    private static int executeUnlock(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerWorld world = ctx.getSource().getWorld();
        StageManager manager = StageManager.get(world);
        manager.setLocked(false);
        ctx.getSource().sendFeedback(() -> Text.literal("Stage progression is now unlocked."), true);
        return 1;
    }

    private static int executeGet(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerWorld world = ctx.getSource().getWorld();
        StageManager manager = StageManager.get(world);
        Stage currentStage = manager.getStage();
        boolean isLocked = manager.isLocked();

        ctx.getSource().sendFeedback(() -> Text.literal("Current stage: " + currentStage.getDisplayName() + " (Progress: " + (isLocked ? "Locked" : "Unlocked") + ")"), false);
        return 1;
    }
}