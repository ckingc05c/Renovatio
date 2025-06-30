package RenovatioMod.renovatio;

import RenovatioMod.renovatio.block.functional.ModBlocks;
import RenovatioMod.renovatio.boss.OminousBossHandler;
import RenovatioMod.renovatio.effects.MobStageBuffHandler;
import RenovatioMod.renovatio.event.WorldEventHandler;
import RenovatioMod.renovatio.item.ModItems;
import RenovatioMod.renovatio.item.ModToolMaterials;
import RenovatioMod.renovatio.registry.ModBlockEntities;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import RenovatioMod.renovatio.command.StageCommand;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class Renovatio implements ModInitializer {

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.register();
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
