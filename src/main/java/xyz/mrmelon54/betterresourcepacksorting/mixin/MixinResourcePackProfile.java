package xyz.mrmelon54.betterresourcepacksorting.mixin;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.mrmelon54.betterresourcepacksorting.duck.PackResourceCustomNameSetter;

@Mixin(ResourcePackProfile.class)
public class MixinResourcePackProfile implements PackResourceCustomNameSetter {
    private Text customName;

    @Override
    public void setCustomName(Text name) {
        customName = name;
    }

    @Inject(method = "getDisplayName", at = @At("RETURN"), cancellable = true)
    private void injectedGetDisplayName(CallbackInfoReturnable<Text> cir) {
        if (customName != null) cir.setReturnValue(customName);
    }
}
