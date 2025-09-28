package ckingc05c.renovatio.block;

import ckingc05c.renovatio.Renovatio;
import ckingc05c.renovatio.block.functional.ModFunctionalBlocks;

/**
 * This class is responsible for registering all the blocks in the mod.
 */
public class ModBlocks {


    /**
     * This is the main registration method called from your Renovatio.java
     */
    public static void registerModBlocks() {
        Renovatio.LOGGER.info("Registering ModBlocks for " + Renovatio.MOD_ID);

        ModFunctionalBlocks.registerModBlocks();
    }
}