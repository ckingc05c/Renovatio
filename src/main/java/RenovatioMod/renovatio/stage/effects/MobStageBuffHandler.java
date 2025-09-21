package RenovatioMod.renovatio.stage.effects;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.world.ServerWorld;
import RenovatioMod.renovatio.stage.StageManager;
import RenovatioMod.renovatio.stage.Stage;

import java.util.UUID;

/**
 * This class handles the application of stage-based buffs to mobs.
 */
public class MobStageBuffHandler {

    // UUIDs to prevent stacking modifiers
    private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("aaaa1111-2222-3333-4444-555566667777");
    private static final UUID DAMAGE_MODIFIER_UUID = UUID.fromString("bbbb1111-2222-3333-4444-555566667777");
    private static final UUID SPEED_MODIFIER_UUID = UUID.fromString("cccc1111-2222-3333-4444-555566667777");
    private static final UUID FOLLOW_RANGE_MODIFIER_UUID = UUID.fromString("dddd1111-2222-3333-4444-555566667777");



    /**
     * Registers the entity load event listener.
     */
    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!(entity instanceof LivingEntity living)) return;
            applyStageBuffs(living, (ServerWorld) world);
        });
    }

    /**
     * Applies stage-based buffs to a living entity.
     * @param entity The entity to apply the buffs to.
     * @param world The world the entity is in.
     */
    public static void applyStageBuffs(LivingEntity entity, ServerWorld world) {
        if (!entity.isPlayer()){
            Stage stage = StageManager.get(world).getStage();

            double healthMult = StageEffects.getHealthModifier(stage);
            double damageMult = StageEffects.getMeleeDamageModifier(stage);
            double speedMult = StageEffects.getSpeedModifier(stage);
            double aggroMult = StageEffects.getAggroModifier(stage);

            // Apply health modifier
            var healthAttr = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
            if (healthAttr != null && healthAttr.getModifier(HEALTH_MODIFIER_UUID) == null) {
                double bonus = healthAttr.getBaseValue() * (healthMult - 1.0);
                healthAttr.addPersistentModifier(new EntityAttributeModifier(
                        HEALTH_MODIFIER_UUID,
                        "Stage health bonus",
                        bonus,
                        EntityAttributeModifier.Operation.ADDITION
                ));
                entity.setHealth((float) healthAttr.getValue()); // Heal to new max
            }

            // Apply damage modifier
            var damageAttr = entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            if (damageAttr != null && damageAttr.getModifier(DAMAGE_MODIFIER_UUID) == null) {
                double bonus = damageAttr.getBaseValue() * (damageMult - 1.0);
                damageAttr.addPersistentModifier(new EntityAttributeModifier(
                        DAMAGE_MODIFIER_UUID,
                        "Stage damage bonus",
                        bonus,
                        EntityAttributeModifier.Operation.ADDITION
                ));
            }
            // Apply speed modifier
            var speedAttr = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) == null) {
                double bonus = speedAttr.getBaseValue() * (speedMult - 1.0);
                speedAttr.addPersistentModifier(new EntityAttributeModifier(
                        SPEED_MODIFIER_UUID,
                        "Stage speed bonus",
                        bonus,
                        EntityAttributeModifier.Operation.ADDITION
                ));
            }
            // Apply aggro range modifier
            var aggroAttr = entity.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE);
            if (aggroAttr != null && aggroAttr.getModifier(FOLLOW_RANGE_MODIFIER_UUID) == null) {
                double bonus = aggroAttr.getBaseValue() * (aggroMult - 1.0);
                aggroAttr.addPersistentModifier(new EntityAttributeModifier(
                        FOLLOW_RANGE_MODIFIER_UUID,
                        "Stage aggro range bonus",
                        bonus,
                        EntityAttributeModifier.Operation.ADDITION
                ));
            }

        }

    }
}
