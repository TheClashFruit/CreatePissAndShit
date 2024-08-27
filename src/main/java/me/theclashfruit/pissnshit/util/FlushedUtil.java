package me.theclashfruit.pissnshit.util;

import me.theclashfruit.pissnshit.registry.Items;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

import java.util.regex.Pattern;

public class FlushedUtil {
    private static boolean isEnabled = false;

    public static void tick() {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null) {
            isEnabled = false;

            return;
        }

        isEnabled = client.player.getInventory().contains(
            new ItemStack(Items.FLUSHED)
        );
    }

    public static String flushify(String input) {
        input = Pattern.compile("([A-Za-zÀ-ÖØ-öø-ÿőű]+)").matcher(input).replaceAll(m -> {
            String group = m.group();

            return "😳".repeat(group.length());
        });

        return input;
    }

    public static boolean isEnabled() {
        return isEnabled;
    }
}
