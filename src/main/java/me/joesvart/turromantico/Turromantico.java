package me.joesvart.turromantico;

import me.joesvart.turromantico.commands.HubCommand;
import me.joesvart.turromantico.commands.MOTDCommand;
import me.joesvart.turromantico.commands.MaintenanceCommand;
import me.joesvart.turromantico.listeners.MOTDListener;
import lombok.Getter;
import lombok.Setter;
import me.joesvart.turromantico.listeners.MaintenanceListener;
import me.joesvart.turromantico.utils.ConfigHelper;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

@Getter @Setter
public class Turromantico extends Plugin {

    @Getter
    private static Turromantico instance;

    @Getter
    private static Turromantico plugin;

    private ConfigHelper turromanticoConfiguration;

    @Override
    public void onEnable() {
        instance = this;
        plugin = this;

        /**
         * Register the Configs
         */
        turromanticoConfiguration = new ConfigHelper(this, "config.yml", true);

        /**
         * Register the commands
         * and the listeners
         */
        registerCommands();
        registerListeners();
    }

    public void registerCommands() {
        PluginManager pluginManager = getProxy().getPluginManager();

        pluginManager.registerCommand(this, new MaintenanceCommand(this));
        pluginManager.registerCommand(this, new MOTDCommand(this));
        pluginManager.registerCommand(this, new HubCommand(this));
    }

    public void registerListeners() {
        PluginManager pluginManager = getProxy().getPluginManager();

        pluginManager.registerListener(this, new MaintenanceListener(this));
        pluginManager.registerListener(this, new MOTDListener(this));
    }

    @Override
    public void onDisable() {
        /**
         * Save the config
         */
        turromanticoConfiguration.save();
    }
}
