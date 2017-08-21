package com.king.common.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * Created by YJH
 * on 2017/8/21 10:34.
 * 注释: 图片工具类
 */
public class ImgUtils {

    /**
     * 图片压缩
     *
     * @param sourceFile      源文件
     * @param targetFile      目标文件
     * @param width           宽度
     * @param height          高度
     * @param keepAspectRatio 是否按比例缩放
     */
    public static void saveImg(File sourceFile, String targetFile, int width, int height, boolean keepAspectRatio) {
        try {
            Thumbnails.of(sourceFile).size(width, height).keepAspectRatio(keepAspectRatio).toFile(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
