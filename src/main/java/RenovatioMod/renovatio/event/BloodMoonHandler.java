package RenovatioMod.renovatio.event;

import net.minecraft.server.world.ServerWorld;
import RenovatioMod.renovatio.stage.Stage;

import java.util.Random;

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

    public static boolean isBloodMoon() {
        return isBloodMoon;
    }

    public static boolean isCrimsonMoon() {
        return isCrimson;
    }

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
