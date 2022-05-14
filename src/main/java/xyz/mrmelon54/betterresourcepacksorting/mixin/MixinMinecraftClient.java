package xyz.mrmelon54.betterresourcepacksorting.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.metadata.PackResourceMetadata;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.mrmelon54.betterresourcepacksorting.duck.PackResourceCustomNameGetter;
import xyz.mrmelon54.betterresourcepacksorting.duck.PackResourceCustomNameSetter;

import java.util.function.Supplier;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {
    @Inject(method = "createResourcePackProfile", at = @At("RETURN"))
    private static void injectedCreateResourcePackProfile(String name, Text displayName, boolean alwaysEnabled, Supplier<ResourcePack> packFactory, PackResourceMetadata metadata, ResourcePackProfile.InsertionPosition insertionPosition, ResourcePackSource source, CallbackInfoReturnable<ResourcePackProfile> cir) {
        if (metadata instanceof PackResourceCustomNameGetter getter) {
            Text customName = getter.getCustomName();
            ResourcePackProfile returnValue = cir.getReturnValue();
            if (returnValue instanceof PackResourceCustomNameSetter setter)
                setter.setCustomName(customName);
        }
    }
}
