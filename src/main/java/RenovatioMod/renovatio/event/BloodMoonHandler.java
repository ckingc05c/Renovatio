package RenovatioMod.renovatio.event;

import net.minecraft.server.world.ServerWorld;
import RenovatioMod.renovatio.stage.Stage;

import java.util.Random;

/**
 * This class handles the logic for the Blood Moon and Crimson Moon events.
 * These events are triggered randomly at night and increase the difficulty of the game.
 */
public class BloodMoonHandler {

    private static final Random RANDOM = new Random();

    // Tracks if Blood Moon is active
    private static boolean isBloodMoon = false;
    private static boolean isCrimson = false;
    private static int crimsonTicksLeft = 0;
    // Lookup table for Blood Moon and Crimson Moon chances by stage ID
    private static final double[] BLOOD_MOON_CHANCES = {
            0.00, 0.05, 0.075, 0.10, 0.125,
            0.15, 0.175, 0.20, 0.225, 0.25
    };

    private static final double[] CRIMSON_MOON_CHANCES = {
            0.00, 0.025, 0.0325, 0.05, 0.075,
            0.10, 0.125, 0.15, 0.20, 0.25
    };

    /**
     * Checks if a Blood Moon is currently active.
     *
     * @return true if a Blood Moon is active, false otherwise.
     */
    public static boolean isBloodMoon() {
        return isBloodMoon;
    }

    /**
     * Checks if a Crimson Moon is currently active.
     *
     * @return true if a Crimson Moon is active, false otherwise.
     */
    public static boolean isCrimsonMoon() {
        return isCrimson;
    }

    /**
     * Tries to trigger a Blood Moon or Crimson Moon event.
     * The chance of an event triggering depends on the current stage.
     *
     * @param world The server world.
     * @param stageId The current stage ID.
     */
    public static void tryTrigger(ServerWorld world, int stageId) {
        // Clamp stageId to valid range
        int index = Math.max(0, Math.min(stageId - 1, BLOOD_MOON_CHANCES.length - 1));
        double bloodMoonChance = BLOOD_MOON_CHANCES[index];
        double crimsonChance = CRIMSON_MOON_CHANCES[index];

        if (RANDOM.nextDouble() < bloodMoonChance) {
            isBloodMoon = true;
            isCrimson = RANDOM.nextDouble() < crimsonChance;

            System.out.println("[Renovatio] Blood Moon triggered" + (isCrimson ? " (CRIMSON)" : ""));

            if (isCrimson) {
                crimsonTicksLeft = 48000; // 1 extra day/night cycle
            }
        } else {
            isBloodMoon = false;
            isCrimson = false;
        }
    }

    /**
     * Ticks the day extension for Crimson Moons.
     *
     * @param world The server world.
     */
    public static void tickDayExtension(ServerWorld world) {
        if (crimsonTicksLeft > 0) {
            crimsonTicksLeft--;
            // Optional: Add time delay logic if needed
        } else {
            isBloodMoon = false;
            isCrimson = false;
        }
    }
}
