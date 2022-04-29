package cn.pprocket.utils;

import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

public class OS {
    private static OperatingSystem os = new SystemInfo().getOperatingSystem();
    public static String run(String cmd ) throws Exception {
        java.lang.Process process = null;
        ByteArrayOutputStream resultOutStream = new ByteArrayOutputStream();
        try {
            process = Runtime.getRuntime().exec(cmd);
            InputStream errorInStream = new BufferedInputStream(process.getErrorStream());
            InputStream processInStream = new BufferedInputStream(process.getInputStream());
            int num = 0;
            byte[] bs = new byte[1024];
            while ((num = errorInStream.read(bs)) != -1) {
                resultOutStream.write(bs, 0, num);
            }
            while ((num = processInStream.read(bs)) != -1) {
                resultOutStream.write(bs, 0, num);
            }
            errorInStream.close();
            processInStream.close();
            resultOutStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (process != null) process.destroy();
        }
        String charset;
        OperatingSystem os = new SystemInfo().getOperatingSystem();
        if(os.getFamily() == "Windows"){
            charset = "GBK";
        } else {
            charset = "UTF-8";
        }
        return resultOutStream.toString(charset);

    }
    public static String getVersion(){
        OperatingSystem os = new SystemInfo().getOperatingSystem();
        return os.getFamily() + os.getVersionInfo();
    }
    public static String getUser() {
        return os.getSessions().get(0).getUserName();
    }
    public static boolean isRoot(){
        return os.isElevated();
    }
    public static int JavaProcessCount(){
        int count = 0;
        List<OSProcess> list = os.getProcesses();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("java")) {
                count++;
            }
        }
        return count;
    }
    public static int ProcessCount(){
        return os.getProcessCount();
    }

    /**
     *
     * @return 获取当前进程的命令行参数
     */
    public static String getCommand(){
        return os.getProcess(os.getProcessId()).getCommandLine();
    }

}


