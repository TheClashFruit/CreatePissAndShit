package me.theclashfruit.pissnshit.network;

import io.netty.buffer.Unpooled;
import me.theclashfruit.pissnshit.util.PlayerEntityUtil;
import me.theclashfruit.pissnshit.util.ShitManager;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class ShitSyncPacket {
    public static final Identifier ID = new Identifier(MOD_ID, "shit_sync");

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(ShitSyncPacket.ID, (c, handler, buf, responseSender) -> {
            Packet packet = Packet.decode(buf);

            c.execute(() -> {
                if (c.player != null) {
                    ShitManager shitManager = ((PlayerEntityUtil) c.player).getShitManager();

                    shitManager.setShitLevel(packet.shitLevel());
                }
            });
        });
    }

    public static void sendToClient(ServerPlayerEntity player, double shitLevel) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        new Packet(shitLevel)
            .encode(buf);

        ServerPlayNetworking.send(player, ID, buf);
    }

    public record Packet(double shitLevel) {
        public static Packet decode(PacketByteBuf buf) {
            double shitLevel = buf.readDouble();

            return new Packet(shitLevel);
        }

        public void encode(PacketByteBuf buf) {
            buf.writeDouble(shitLevel);
        }
    }
}
