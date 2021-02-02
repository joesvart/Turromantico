package me.joesvart.turromantico.listeners;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.joesvart.turromantico.Turromantico;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@AllArgsConstructor
public class FakePlayerListener implements Listener {

    @Getter
    private final Turromantico plugin;

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        ServerPing response = event.getResponse();
        ServerPing.Players players = response.getPlayers();

        int onlinePlayers = players.getOnline();
        int maxPlayers = players.getMax();

        /**
         * Set the fake amount of
         * players getting of
         * the configuration.
         */
        if (plugin.getMainConfig().get().getBoolean("BOOLEANS.FAKE-PLAYERS")) {
            onlinePlayers = onlinePlayers + plugin.getMainConfig().get().getInt("FAKE-PLAYERS.AMOUNT");

            players.setOnline(onlinePlayers);
        }
    }
}