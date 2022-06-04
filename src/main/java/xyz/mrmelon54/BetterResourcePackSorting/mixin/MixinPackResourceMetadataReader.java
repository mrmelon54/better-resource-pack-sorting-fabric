package xyz.mrmelon54.BetterResourcePackSorting.mixin;

import com.google.gson.JsonObject;
import net.minecraft.resource.metadata.PackResourceMetadata;
import net.minecraft.resource.metadata.PackResourceMetadataReader;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.mrmelon54.BetterResourcePackSorting.duck.PackResourceCustomNameSetter;

@Mixin(PackResourceMetadataReader.class)
public class MixinPackResourceMetadataReader {
    @Inject(method = "fromJson(Lcom/google/gson/JsonObject;)Lnet/minecraft/resource/metadata/PackResourceMetadata;", at = @At("RETURN"))
    private void injectedFromJson(JsonObject jsonObject, CallbackInfoReturnable<PackResourceMetadata> cir) {
        PackResourceMetadata returnValue = cir.getReturnValue();
        if (jsonObject.has("name") && returnValue instanceof PackResourceCustomNameSetter setter) {
            MutableText text = Text.Serializer.fromJson(jsonObject.get("name"));
            setter.setCustomName(text);
        }
    }
}
