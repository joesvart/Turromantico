package me.joesvart.turromantico.listeners;

import lombok.RequiredArgsConstructor;
import me.joesvart.turromantico.Turromantico;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class MOTDListener implements Listener {

    private final Turromantico plugin;

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        ServerPing response = event.getResponse();

        response.setDescriptionComponent(parseMotd());
        event.setResponse(response);
    }

    private TextComponent parseMotd() {
        Configuration config = plugin.getTurromanticoConfiguration().get();
        ThreadLocalRandom tlr = ThreadLocalRandom.current();

        List<String> motds = config.getStringList("MOTDS");

        /**
         * Get the actual MOTD
         */
        String string = motds.get(tlr.nextInt(0, motds.size()));
        /**
         * Parsing variables/placeholders
         */
        string = string.replace("\\n", "\n");
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', string));
    }
}
