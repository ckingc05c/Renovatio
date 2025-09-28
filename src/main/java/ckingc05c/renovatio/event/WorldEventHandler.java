package ckingc05c.renovatio.event;

import ckingc05c.renovatio.combat.toughness.ToughnessEntity;
import ckingc05c.renovatio.combat.toughness.ToughnessEntityManager;
import ckingc05c.renovatio.combat.toughness.ToughnessManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import ckingc05c.renovatio.stage.StageManager;
import ckingc05c.renovatio.stage.Stage;

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
            // --- Toughness Regeneration ---
            // We check if the current game tick is a multiple of 20 (1 second)
            if (world.getTime() % 20 == 0) {
                world.getPlayers().forEach(player -> {
                    ToughnessEntity toughnessPlayer = ToughnessEntityManager.get(player);
                    if (ToughnessManager.canRegenerate(toughnessPlayer)) {
                        ToughnessManager.regenerate(toughnessPlayer);
                    }
                });
            }

            // --- Stage and Blood Moon Logic ---
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
