package RenovatioMod.renovatio.effects;

import RenovatioMod.renovatio.stage.Stage;
import RenovatioMod.renovatio.stage.StageManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.attribute.EntityAttributes;

public class StageEffects {



    // Example modifier functions
    public static double getHealthModifier(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.8;
            case NORMAL -> 1.0;
            case ADEPT -> 1.25;
            case ADVANCED -> 1.5;
            case ELITE -> 1.75;
            case VETERAN -> 2.0;
            case EXPERT -> 2.25;
            case CHAMPION -> 2.5;
            case MASTER -> 2.75;
            case ASCENDANT -> 3.0;
        };
    }

    public static double getDamageModifier(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.9;
            case NORMAL -> 1.0;
            case ADEPT -> 1.1;
            case ADVANCED -> 1.2;
            case ELITE -> 1.25;
            case VETERAN -> 1.3;
            case EXPERT -> 1.5;
            case CHAMPION -> 1.75;
            case MASTER -> 2.0;
            case ASCENDANT -> 2.5;
        };
    }

    public static double getSpeedModifier(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.95;
            case NORMAL -> 1.0;
            case ADEPT -> 1.05;
            case ADVANCED -> 1.10;
            case ELITE -> 1.15;
            case VETERAN -> 1.20;
            case EXPERT -> 1.25;
            case CHAMPION -> 1.30;
            case MASTER -> 1.35;
            case ASCENDANT -> 1.5;
        };
    }

    public static double getAggroModifier(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.88;
            case NORMAL -> 1.0;
            case ADEPT -> 1.13;
            case ADVANCED -> 1.25;
            case ELITE -> 1.38;
            case VETERAN -> 1.5;
            case EXPERT -> 1.63;
            case CHAMPION -> 1.75;
            case MASTER -> 1.88;
            case ASCENDANT -> 2.0;
        };
    }

    // Custom method to get base damage (or store a base value elsewhere)
    private static double getBaseDamage(LivingEntity entity) {
        return entity.getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }
}
