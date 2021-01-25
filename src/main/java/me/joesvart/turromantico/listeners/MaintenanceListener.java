package me.joesvart.turromantico.listeners;

import lombok.Getter;
import me.joesvart.turromantico.Turromantico;
import me.joesvart.turromantico.utils.ColorHelper;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MaintenanceListener implements Listener {

    @Getter
    private final Turromantico plugin;

    public MaintenanceListener(Turromantico plugin) {
        this.plugin = plugin;

        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        if (!plugin.getTurromanticoConfiguration().get().getBoolean("BOOLEANS.MAINTENANCE")) return;

        ServerPing response = event.getResponse();
        ServerPing.Protocol protocol = new ServerPing.Protocol(plugin.getTurromanticoConfiguration().get().getString("MAINTENANCE.MAINTENANCE-SERVER-PING"), response.getVersion().getProtocol() - 1);

        response.setVersion(protocol);
        event.setResponse(response);
    }

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        if (!plugin.getTurromanticoConfiguration().get().getBoolean("BOOLEANS.MAINTENANCE")) return;

        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(event.getConnection().getUniqueId());

        if(player == null) return;
        if(player.hasPermission("turromantico.admin")) return;

        event.setCancelReason(ColorHelper.translate(plugin.getTurromanticoConfiguration().get().getString("MAINTENANCE.MAINTENANCE-KICK")));
        event.setCancelled(true);
    }
}
