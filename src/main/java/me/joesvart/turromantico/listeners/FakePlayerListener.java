package me.joesvart.turromantico.listeners;

import lombok.Getter;
import me.joesvart.turromantico.Turromantico;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class FakePlayerListener implements Listener {

    @Getter
    private final Turromantico plugin;

    public FakePlayerListener(Turromantico plugin) {
        this.plugin = plugin;
    }

    @EventHandler (priority = 64)
    public void onProxyPing(ProxyPingEvent event) {
        final ServerPing response = event.getResponse();
        final ServerPing.Players players = response.getPlayers();

        int onlinePlayers = players.getOnline();
        int maxPlayers = players.getMax();

        if (plugin.getTurromanticoConfiguration().get().getBoolean("BOOLEANS.FAKE-PLAYERS")) {
            onlinePlayers = onlinePlayers + plugin.getTurromanticoConfiguration().get().getInt("FAKE-PLAYERS.AMOUNT");

            players.setOnline(onlinePlayers);
        }
    }
}