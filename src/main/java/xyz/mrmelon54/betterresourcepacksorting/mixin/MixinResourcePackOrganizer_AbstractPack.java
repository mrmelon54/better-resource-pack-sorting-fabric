package xyz.mrmelon54.betterresourcepacksorting.mixin;

import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.mrmelon54.betterresourcepacksorting.duck.PackResourceCustomNameGetter;

@Mixin(targets = "net/minecraft/client/gui/screen/pack/ResourcePackOrganizer$AbstractPack")
public class MixinResourcePackOrganizer_AbstractPack implements PackResourceCustomNameGetter {
    private Text customName;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectedInit(ResourcePackOrganizer resourcePackOrganizer, ResourcePackProfile profile, CallbackInfo ci) {
        if (profile instanceof PackResourceCustomNameGetter getter) {
            customName = getter.getCustomName();
        }
    }

    @Override
    public Text getCustomName() {
        return customName;
    }
}
