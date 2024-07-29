package me.theclashfruit.pissnshit.network;

import io.netty.buffer.Unpooled;
import me.theclashfruit.pissnshit.util.PlayerEntityUtil;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class PissingPacket {
    public static final Identifier ID = new Identifier(MOD_ID, "pissing");

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ID, (server, player, handler, buffer, responseSender) -> {
            Packet packet = Packet.decode(buffer);

            server.execute(() -> {
                ((PlayerEntityUtil) player).getPissManager().piss(packet.amount(), player);
            });
        });
    }

    public static void sentToServer(int amount) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        new Packet(1)
            .encode(buf);

        ClientPlayNetworking.send(ID, buf);
    }

    public record Packet(int amount) {
        public static Packet decode(PacketByteBuf buf) {
            int amount = buf.readInt();

            return new Packet(amount);
        }

        public void encode(PacketByteBuf buf) {
            buf.writeInt(amount);
        }
    }
}
