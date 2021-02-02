package me.joesvart.turromantico.listeners;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.joesvart.turromantico.Turromantico;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@AllArgsConstructor
public class PlayersListener implements Listener {

    @Getter
    private final Turromantico plugin;

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        final ServerPing response = event.getResponse();
        final ServerPing.Players players = response.getPlayers();

        int onlinePlayers = players.getOnline();
        int maxPlayers = players.getMax();

        /**
         * Max players
         * option.
         */
        if (plugin.getTurromanticoConfiguration().get().getBoolean("BOOLEANS.MAX-PLAYERS")) {
            maxPlayers = plugin.getTurromanticoConfiguration().get().getInt("PLAYERS.MAX-PLAYERS");

            players.setMax(maxPlayers);
        }

        /**
         * Just one more
         * option.
         */
        if (plugin.getTurromanticoConfiguration().get().getBoolean("BOOLEANS.JUST-ONE-MORE")) {
            maxPlayers = onlinePlayers + 1;

            players.setMax(maxPlayers);
        }
    }
}