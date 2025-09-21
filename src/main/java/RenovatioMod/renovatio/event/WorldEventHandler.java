package RenovatioMod.renovatio.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import RenovatioMod.renovatio.stage.StageManager;
import RenovatioMod.renovatio.stage.Stage;

/**
 * This class handles world-related events, such as ticking the stage manager and triggering blood moons.
 */
public class WorldEventHandler {

    private static final int TICKS_PER_DAY = 24000;

    /**
     * Registers the world event handlers.
     */
    public static void register() {
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            // world is already ServerWorld, no cast needed
            StageManager.checkAndUpdate(world);

            Stage currentStage = StageManager.get(world).getStage();

            long timeOfDay = world.getTimeOfDay() % 24000;

            if (timeOfDay == 13000) {
                BloodMoonHandler.tryTrigger(world, currentStage.getId());
            }

            BloodMoonHandler.tickDayExtension(world);
        });
    }

}
