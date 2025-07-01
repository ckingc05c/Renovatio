package RenovatioMod.renovatio.effects;

import RenovatioMod.renovatio.stage.Stage;
import com.ibm.icu.impl.CacheValue;
import net.minecraft.entity.LivingEntity;
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

    public static double getMeleeDamageModifier(Stage stage) {
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
    public static double getProjectileDamageModifier(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.85;
            case NORMAL -> 1.0;
            case ADEPT -> 1.05;
            case ADVANCED -> 1.1;
            case ELITE -> 1.15;
            case VETERAN -> 1.25;
            case EXPERT -> 1.375;
            case CHAMPION -> 1.5;
            case MASTER -> 1.625;
            case ASCENDANT -> 2.0;
        };
    }

    public static double getExplosionDamageModifier(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.85;
            case NORMAL -> 1.0;
            case ADEPT -> 1.05;
            case ADVANCED -> 1.1;
            case ELITE -> 1.15;
            case VETERAN -> 1.25;
            case EXPERT -> 1.375;
            case CHAMPION -> 1.5;
            case MASTER -> 1.625;
            case ASCENDANT -> 2.0;
        };
    }
    public static int getCreeperExplosionStrength(Stage stage) {
        return switch (stage) {
            case NOVICE -> 2;
            case NORMAL, ADVANCED, ADEPT -> 3;
            case ELITE, EXPERT, VETERAN -> 4;
            case CHAMPION, MASTER -> 5;
            case ASCENDANT -> 6;
        };
    }
    public static double getCreeperFuseTime(Stage stage) {
        return switch (stage) {
            case NOVICE -> 2.0;
            case NORMAL -> 1.5;
            case ADEPT -> 1.375;
            case ADVANCED -> 1.25;
            case ELITE -> 1.125;
            case VETERAN -> 1.0;
            case EXPERT -> 0.875;
            case CHAMPION -> 0.75;
            case MASTER -> 0.625;
            case ASCENDANT -> 0.5;
        };
    }

    public static int getGhastFireballStrength(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0;
            case NORMAL -> 1;
            case ADEPT -> 1;
            case ADVANCED -> 2;
            case ELITE -> 2;
            case VETERAN -> 3;
            case EXPERT -> 3;
            case CHAMPION -> 4;
            case MASTER -> 4;
            case ASCENDANT -> 5;
        };
    }

    public static double getGhastFireballSpeed(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.95;
            case NORMAL -> 1.0;
            case ADEPT -> 1.05;
            case ADVANCED -> 1.10;
            case ELITE -> 1.15;
            case VETERAN -> 1.20;
            case EXPERT -> 1.30;
            case CHAMPION -> 1.35;
            case MASTER -> 1.40;
            case ASCENDANT -> 1.50;
        };
    }

    public static double getWitchPotionDurationModifier(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.80;
            case NORMAL -> 1.00;
            case ADEPT -> 1.25;
            case ADVANCED -> 1.50;
            case ELITE -> 1.625;
            case VETERAN -> 1.75;
            case EXPERT -> 1.875;
            case CHAMPION -> 2.00;
            case MASTER -> 2.50;
            case ASCENDANT -> 3.00;
        };
    }

    public static int getWitchPotionAmplifier(Stage stage) {
        return switch (stage) {
            case NOVICE, NORMAL, ADEPT -> 0;
            case ADVANCED, ELITE, VETERAN -> 1;
            case EXPERT, CHAMPION, MASTER -> 2;
            case ASCENDANT -> 3;
        };

    }
    public static double getPotionDurationModifier(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.80;
            case NORMAL -> 1.00;
            case ADEPT -> 1.25;
            case ADVANCED -> 1.50;
            case ELITE -> 1.625;
            case VETERAN -> 1.75;
            case EXPERT -> 1.875;
            case CHAMPION -> 2.00;
            case MASTER -> 2.50;
            case ASCENDANT -> 3.00;
        };
    }

    public static int getPotionAmplifier(Stage stage) {
        return switch (stage) {
            case NOVICE, NORMAL, ADEPT -> 0;
            case ADVANCED, ELITE, VETERAN -> 1;
            case EXPERT, CHAMPION, MASTER -> 2;
            case ASCENDANT -> 3;
        };

    }
    public static double getEnvironmentalDamageModifier(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.85;
            case NORMAL -> 1.0;
            case ADEPT -> 1.05;
            case ADVANCED -> 1.1;
            case ELITE -> 1.15;
            case VETERAN -> 1.25;
            case EXPERT -> 1.375;
            case CHAMPION -> 1.5;
            case MASTER -> 1.625;
            case ASCENDANT -> 2.0;
        };
    }
    public static double getMagicDamageModifier(Stage stage) {
        return switch (stage) {
            case NOVICE -> 0.85;
            case NORMAL -> 1.0;
            case ADEPT -> 1.05;
            case ADVANCED -> 1.1;
            case ELITE -> 1.15;
            case VETERAN -> 1.25;
            case EXPERT -> 1.375;
            case CHAMPION -> 1.5;
            case MASTER -> 1.625;
            case ASCENDANT -> 2.0;
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
