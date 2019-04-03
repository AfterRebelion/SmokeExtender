package net.smokeextender.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(net.minecraft.client.particle.CampfireSmokeParticle.class)
public abstract class SmokeExtenderMixin extends SpriteBillboardParticle {
	public SmokeExtenderMixin(World world_1, double double_1, double double_2, double double_3, double double_4, double double_5, double double_6) {
		super(world_1, double_1, double_2, double_3, double_4, double_5, double_6);
	}

	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/world/World;DDDDDDZ)V")
	private void CampfireSmokeParticle (World world_1, double double_1, double double_2, double double_3, double double_4, double double_5, double double_6, boolean boolean_1, CallbackInfo info) {
		if (boolean_1) {
			// System.out.println("Injecting on the smoke constructor!");
			this.maxAge += 560;
		}
	}

	@Inject(at = @At("RETURN"), method = "update()V")
	public void update(CallbackInfo info) {
		// System.out.println( "Before: " + this.velocityX);
		this.velocityX -= this.velocityX / 170.0D;
		// System.out.println( "After:  " + this.velocityX);
		this.velocityZ -= this.velocityZ / 170.0D;
	}
}