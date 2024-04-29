package me.xlonx.xlonxwelcomequitmsg;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        reloadConfig();

        Bukkit.getPluginManager().addPermission(new Permission("xlonx-join-quit.reload"));

        getLogger().info("Loaded");

        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("joinquit-reload").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Stopped");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(Colorize.colorize(getConfig().getString("join_message").replace("{PLAYER}", e.getPlayer().getDisplayName())));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(Colorize.colorize(getConfig().getString("quit_message").replace("{PLAYER}", e.getPlayer().getDisplayName())));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("xlonx-join-quit.reload")) {
            reloadConfig();
            sender.sendMessage(Colorize.colorize(getConfig().getString("reload_message")));
        }
        else {
            sender.sendMessage(Colorize.colorize(getConfig().getString("no_permission")));
            return false;
        }
        return super.onCommand(sender, command, label, args);
    }
}
