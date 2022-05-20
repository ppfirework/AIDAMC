package cn.pprocket

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.command.CommandExecutor
import cn.pprocket.command.CommandMain
import org.bukkit.Bukkit

class aidamc : JavaPlugin() {
    var INSTANCEs: aidamc? = null
    override fun onEnable() {
        Metrics(this, 15068)
        val commandExecutor: CommandExecutor = CommandMain()
        Bukkit.getPluginCommand("aida").executor = commandExecutor
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}