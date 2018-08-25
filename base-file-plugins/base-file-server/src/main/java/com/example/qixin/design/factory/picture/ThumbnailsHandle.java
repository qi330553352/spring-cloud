package com.example.qixin.design.factory.picture;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/** Thumbnails框架处理图片
 * 创 建  时 间： 2018/8/25 11:45
 * 版           本:  V1.0
 * 作           者:  qixin
 */
public class ThumbnailsHandle implements Handle{

    private File file;

    private ThumbnailsHandle() {

    }

    ThumbnailsHandle(File file) {
        this.file = file;
    }

    @Override
    public InputStream thumbnail(int width, int height) throws IOException {
        Thumbnails.Builder<File> builder = Thumbnails.of(file).size(width,height).outputQuality(0.8);
        BufferedImage image = builder.asBufferedImage();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String[] strs = file.getName().split("\\.");
        String formatName = strs[strs.length-1];
        ImageIO.write(image, formatName, os);
        return new ByteArrayInputStream(os.toByteArray());
    }


    @Override
    public InputStream watermark(Position position, BufferedImage bufferedImage, float arg0) throws IOException {
        Thumbnails.Builder<File> builder = Thumbnails.of(file).scale(1f).watermark(position,bufferedImage,arg0).outputQuality(0.8);
        BufferedImage image = builder.asBufferedImage();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String[] strs = file.getName().split("\\.");
        String formatName = strs[strs.length-1];
        ImageIO.write(image, formatName, os);
        return new ByteArrayInputStream(os.toByteArray());
    }

    @Override
    public InputStream watermark(int width, int height, Position position, BufferedImage bufferedImage, float arg0) throws IOException {
        Thumbnails.Builder<File> builder = Thumbnails.of(file).size(width,height).watermark(position,bufferedImage,arg0).outputQuality(0.8);
        BufferedImage image = builder.asBufferedImage();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String[] strs = file.getName().split("\\.");
        String formatName = strs[strs.length-1];
        ImageIO.write(image, formatName, os);
        return new ByteArrayInputStream(os.toByteArray());
    }
}
