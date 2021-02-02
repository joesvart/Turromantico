package me.joesvart.turromantico.listeners;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.joesvart.turromantico.Turromantico;
import me.joesvart.turromantico.utils.ColorHelper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public class MOTDListener implements Listener {

    @Getter
    private final Turromantico plugin;

    @EventHandler
    public void onProxyMessagePing(ProxyPingEvent event) {
        Configuration config = plugin.getTurromanticoConfiguration().get();
        List<String> serverPing = config.getStringList(ColorHelper.translate("MOTD-SERVER-PING"));

        /**
         * Create a new array with the line count
         */
        ServerPing.PlayerInfo[] motdPing = new ServerPing.PlayerInfo[serverPing.size()];

        for (int i = 0; i < motdPing.length; i++) {
            /**
             * Create a player with an empty ID and the line as name and add them to the array
             */
            motdPing[i] = new ServerPing.PlayerInfo(serverPing.get(i), "");
        }

        /**
         * This set the player
         * response.
         */
        if (plugin.getTurromanticoConfiguration().get().getBoolean("BOOLEANS.MOTD-SERVER-PING")) {
            event.getResponse().getPlayers().setSample(motdPing);
        }
    }

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        ServerPing response = event.getResponse();

        /**
         * Set the MOTD and replace
         * the new line for <new-line>
         * variable / placeholder.
         */
        if (plugin.getTurromanticoConfiguration().get().getBoolean("BOOLEANS.MOTD")) {
            response.setDescriptionComponent(replace());
        }

        /**
         * This set the player
         * response.
         */
        event.setResponse(response);
    }

    private TextComponent replace() {
        Configuration config = plugin.getTurromanticoConfiguration().get();
        ThreadLocalRandom thread = ThreadLocalRandom.current();

        /**
         * Get the MOTD string list
         * for the messages in
         * ProxyPingEvent.
          */
        List<String> motds = config.getStringList("MOTDS");

        /**
         * Get the active MOTD
         * in the list.
         */
        String string = motds.get(thread.nextInt(0, motds.size()));

        /**
         * Create a new Variable or
         * placeholder for a new line
         * with the placeholder of <new-line>.
         */
        string = string.replace("<new-line>", "\n");
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', string));
    }
}
