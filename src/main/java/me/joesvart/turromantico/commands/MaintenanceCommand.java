package me.joesvart.turromantico.commands;

import me.joesvart.turromantico.Turromantico;
import me.joesvart.turromantico.utils.ColorHelper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class MaintenanceCommand extends Command {

    private final Turromantico plugin;

    public MaintenanceCommand(Turromantico plugin) {
        super("maintenance", "turromantico.admin");

        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("");
            sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + ChatColor.UNDERLINE + "Maintenance");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.DARK_GRAY + "▸ " + ChatColor.YELLOW + "/maintenance on " + ChatColor.GRAY + "Activate the maintenance");
            sender.sendMessage(ChatColor.DARK_GRAY + "▸ " + ChatColor.YELLOW + "/maintenance off " + ChatColor.GRAY + "Deactivate the maintenance");
            sender.sendMessage("");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "on":
                plugin.getMainConfig().get().set("BOOLEANS.MAINTENANCE", true);
                sender.sendMessage(ColorHelper.translate(plugin.getMainConfig().get().getString("MAINTENANCE.MAINTENANCE-ON")));
                plugin.getMainConfig().save();
                break;
            case "off":
                plugin.getMainConfig().get().set("BOOLEANS.MAINTENANCE", false);
                sender.sendMessage(ColorHelper.translate(plugin.getMainConfig().get().getString("MAINTENANCE.MAINTENANCE-OFF")));
                plugin.getMainConfig().save();
                break;
        }
    }
}
