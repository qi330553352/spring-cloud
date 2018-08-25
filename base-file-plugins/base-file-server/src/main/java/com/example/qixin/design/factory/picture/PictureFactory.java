package com.example.qixin.design.factory.picture;

import java.io.File;

/**
 * 创 建  时 间： 2018/8/25 12:49
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public class PictureFactory {

    private PictureFactory() {
    }

    public static Handle instance(File file){

        return new ThumbnailsHandle(file);
    }
}
