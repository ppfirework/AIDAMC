package cn.pprocket;

import cn.hutool.http.HttpUtil;
import cn.pprocket.command.CommandMain;
import cn.pprocket.utils.DependencyLoader;
import cn.pprocket.utils.OS;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.Locale;

public class aidamc extends JavaPlugin {
    public static aidamc INSTANCE = null;
    public  File f = new File(this.getDataFolder(),"speedtest");
    @Override
    public void onEnable() {
        DependencyLoader.loadLibs();
        INSTANCE = this;
        new Metrics(this, 15068);
        Bukkit.getPluginCommand("aida").setExecutor(new CommandMain());
        Bukkit.getScheduler().runTaskAsynchronously(this, this::downloadSpeedTest);
    }
    void downloadSpeedTest() {
        String win = "https://pan.tenire.com/down.php/90e29c4098418f00a7e45202be3bedd6.exe";
        String lin = "https://pan.tenire.com/down.php/b04c1b6fdc66980f5e57ff63c1d6b5a8";
        String os = OS.getVersion();
        if (!f.exists()) {
            if (os.toLowerCase(Locale.ROOT).contains("win")) {
                HttpUtil.downloadFile(win,f);
            }  else {
                HttpUtil.downloadFile(lin,f);
            }
        }
    }
}