package xyz.mrmelon54.BetterResourcePackSorting.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.pack.PackListWidget;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameGetter;

@Mixin(PackListWidget.ResourcePackEntry.class)
public abstract class MixinPackListWidget_ResourcePackEntry {
    private boolean hovered;
    @Shadow
    @Final
    private ResourcePackOrganizer.Pack pack;

    @Shadow
    private static OrderedText trimTextToWidth(MinecraftClient client, Text text) {
        return null;
    }

    @Shadow
    @Final
    protected MinecraftClient client;

    @Shadow
    protected abstract boolean isSelectable();

    @Inject(method = "render", at = @At("HEAD"))
    private void injectedRender(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta, CallbackInfo ci) {
        this.hovered = this.isSelectable() && (this.client.options.touchscreen || hovered);
    }

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/pack/PackListWidget$ResourcePackEntry;displayName:Lnet/minecraft/text/OrderedText;"))
    private OrderedText redirectDisplayName(PackListWidget.ResourcePackEntry instance) {
        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), InputUtil.GLFW_KEY_LEFT_ALT) && hovered)
            return trimTextToWidth(client, pack.getDisplayName());
        if (this.pack instanceof PackResourceCustomNameGetter getter) {
            Text customName = getter.getCustomName();
            if (customName != null) return trimTextToWidth(client, customName);
        }
        return trimTextToWidth(client, pack.getDisplayName());
    }
}
