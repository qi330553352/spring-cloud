package com.example.qixin.design.factory.picture;

import net.coobird.thumbnailator.geometry.Position;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * 创 建  时 间： 2018/8/25 10:40
 * 版           本:  V1.0
 * 作           者:  qixin
 * Copyright (c) 2018, 中软国际.
 */
public interface Handle {

    /**
     * 按指定大小把图片进行缩放（会遵循原图高宽比例）
     * @param width 高度
     * @param height 宽度
     * @return 处理后的输入流
     * @author qixin
     */
    InputStream thumbnail(int width,int height) throws IOException;

    /**
     * 给图片加水印
     * @param position 位置
     * @param bufferedImage 水印图
     * @param arg0 透明度
     * @return 处理后的输入流
     * @author qixin
     */
    InputStream watermark(Position position, BufferedImage bufferedImage,float arg0) throws IOException;

    /**
     * 按指定大小将图片绽放，并给图片加水印
     * @param width 宽度
     * @param height 高度
     * @param position 水印位置
     * @param bufferedImage 水印图片
     * @param arg0 透明度
     * @return 处理后的输入流
     * @author qixin
     */
    InputStream watermark(int width,int height,Position position, BufferedImage bufferedImage,float arg0) throws IOException;
}
