package cn.pprocket.Command

import cn.pprocket.lib.HardWare
import cn.pprocket.lib.Network
import cn.pprocket.lib.OS
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.ChatColor
import oshi.SystemInfo

class CommandMain{
     private var str = ""
    private fun DiskInfo(): String {
        val list = SystemInfo().hardware.diskStores
        var result = ""
        for (disk in list){
            result+=ChatColor.GREEN.toString() + disk.model + ": " + disk.size/1024/1024/1024 + "GB" + "\n"
        }
        return result
    }
     fun onCall(){
         val sub = CommandAPICommand("shell")
             .withPermission(CommandPermission.OP)
             .withArguments(GreedyStringArgument("command"))
             .executes(CommandExecutor{sender,args ->
                 val t = Thread(Runnable{
                     val str = OS.run(args[0] as String?)
                     sender.sendMessage(str)
                 })
                 t.start()
             })
         CommandAPICommand("aida")
             .withPermission(CommandPermission.OP)
             .executes(CommandExecutor { player, _ ->
                     val t = Thread {
                         player.sendMessage(getMsg())
                     }
                     t.start()

             })
             .withSubcommand(sub)
             .register()
     }
     private fun  getMsg (): String {
         val startTime = System.currentTimeMillis()
         var str = """
                    ${ChatColor.GREEN}
                    ------------------CPU------------------
                    CPU型号: ${HardWare.CPU.getCPUName()}
                    CPU核心数: ${HardWare.CPU.getPhysicalProcessorCount()}
                    
                    ------------------内存------------------
                    内存总大小: ${HardWare.RAM.getTotalRAM()}GB
                    内存剩余大小: ${HardWare.RAM.getRestMemory()}GB
                    
                    ------------------系统------------------
                    系统平台：${OS.getVersion()}
                    进程数：${OS.ProcessCount()}
                    Java进程数：${OS.JavaProcessCount()}
                    是否为虚拟机：${HardWare.isVirtualMachine()}
                    ${if (HardWare.isVirtualMachine()) "虚拟机类型：${HardWare.MainBoard.getBoardProducer()}" else "主板生产商：${HardWare.MainBoard.getBoardProducer()}"}
                    是否为root：${OS.isRoot()}
                    
                    ------------------网络------------------
                    IP地址：${Network.getIP().ip}
                    归属地：${Network.getIP().city}
                    
                    ------------------磁盘------------------
                    
                """.trimIndent()
         str+=DiskInfo()
         str+="""
                    
                    
                    耗时：${System.currentTimeMillis() - startTime}ms
                """.trimIndent()
         return str

     }
 }