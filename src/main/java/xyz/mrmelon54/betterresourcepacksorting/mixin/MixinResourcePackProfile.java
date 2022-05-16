package xyz.mrmelon54.betterresourcepacksorting.mixin;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import xyz.mrmelon54.betterresourcepacksorting.duck.PackResourceCustomNameGetter;
import xyz.mrmelon54.betterresourcepacksorting.duck.PackResourceCustomNameSetter;

@Mixin(ResourcePackProfile.class)
public class MixinResourcePackProfile implements PackResourceCustomNameSetter, PackResourceCustomNameGetter {
    private Text customName;

    @Override
    public void setCustomName(Text name) {
        customName = name;
    }

    @Override
    public Text getCustomName() {
        return customName;
    }
}
