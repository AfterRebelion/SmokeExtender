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
	SmokeExtenderMixin(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
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
	private void tick(CallbackInfo info) {
		if (this.age >= 180) {
			// System.out.println( "Before: " + this.velocityX);
			this.velocityX /= 25.0D;
			// System.out.println( "After:  " + this.velocityX);
			this.velocityZ /= 25.0D;
			if (this.age >= this.maxAge - 600 && this.age < this.maxAge - 60 && this.alpha > 0.05F) {
				this.alpha -= 0.002F;
			}
		}
	}
}