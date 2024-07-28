package me.theclashfruit.pissnshit.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class PissSyncPacket {
    public static final Identifier ID = new Identifier(MOD_ID, "piss_sync");

    public static void register() {}

    public static void sendToClient(ServerPlayerEntity player, int pissLevel) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        new SyncPacket(pissLevel)
            .encode(buf);

        ServerPlayNetworking.send(player, ID, buf);
    }

    public record SyncPacket(int pissLevel) {
        public static SyncPacket decode(PacketByteBuf buf) {
            int pissLevel = buf.readInt();

            return new SyncPacket(pissLevel);
        }

        public void encode(PacketByteBuf buf) {
            buf.writeInt(pissLevel);
        }
    }
}
