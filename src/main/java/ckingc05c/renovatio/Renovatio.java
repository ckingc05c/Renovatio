package ckingc05c.renovatio;

import ckingc05c.renovatio.attribute.ModAttributes;
import ckingc05c.renovatio.block.ModBlocks;
import ckingc05c.renovatio.combat.toughness.ToughnessEntity;
import ckingc05c.renovatio.combat.toughness.ToughnessEntityManager;
import ckingc05c.renovatio.combat.toughness.ToughnessManager;
import ckingc05c.renovatio.command.GetStatsCommand;
import ckingc05c.renovatio.effect.status.ModStatusEffect;
import ckingc05c.renovatio.entity.mob.hostile.boss.OminousBossHandler;
import ckingc05c.renovatio.stage.effects.MobStageBuffHandler;
import ckingc05c.renovatio.event.AttributeEffectHandler;
import ckingc05c.renovatio.event.ItemAttributeHandler;
import ckingc05c.renovatio.event.WorldEventHandler;
import ckingc05c.renovatio.item.ModItems;
import ckingc05c.renovatio.registry.ModBlockEntities;
import ckingc05c.renovatio.registry.ModRecipeTypes;
import ckingc05c.renovatio.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import ckingc05c.renovatio.command.StageCommand;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main initializer class for the Renovatio mod.
 * This class is responsible for registering all the mod's features,
 * such as blocks, items, screen handlers, events, and commands.
 */
public class Renovatio implements ModInitializer {
    /**
     * The unique identifier for the Renovatio mod.
     */
    public static final String MOD_ID = "renovatio";
    /**
     * The logger instance for the Renovatio mod, used for logging messages.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /**
     * This method is called by Fabric when the mod is initialized.
     * It registers all the mod's content and sets up event listeners.
     */
    @Override
    public void onInitialize() {
        // Corrected Order: Blocks first, then Items
        ModBlocks.registerModBlocks();
        ModItems.registerItems();
        ModScreenHandlers.register(); // Add this line
        ItemAttributeHandler.register(); // Add this line
        AttributeEffectHandler.initialize(); // Add this line
        ModStatusEffect.registerStatusEffects();
        ModAttributes.registerAttributes();

        ModBlockEntities.register();
        ModRecipeTypes.register(); // Register your custom recipe type
        MobStageBuffHandler.register();
        WorldEventHandler.register();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            StageCommand.register(dispatcher);
            GetStatsCommand.register(dispatcher);
        });
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            return ActionResult.PASS; // Not for spawning
        });

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!(entity instanceof LivingEntity living)) return;

            ToughnessEntity livingToughness = ToughnessEntityManager.get(living);
            ToughnessManager.initializeToughness(livingToughness);
            if (entity instanceof WitherEntity ||
                    entity instanceof EnderDragonEntity ||
                    entity instanceof ElderGuardianEntity ||
                    entity.getType().getTranslationKey().contains("warden")) { // covers modded Wardens too

                if (OminousBossHandler.shouldApplyOminous(living)) {
                    OminousBossHandler.applyOminousModifiers(living);
                }
            }
        });
        ServerEntityEvents.ENTITY_UNLOAD.register((entity, world) -> {
            if (entity instanceof LivingEntity living) {
                ToughnessEntityManager.remove(living);
            }
        });
    }
}