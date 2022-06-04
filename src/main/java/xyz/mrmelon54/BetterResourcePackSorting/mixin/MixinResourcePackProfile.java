package xyz.mrmelon54.BetterResourcePackSorting.mixin;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import xyz.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameGetter;
import xyz.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameSetter;

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
