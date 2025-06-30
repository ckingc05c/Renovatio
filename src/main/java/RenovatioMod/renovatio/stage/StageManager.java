package RenovatioMod.renovatio.stage;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.world.PersistentState;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StageManager extends PersistentState {

    private static final String KEY = "renovatio_stage";
    private static final String NBT_STAGE = "global_stage";

    private Stage currentStage = Stage.NOVICE;

    public StageManager() {
        // Default constructor for new data
    }

    public Stage getStage() {
        return currentStage;
    }

    public void setStage(Stage stage) {
        this.currentStage = stage;
        System.out.println("[Renovatio] Stage set to " + currentStage.getDisplayName());
        markDirty(); // Tell Minecraft to save this state
    }

    public void increaseStage() {
        if (currentStage != Stage.ASCENDANT) {
            currentStage = Stage.next(currentStage);
            markDirty();
            System.out.println("[Renovatio] Stage increased to " + currentStage.getDisplayName());
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt(NBT_STAGE, currentStage.getId());
        return nbt;
    }

    public static StageManager fromNbt(NbtCompound nbt) {
        StageManager data = new StageManager();
        int stageId = nbt.contains(NBT_STAGE) ? nbt.getInt(NBT_STAGE) : Stage.NOVICE.getId();
        data.currentStage = Stage.fromId(stageId);
        return data;
    }

    public static StageManager get(ServerWorld world) {
        ServerWorld overworld = world.getServer().getOverworld();
        return overworld.getPersistentStateManager().getOrCreate(StageManager::fromNbt, StageManager::new, KEY);
    }

    public static void checkAndUpdate(ServerWorld world) {
        StageManager manager = get(world);
        Stage stage = manager.getStage();

        MinecraftServer server = world.getServer();
        //Stage 1 → 2
        if (stage == Stage.NOVICE && world.getTimeOfDay() > 72000) {
            manager.setStage(Stage.NORMAL);
            return;
        }
        //Stage 2 → 3
        if (stage == Stage.NORMAL) {
            for (ServerPlayerEntity player : world.getPlayers()) {
                int playerKills = player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.MOB_KILLS));
                if (playerKills >= 100) {
                    manager.setStage(Stage.ADEPT);
                    return;
                }
            }
        }
        //Stage 3 → 4
        if (stage == Stage.ADEPT) {
            Identifier advancementId = new Identifier("minecraft", "story/enter_the_nether");
            Advancement advancement = server.getAdvancementLoader().get(advancementId);

            if (advancement != null) {
                for (ServerPlayerEntity player : world.getPlayers()) {
                    PlayerAdvancementTracker tracker = player.getAdvancementTracker();
                    if (tracker.getProgress(advancement).isDone()) {
                        manager.setStage(Stage.ADVANCED);
                        return;
                    }
                }
            }
        }
        //Stage 4 → 5
        if (stage == Stage.ADVANCED) {
            boolean gotWitherSkull = false;
            boolean returnedToSender = false;
            boolean distractedPiglin = false;
            boolean gotBlazeRod = false;

            Identifier witherSkullId = new Identifier("minecraft", "nether/get_wither_skull");
            Identifier returnSenderId = new Identifier("minecraft", "nether/return_to_sender");
            Identifier distractPiglinId = new Identifier("minecraft", "nether/distract_piglin");
            Identifier blazeRodId = new Identifier("minecraft", "nether/obtain_blaze_rod");

            Advancement witherSkull = server.getAdvancementLoader().get(witherSkullId);
            Advancement returnSender = server.getAdvancementLoader().get(returnSenderId);
            Advancement distractPiglin = server.getAdvancementLoader().get(distractPiglinId);
            Advancement blazeRod = server.getAdvancementLoader().get(blazeRodId);

            for (ServerPlayerEntity player : world.getPlayers()) {
                PlayerAdvancementTracker tracker = player.getAdvancementTracker();

                if (witherSkull != null && tracker.getProgress(witherSkull).isDone()) gotWitherSkull = true;
                if (returnSender != null && tracker.getProgress(returnSender).isDone()) returnedToSender = true;
                if (distractPiglin != null && tracker.getProgress(distractPiglin).isDone()) distractedPiglin = true;
                if (blazeRod != null && tracker.getProgress(blazeRod).isDone()) gotBlazeRod = true;
            }

            if (gotWitherSkull && returnedToSender && distractedPiglin && gotBlazeRod) {
                manager.setStage(Stage.ELITE);
                return;
            }
        }

        //Stage 5 → 6
        if (stage == Stage.ELITE) {
            for (ServerPlayerEntity player : world.getPlayers()) {
                Stat<EntityType<?>> witherStat = Stats.KILLED.getOrCreateStat(EntityType.WITHER);
                int witherKills = player.getStatHandler().getStat(witherStat);
                if (witherKills > 0) {
                    manager.setStage(Stage.VETERAN);
                    return;
                }
            }
        }
        //Stage 6 → 7
        if (stage == Stage.VETERAN) {
            int requiredEyeCount = 8; // Easily tweakable

            List<String> eyeAdvancementKeys = Arrays.asList(
                    "magical_eye", "nether_eye", "old_eye", "rogue_eye",
                    "root", "undead_eye", "witch_eye", "wither_eye",
                    "black_eye", "cold_eye", "corrupted_eye", "cryptic_eye",
                    "cursed_eye", "evil_eye", "exotic_eye", "guardian_eye",
                    "lost_eye"
            );

            Set<String> unlockedEyes = new HashSet<>();

            for (ServerPlayerEntity player : world.getPlayers()) {
                PlayerAdvancementTracker tracker = player.getAdvancementTracker();

                for (String eyeKey : eyeAdvancementKeys) {
                    if (unlockedEyes.contains(eyeKey)) continue;

                    Identifier advancementId = new Identifier("endrem", "main/" + eyeKey);
                    Advancement advancement = server.getAdvancementLoader().get(advancementId);

                    if (advancement != null && tracker.getProgress(advancement).isDone()) {
                        unlockedEyes.add(eyeKey);
                    }

                    // Early exit if we already have enough
                    if (unlockedEyes.size() >= requiredEyeCount) {
                        manager.setStage(Stage.EXPERT);
                        return;
                    }
                }
            }
        }

        //Stage 7 → 8
        if (stage == Stage.EXPERT) {
            for (ServerPlayerEntity player : world.getPlayers()) {
                Stat<EntityType<?>> dragonStat = Stats.KILLED.getOrCreateStat(EntityType.ENDER_DRAGON);
                int dragonKills = player.getStatHandler().getStat(dragonStat);
                if (dragonKills > 0) {
                    manager.setStage(Stage.CHAMPION);
                    return;
                }
            }
        }

        // TODO: Add other stages here!
    }


}
