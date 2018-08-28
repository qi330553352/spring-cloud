package com.example.qixin.design.facade;

/**
 * 创 建  时 间： 2018/8/28 21:25
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class Computer {

    private CPU cpu;
    private Memory menory;
    private Disk disk;

    public Computer() {
        cpu = new CPU();
        menory = new Memory();
        disk = new Disk();
    }

    public void startup(){
        System.out.println("start the computer!");
        cpu.startup();
        menory.startup();
        disk.startup();
        System.out.println("start computer finished!");
    }

    public void shutdown(){
        System.out.println("begin to close the computer!");
        cpu.shutdown();
        menory.shutdown();
        disk.shutdown();
        System.out.println("computer closed!");
    }
}
