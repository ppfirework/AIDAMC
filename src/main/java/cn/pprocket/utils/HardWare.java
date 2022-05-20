package cn.pprocket.utils;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PhysicalMemory;
import oshi.hardware.common.AbstractBaseboard;

public class HardWare {
    private static SystemInfo si = new SystemInfo();
    private static HardwareAbstractionLayer hal = si.getHardware();
    private static CentralProcessor cpu = hal.getProcessor();
    private static CentralProcessor.ProcessorIdentifier id = cpu.getProcessorIdentifier();

    public static class CPU {
        public static String getCPUName() {
            return id.getName();
        }

        /**
         * 返回CPU个数
         *
         * @return CPU个数
         */
        public static int getCPUNumber() {
            return cpu.getPhysicalPackageCount();
        }

        /**
         * 返回CPU最大频率
         *
         * @return CPU最大频率，单位为Mhz
         */
        public static long getMaxFreq() {
            return cpu.getMaxFreq() / 1000;
        }

        /**
         * 返回CPU核心数
         *
         * @return CPU核心数，不算超线程
         */
        public static int getPhysicalProcessorCount() {
            return cpu.getPhysicalProcessorCount();
        }

        /**
         * 返回CPU原始频率
         *
         * @return CPU原始频率，单位为Mhz
         */
        public static long getVendorFreq() {
            return id.getVendorFreq() / 1000;
        }

        /**
         * 返回CPU架构
         *
         * @return CPU架构
         */
        public static String getMicroArchitecture() {
            return id.getMicroarchitecture();
        }
        public static CentralProcessor getCPU() {
            return cpu;
        }
    }

    /**
     * 返回CPU型号
     *
     * @return CPU型号
     */

    public static class GPU {
        public static String getGPU() {
            return hal.getGraphicsCards().get(00).getName();
        }
    }

    /**
     * 返回主板生产商
     *
     * @return 主板生产商
     */
    public static class MainBoard {
        public static String getBoardProducer() {
            return hal.getComputerSystem().getBaseboard().getManufacturer();
        }

    }

    public static class RAM {
        /**
         * 获取内存总数
         *
         * @return 内存总数，单位为GB
         */
        public static int getTotalRAM() {
            int mem = (int) (hal.getMemory().getTotal() / 1024 / 1024);
            return mem % 1024 == 0 ? mem / 1024 : mem / 1024 + 1;
        }

        /**
         * 获取剩餘内存
         *
         * @return 剩餘内存，单位为GB
         * @return
         */
        public static int getRestMemory() {
            return (int) (hal.getMemory().getAvailable() / 1024 / 1024 / 1024);
        }

        /**
         * @return 返回内存条数
         */
        public static int GetMemoryCount() {
            return hal.getMemory().getPhysicalMemory().size();
        }

        public static long getMemoryMhz() {
            if (GetMemoryCount() > 0) {
                return hal.getMemory().getPhysicalMemory().get(0).getClockSpeed() / 1000 / 1000;
            } else {
                return 0;
            }

        }

        public static String getMemoryProducer() {
            if (GetMemoryCount() > 0) {
                return hal.getMemory().getPhysicalMemory().get(0).getManufacturer();
            } else {
                return "Error";
            }
        }

        /**
         * 判断是否为虚拟机
         *
         * @return boolean值
         */

    }
    public static boolean isVirtualMachine() {
        String name = hal.getComputerSystem().getManufacturer();
        return name.contains("VMware") || name.contains("VirtualBox") || name.contains("Parallels") || name.contains("Xen") || name.contains("QEMU") || name.contains("unknown"); //我也不知道对不对
    }
}


