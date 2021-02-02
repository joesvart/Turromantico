package me.joesvart.turromantico;

import me.joesvart.turromantico.commands.HubCommand;
import me.joesvart.turromantico.commands.MOTDCommand;
import me.joesvart.turromantico.commands.MaintenanceCommand;
import me.joesvart.turromantico.listeners.FakePlayerListener;
import me.joesvart.turromantico.listeners.MOTDListener;
import lombok.Getter;
import lombok.Setter;
import me.joesvart.turromantico.listeners.MaintenanceListener;
import me.joesvart.turromantico.listeners.PlayersListener;
import me.joesvart.turromantico.utils.ConfigHelper;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

@Getter @Setter
public class Turromantico extends Plugin {

    @Getter
    private static Turromantico plugin;

    private ConfigHelper turromanticoConfiguration;

    @Override
    public void onEnable() {
        plugin = this;

        /**
         * Register the
         * Configurations.
         */
        turromanticoConfiguration = new ConfigHelper(this, "config.yml", true);

        /**
         * Register the commands
         * and the listeners.
         */
        registerCommands();
        registerListeners();
    }

    public void registerCommands() {
        /**
         * Plugin manager
         * object for register
         * more easy the commands
         * and the listeners.
         */
        PluginManager pluginManager = getProxy().getPluginManager();

        /**
         * Register the commands.
         */
        pluginManager.registerCommand(this, new MaintenanceCommand(this));
        pluginManager.registerCommand(this, new MOTDCommand(this));
        pluginManager.registerCommand(this, new HubCommand(this));
    }

    public void registerListeners() {
        /**
         * Plugin manager
         * object for register
         * more easy the commands
         * and the listeners.
         */
        PluginManager pluginManager = getProxy().getPluginManager();

        /**
         * Register the listeners.
         */
        pluginManager.registerListener(this, new FakePlayerListener(this));
        pluginManager.registerListener(this, new MaintenanceListener(this));
        pluginManager.registerListener(this, new MOTDListener(this));
        pluginManager.registerListener(this, new PlayersListener(this));
    }

    @Override
    public void onDisable() {
        /**
         * Save the config
         */
        turromanticoConfiguration.save();
    }
}
