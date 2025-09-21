package RenovatioMod.renovatio.attribute;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import RenovatioMod.renovatio.Renovatio;

public class ModAttributes {
    public static final EntityAttribute GENERIC_MAX_TOUGHNESS = new ClampedEntityAttribute(
            "attribute.name.generic.max_toughness",
            20.0, // Default value
            0.0,   // Min value
            1024.0 // Max value
    ).setTracked(true);

    public static void registerAttributes() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(Renovatio.MOD_ID, "generic.max_toughness"), GENERIC_MAX_TOUGHNESS);

        // --- CHANGE IS HERE ---
        // Loop through all entity types and add the attribute to any that are LivingEntities
        for (EntityType<?> type : Registries.ENTITY_TYPE) {
            if (type.getBaseClass().isAssignableFrom(LivingEntity.class)) {
                // This cast is safe because we checked it in the if statement
                @SuppressWarnings("unchecked")
                EntityType<LivingEntity> livingEntityType = (EntityType<LivingEntity>) type;
                FabricDefaultAttributeRegistry.register(livingEntityType, LivingEntity.createLivingAttributes().add(GENERIC_MAX_TOUGHNESS));
            }
        }
    }
}
