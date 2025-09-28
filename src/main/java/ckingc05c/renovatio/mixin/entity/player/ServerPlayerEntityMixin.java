package ckingc05c.renovatio.mixin.entity.player;

import ckingc05c.renovatio.attribute.ModAttributes;
import ckingc05c.renovatio.combat.toughness.ToughnessEntity;
import ckingc05c.renovatio.combat.toughness.ToughnessManager;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void resetToughnessOnRespawn(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        // When a player respawns after death, 'alive' is false.
        if (!alive) {
            // Reset their current toughness to its maximum value.
            float maxToughness = (float) this.getAttributeValue(ModAttributes.GENERIC_MAX_TOUGHNESS);

            ToughnessManager.setToughness(new ToughnessEntity(this), maxToughness);
        }
    }
}

