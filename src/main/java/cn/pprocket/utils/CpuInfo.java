package cn.pprocket.utils;


//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.text.DecimalFormat;

import oshi.hardware.CentralProcessor;

public class CPUInfo {
    private static final DecimalFormat LOAD_FORMAT = new DecimalFormat("#.00");
    private Integer cpuNum;
    private double toTal;
    private double sys;
    private double user;
    private double wait;
    private double free;
    private String cpuModel;
    private CPUTicks ticks;

    public CPUInfo() {
    }

    public CPUInfo(CentralProcessor processor, long waitingTime) {
        this.init(processor, waitingTime);
    }

    public CPUInfo(Integer cpuNum, double toTal, double sys, double user, double wait, double free, String cpuModel) {
        this.cpuNum = cpuNum;
        this.toTal = toTal;
        this.sys = sys;
        this.user = user;
        this.wait = wait;
        this.free = free;
        this.cpuModel = cpuModel;
    }

    public Integer getCpuNum() {
        return this.cpuNum;
    }

    public void setCpuNum(Integer cpuNum) {
        this.cpuNum = cpuNum;
    }

    public double getToTal() {
        return this.toTal;
    }

    public void setToTal(double toTal) {
        this.toTal = toTal;
    }

    public double getSys() {
        return this.sys;
    }

    public void setSys(double sys) {
        this.sys = sys;
    }

    public double getUser() {
        return this.user;
    }

    public void setUser(double user) {
        this.user = user;
    }

    public double getWait() {
        return this.wait;
    }

    public void setWait(double wait) {
        this.wait = wait;
    }

    public double getFree() {
        return this.free;
    }

    public void setFree(double free) {
        this.free = free;
    }

    public String getCpuModel() {
        return this.cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public CPUTicks getTicks() {
        return this.ticks;
    }

    public void setTicks(CPUTicks ticks) {
        this.ticks = ticks;
    }

    public double getUsed() {
        return 100D - free;
    }

    public String toString() {
        return "CpuInfo{CPU?????????=" + this.cpuNum + ", CPU???????????????=" + this.toTal + ", CPU???????????????=" + this.sys + ", CPU???????????????=" + this.user + ", CPU???????????????=" + this.wait + ", CPU???????????????=" + this.free + ", CPU?????????=" + this.getUsed() + ", CPU????????????='" + this.cpuModel + '\'' + '}';
    }

    private void init(CentralProcessor processor, long waitingTime) {
       CPUTicks ticks = new CPUTicks(processor, waitingTime);
        this.ticks = ticks;
        this.cpuNum = processor.getLogicalProcessorCount();
        this.cpuModel = processor.toString();
        long totalCpu = ticks.totalCpu();
        this.toTal = (double)totalCpu;
        this.sys = formatDouble(ticks.cSys, totalCpu);
        this.user = formatDouble(ticks.user, totalCpu);
        this.wait = formatDouble(ticks.ioWait, totalCpu);
        this.free = formatDouble(ticks.idle, totalCpu);
    }

    private static double formatDouble(long tick, long totalCpu) {
        return 0L == totalCpu ? 0.0D : Double.parseDouble(LOAD_FORMAT.format(tick <= 0L ? 0.0D : 100.0D * (double)tick / (double)totalCpu));
    }
}
