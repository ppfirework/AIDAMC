package cn.pprocket

import cn.pprocket.command.CommandMain
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class aidamc : JavaPlugin() {

    override fun onEnable() {
        Metrics(this, 15068)
        Bukkit.getPluginCommand("aida")?.setExecutor(CommandMain())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}