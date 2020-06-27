package net.afterrebelion.smokeextender.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(net.minecraft.client.particle.CampfireSmokeParticle.class)
public abstract class SmokeExtenderMixin extends SpriteBillboardParticle {
	public SmokeExtenderMixin(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		super(world, x, y, z, velocityX, velocityY, velocityZ);
	}

	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/client/world/ClientWorld;DDDDDDZ)V")
	private void CampfireSmokeParticle (ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, boolean signalFire, CallbackInfo info) {
		if (signalFire) {
			// System.out.println("Injecting on the smoke constructor!");
			this.maxAge += 560;
		}
	}

	@Inject(at = @At("RETURN"), method = "tick()V")
	public void tick(CallbackInfo info) {
		// System.out.println( "Before: " + this.velocityX);
		this.velocityX -= this.velocityX / 170.0D;
		// System.out.println( "After:  " + this.velocityX);
		this.velocityZ -= this.velocityZ / 170.0D;
	}
}