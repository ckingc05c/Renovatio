package RenovatioMod.renovatio.stage.effects;

import RenovatioMod.renovatio.stage.Stage;
import com.ibm.icu.impl.CacheValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;

/**
 * This class contains the effects for the different stages.
 */
public class StageEffects {



    /**
     * Gets the health modifier for a given stage.
     * @param stage The stage.
     * @return The health modifier.
     */
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

    /**
     * Gets the melee damage modifier for a given stage.
     * @param stage The stage.
     * @return The melee damage modifier.
     */
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

    /**
     * Gets the projectile damage modifier for a given stage.
     * @param stage The stage.
     * @return The projectile damage modifier.
     */
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

    /**
     * Gets the explosion damage modifier for a given stage.
     * @param stage The stage.
     * @return The explosion damage modifier.
     */
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

    /**
     * Gets the creeper explosion strength for a given stage.
     * @param stage The stage.
     * @return The creeper explosion strength.
     */
    public static int getCreeperExplosionStrength(Stage stage) {
        return switch (stage) {
            case NOVICE -> 2;
            case NORMAL, ADVANCED, ADEPT -> 3;
            case ELITE, EXPERT, VETERAN -> 4;
            case CHAMPION, MASTER -> 5;
            case ASCENDANT -> 6;
        };
    }


    /**
     * Gets the ghast fireball strength for a given stage.
     * @param stage The stage.
     * @return The ghast fireball strength.
     */
    public static int getGhastFireballStrength(Stage stage) {
        return switch (stage) {
            case NOVICE, NORMAL, ADEPT -> 1;
            case ADVANCED, ELITE -> 2;
            case VETERAN, EXPERT -> 3;
            case CHAMPION, MASTER -> 4;
            case ASCENDANT -> 5;
        };
    }

    /**
     * Gets the ghast fireball speed for a given stage.
     * @param stage The stage.
     * @return The ghast fireball speed.
     */
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

    /**
     * Gets the negative potion duration modifier for a given stage.
     * @param stage The stage.
     * @return The negative potion duration modifier.
     */
    public static double getNegativePotionDurationModifier(Stage stage) {
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





    /**
     * Gets the environmental damage modifier for a given stage.
     * @param stage The stage.
     * @return The environmental damage modifier.
     */
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

    /**
     * Gets the magic damage modifier for a given stage.
     * @param stage The stage.
     * @return The magic damage modifier.
     */
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

    /**
     * Gets the speed modifier for a given stage.
     * @param stage The stage.
     * @return The speed modifier.
     */
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

    /**
     * Gets the aggro modifier for a given stage.
     * @param stage The stage.
     * @return The aggro modifier.
     */
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

    /**
     * Gets the base damage of a living entity.
     * @param entity The entity.
     * @return The base damage of the entity.
     */
    private static double getBaseDamage(LivingEntity entity) {
        return entity.getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }
}
