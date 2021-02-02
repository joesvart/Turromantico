package me.joesvart.turromantico.commands;

import me.joesvart.turromantico.Turromantico;
import me.joesvart.turromantico.utils.ColorHelper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class MOTDCommand extends Command {

    private final Turromantico plugin;

    public MOTDCommand(Turromantico plugin) {
        super("motd", "turromantico.admin");

        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("");
            sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + ChatColor.UNDERLINE + "MOTD");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.DARK_GRAY + "▸ " + ChatColor.YELLOW + "/motd reload " + ChatColor.GRAY + "Reload the config");
            sender.sendMessage("");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                plugin.getMainConfig().reload();
                sender.sendMessage(ColorHelper.translate(plugin.getMainConfig().get().getString("MESSAGES.CONFIG-RELOADED")));
                break;
        }
    }
}
