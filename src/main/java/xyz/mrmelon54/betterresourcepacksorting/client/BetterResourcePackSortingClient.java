package xyz.mrmelon54.betterresourcepacksorting.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.Locale;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class BetterResourcePackSortingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

    }

    public static String getTextAsSortable(Text a) {
        Optional<String> o = a.getWithStyle(Style.EMPTY).stream().map(text -> {
            String s = text.asString().toUpperCase(Locale.ROOT);
            return removeFormattingCodes(s);
        }).reduce((s, s2) -> s + s2);
        return o.orElse("");
    }

    public static String removeFormattingCodes(String a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '§') {
                i++;
                continue;
            }
            sb.append(a.charAt(i));
        }
        return sb.toString();
    }
}
