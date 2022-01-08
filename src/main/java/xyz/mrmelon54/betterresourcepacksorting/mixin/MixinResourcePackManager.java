package xyz.mrmelon54.betterresourcepacksorting.mixin;

import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.mrmelon54.betterresourcepacksorting.client.BetterResourcePackSortingClient;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(ResourcePackManager.class)
public class MixinResourcePackManager {
    @Shadow
    private Map<String, ResourcePackProfile> profiles;

    @Inject(method = "getProfiles", at = @At("RETURN"), cancellable = true)
    private void redirectedCopyOf(CallbackInfoReturnable<Collection<ResourcePackProfile>> cir) {
        Collection<ResourcePackProfile> values = profiles.values();
        Stream<ResourcePackProfile> sorted = values.stream().sorted((o1, o2) -> {
            String s1 = BetterResourcePackSortingClient.getTextAsSortable(o1.getDisplayName());
            String s2 = BetterResourcePackSortingClient.getTextAsSortable(o2.getDisplayName());
            return s1.compareTo(s2);
        });
        cir.setReturnValue(sorted.collect(Collectors.toList()));
        cir.cancel();
    }
}
