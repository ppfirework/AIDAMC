package cn.pprocket.gui

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class GUI {
    fun openGUI(p: Player) {
        var inv = Bukkit.createInventory(null, 9, "§6§AIDAMC")
        var CPU  = ItemStack(Material.WOOL)
    }
}