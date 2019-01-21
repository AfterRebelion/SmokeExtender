package net.smokeextender.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_3937;

import net.minecraft.class_3940;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(net.minecraft.class_3937.class)
public abstract class SmokeExtenderMixin extends class_3940 {
	public SmokeExtenderMixin(World world_1, double double_1, double double_2, double double_3, double double_4, double double_5, double double_6) {
		super(world_1, double_1, double_2, double_3, double_4, double_5, double_6);
	}

	@Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/world/World;DDDDDDZ)V")
	private void class_3937(World world_1, double double_1, double double_2, double double_3, double double_4, double double_5, double double_6, boolean boolean_1, CallbackInfo info) {
		if (boolean_1) {
			// System.out.println("Injecting on the smoke constructor!");
			this.maxAge += 560;
		}
	}
}