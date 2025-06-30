package RenovatioMod.renovatio;

import RenovatioMod.renovatio.block.functional.ModBlocks;
import RenovatioMod.renovatio.boss.OminousBossHandler;
import RenovatioMod.renovatio.effects.MobStageBuffHandler;
import RenovatioMod.renovatio.event.WorldEventHandler;
import RenovatioMod.renovatio.item.ModItems;
import RenovatioMod.renovatio.registry.ModBlockEntities;
import RenovatioMod.renovatio.registry.ModRecipeTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import RenovatioMod.renovatio.command.StageCommand;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Renovatio implements ModInitializer {
    public static final String MOD_ID = "renovatio";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // Corrected Order: Blocks first, then Items
        ModBlocks.registerModBlocks();
        ModItems.registerItems();

        ModBlockEntities.register();
        ModRecipeTypes.register(); // Register your custom recipe type
        MobStageBuffHandler.register();
        WorldEventHandler.register();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            StageCommand.register(dispatcher);
        });
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            return ActionResult.PASS; // Not for spawning
        });

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!(entity instanceof LivingEntity living)) return;

            if (entity instanceof WitherEntity ||
                    entity instanceof EnderDragonEntity ||
                    entity instanceof ElderGuardianEntity ||
                    entity.getType().getTranslationKey().contains("warden")) { // covers modded Wardens too

                if (OminousBossHandler.shouldApplyOminous(living)) {
                    OminousBossHandler.applyOminousModifiers(living);
                }
            }
        });
    }
}