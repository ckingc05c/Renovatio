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

/**
 * Manages the global stage of the game.
 * The stage determines the difficulty of the game and can be advanced by meeting certain criteria.
 */
public class StageManager extends PersistentState {

    private static final String KEY = "renovatio_stage";
    private static final String NBT_STAGE = "global_stage";
    private static final String NBT_LOCKED = "isLocked";

    private Stage currentStage = Stage.NOVICE;
    private boolean isLocked = false;

    /**
     * Default constructor for new data.
     */
    public StageManager() {
        // Default constructor for new data
    }

    /**
     * Gets the current stage.
     * @return The current stage.
     */
    public Stage getStage() {
        return currentStage;
    }

    /**
     * Sets the current stage.
     * @param stage The stage to set.
     */
    public void setStage(Stage stage) {
        this.currentStage = stage;
        System.out.println("[Renovatio] Stage set to " + currentStage.getDisplayName());
        markDirty(); // Tell Minecraft to save this state
    }

    /**
     * Checks if the stage is locked.
     * @return True if the stage is locked, false otherwise.
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Sets whether the stage is locked.
     * @param locked True to lock the stage, false to unlock it.
     */
    public void setLocked(boolean locked) {
        this.isLocked = locked;
        markDirty();
    }

    /**
     * Increases the stage to the next level.
     */
    public void increaseStage() {
        if (currentStage != Stage.ASCENDANT) {
            currentStage = Stage.next(currentStage);
            markDirty();
            System.out.println("[Renovatio] Stage increased to " + currentStage.getDisplayName());
        }
    }

    /**
     * Writes the stage data to an NBT compound.
     * @param nbt The NBT compound to write to.
     * @return The NBT compound with the stage data.
     */
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt(NBT_STAGE, currentStage.getId());
        nbt.putBoolean(NBT_LOCKED, isLocked);
        return nbt;
    }

    /**
     * Creates a StageManager from an NBT compound.
     * @param nbt The NBT compound to read from.
     * @return A new StageManager instance.
     */
    public static StageManager fromNbt(NbtCompound nbt) {
        StageManager data = new StageManager();
        int stageId = nbt.contains(NBT_STAGE) ? nbt.getInt(NBT_STAGE) : Stage.NOVICE.getId();
        data.currentStage = Stage.fromId(stageId);
        data.isLocked = nbt.getBoolean(NBT_LOCKED);
        return data;
    }

    /**
     * Gets the StageManager for the given world.
     * @param world The world to get the StageManager for.
     * @return The StageManager for the world.
     */
    public static StageManager get(ServerWorld world) {
        ServerWorld overworld = world.getServer().getOverworld();
        return overworld.getPersistentStateManager().getOrCreate(StageManager::fromNbt, StageManager::new, KEY);
    }

    /**
     * Checks if the stage should be updated and updates it if necessary.
     * @param world The world to check.
     */
    public static void checkAndUpdate(ServerWorld world) {
        StageManager manager = get(world);
        if (manager.isLocked()) {
            return; // Do not update if the stage is locked
        }
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