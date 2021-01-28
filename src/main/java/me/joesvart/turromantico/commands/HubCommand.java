package me.joesvart.turromantico.commands;

import me.joesvart.turromantico.Turromantico;
import me.joesvart.turromantico.utils.ColorHelper;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command {

    private final Turromantico plugin;

    public HubCommand(Turromantico plugin) {
        super("hub");

        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (!player.getServer().getInfo().getName().equals(plugin.getTurromanticoConfiguration().get().getString("HUB.HUB-SERVER"))) {
                player.sendMessage(ColorHelper.translate(plugin.getTurromanticoConfiguration().get().getString("HUB.SEND-HUB")));

                ServerInfo target = ProxyServer.getInstance().getServerInfo(plugin.getTurromanticoConfiguration().get().getString("HUB.HUB-SERVER"));

                player.connect(target);
            } else {
                player.sendMessage(ColorHelper.translate(plugin.getTurromanticoConfiguration().get().getString("HUB.ALREADY-CONNECTED")));
            }
        }
    }
}
