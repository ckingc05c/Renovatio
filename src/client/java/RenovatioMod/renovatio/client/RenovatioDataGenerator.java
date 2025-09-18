package RenovatioMod.renovatio.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * This class is the data generator for the mod.
 */
public class RenovatioDataGenerator implements DataGeneratorEntrypoint {

    /**
     * Initializes the data generator.
     * @param fabricDataGenerator The data generator.
     */
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
    }
}
