package cn.pprocket;

import cn.pprocket.command.CommandMain;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;


public final class aidamc extends JavaPlugin {

    @Override
    public void onEnable() {
        new Metrics(this,15068);
        CommandExecutor commandExecutor = new CommandMain();
        Bukkit.getPluginCommand("aida").setExecutor(commandExecutor);

    }

    public static void main(String[] args) {

        String str = "submitCover\": \" https://luluossimg.lulufind.com/work/student_CreateFWfgvfcpfqhteznN1DMtdrc7vdsPNr_1651060300123_36315983|https://luluossimg.lulufind.com/work/student_CreateFWfgvfcpfqhteznN1DMtdrc7vdsPNr_1651060300123_18895840|https://luluossimg.lulufind.com/work/student_CreateFWfgvfcpfqhteznN1DMtdrc7vdsPNr_1651060300123_94841122|https://luluossimg.lulufind.com/work/student_CreateFWfgvfcpfqhteznN1DMtdrc7vdsPNr_1651060300123_46436840";
        String[] split = str.split("\\|");
        for (int i = 0;i<split.length;i++){
            System.out.println(split[i]);
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
