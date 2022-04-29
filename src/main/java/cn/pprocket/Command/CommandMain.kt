package cn.pprocket.command

import cn.pprocket.utils.NetWork
import cn.pprocket.utils.HardWare
import cn.pprocket.utils.OS
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import oshi.SystemInfo

class CommandMain: CommandExecutor {
    private var str = ""
    private fun DiskInfo(): String {
        try {
            val list = SystemInfo().hardware.diskStores
            var result = ""
            for (disk in list) {
                result += ChatColor.GREEN.toString() + disk.model + ": " + disk.size / 1024 / 1024 / 1024 + "GB" + "\n"
                return result
            }
        } catch (e: ExceptionInInitializerError) {
            return "未知"
        }



        return ""
    }

    private fun getMsg(): String {
        val startTime = System.currentTimeMillis()
        var str = """
                    ${ChatColor.GREEN}
                    ------------------CPU------------------
                    CPU型号: ${HardWare.CPU.getCPUName()}
                    CPU核心数: ${HardWare.CPU.getPhysicalProcessorCount()}
                    
                    ------------------内存------------------
                    内存总大小: ${HardWare.RAM.getTotalRAM()}GB
                    内存剩余大小: ${HardWare.RAM.getRestMemory()}GB
                    内存频率: ${HardWare.RAM.getMemoryMhz()}MHz
                    内存生产商：${HardWare.RAM.getMemoryProducer()}
                    
                    ------------------系统------------------
                    系统平台：${OS.getVersion()}
                    进程数：${OS.ProcessCount()}
                    Java进程数：${OS.JavaProcessCount()}
                    是否为虚拟机：${HardWare.isVirtualMachine()}
                    ${if (HardWare.isVirtualMachine()) "虚拟机类型：${HardWare.MainBoard.getBoardProducer()}" else "主板生产商：${HardWare.MainBoard.getBoardProducer()}"}
                    是否为root：${OS.isRoot()}
                    服务端启动参数：${OS.getCommand()}
                    
                    ------------------网络------------------
                    IP地址：${NetWork.getIP()}
                    归属地：${NetWork.getLocation()}
                    
                    ------------------磁盘------------------
                    
                """.trimIndent()
        str += DiskInfo()
        str += """
                    
                    
                    耗时：${System.currentTimeMillis() - startTime}ms
                """.trimIndent()
        return str

    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.isOp) {
            sender.sendMessage("${ChatColor.RED}您没有使用此命令的权限")
            return true
        } else {
            if (args.isEmpty()) {
                val t = Thread {
                    sender.sendMessage(getMsg())
                }
                t.start()
            } else if (args[0] == "shell") {
                Thread {
                    sender.sendMessage(OS.run(getArgs(args.toList())))
                }.start()
            } else {
                sender.sendMessage("${ChatColor.RED}无效的参数")
            }

        }

        return true
    }
    private fun getArgs(args: List<String>): String {
        var str = ""
        for (arg in args) {
            if (args[0] == arg) {
                continue
            }
            str += "$arg "
        }
        return str
    }
}


