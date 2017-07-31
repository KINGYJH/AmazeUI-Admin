package com.king.common.utils;

import java.io.*;

/**
 * Created by YJH
 * on 2017/7/26 13:51.
 * 注释: 文件工具类
 */
public class FileUtils {

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return 结果存在true 反之false
     */
    public static boolean isExists(String filePath) {
        return isExists(new File(filePath));
    }

    /**
     * 判断文件是否存在
     *
     * @param file 判断文件
     * @return 结果存在true 反之false
     */
    public static boolean isExists(File file) {
        return file.exists();
    }

    /**
     * 复制文件
     *
     * @param srcFileName  源文件
     * @param descFileName 目标文件
     * @param isCover      是否覆盖
     * @return 如果复制成功，则返回true，否则返回false
     */
    public static boolean copyFileCover(String srcFileName, String descFileName, boolean isCover) {
        File srcFile = new File(srcFileName);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            return false;
        }
        // 判断源文件是否是合法的文件
        else if (!srcFile.isFile()) {
            return false;
        }
        File descFile = new File(descFileName);
        // 判断目标文件是否存在
        if (descFile.exists()) {
            // 如果目标文件存在，并且允许覆盖
            if (isCover) {
                if (!FileUtils.delFile(descFileName)) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (!descFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建目录
                // 创建目标文件所在的目录
                if (!descFile.getParentFile().mkdirs()) {
                    return false;
                }
            }
        }

        // 准备复制文件
        // 读取的位数
        int readByte;
        InputStream ins = null;
        OutputStream outs = null;
        try {
            // 打开源文件
            ins = new FileInputStream(srcFile);
            // 打开目标文件的输出流
            outs = new FileOutputStream(descFile);
            byte[] buf = new byte[1024];
            // 一次读取1024个字节，当readByte为-1时表示文件已经读取完毕
            while ((readByte = ins.read(buf)) != -1) {
                // 将读取的字节流写入到输出流
                outs.write(buf, 0, readByte);
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            // 关闭输入输出流，首先关闭输出流，然后再关闭输入流
            if (outs != null) {
                try {
                    outs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件，可以删除单个文件或文件夹
     *
     * @param fileName 被删除的文件名
     * @return 如果删除成功，则返回true，否是返回false
     */
    public static boolean delFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return true;
        } else {
            if (file.isFile()) {
                return FileUtils.deleteFile(fileName) == 0;
            } else {
                return FileUtils.deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除的文件名
     * @return 如果删除成功，则返回0，否则返回1，文件不存在返回2
     */
    public static Integer deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 2;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dirName 被删除的目录所在的文件路径
     * @return 如果目录删除成功，则返回true，否则返回false
     */
    public static boolean deleteDirectory(String dirName) {
        String dirNames = dirName;
        if (!dirNames.endsWith(File.separator)) {
            dirNames = dirNames + File.separator;
        }
        File dirFile = new File(dirNames);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return true;
        }
        boolean flag = true;
        // 列出全部文件及子目录
        File[] files = dirFile.listFiles();
        for (File file : files) {
            // 删除子文件
            if (file.isFile()) {
                flag = FileUtils.deleteFile(file.getAbsolutePath()) == 0;
                // 如果删除文件失败，则退出循环
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (file.isDirectory()) {
                flag = FileUtils.deleteDirectory(file.getAbsolutePath());
                // 如果删除子目录失败，则退出循环
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            return false;
        }
        // 删除当前目录
        return dirFile.delete();
    }

    /**
     * 创建单个文件
     *
     * @param descFileName 文件名，包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static boolean createFile(String descFileName) {
        File file = new File(descFileName);
        if (file.exists()) {
            return false;
        }
        if (descFileName.endsWith(File.separator)) {
            return false;
        }
        if (!file.getParentFile().exists()) {
            // 如果文件所在的目录不存在，则创建目录
            if (!file.getParentFile().mkdirs()) {
                return false;
            }
        }

        // 创建文件
        try {
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建单个文件
     *
     * @param descFileName 文件名，包含路径
     * @param sourceStr    写入文件的字符串
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static void createFile(String descFileName, String sourceStr) throws IOException {
        File file = new File(descFileName);
        if (file.exists()) {
            file.delete();
        }
        if (!file.getParentFile().exists()) {
            // 如果文件所在的目录不存在，则创建目录
            file.getParentFile().mkdirs();
        }

        // 创建文件
        file.createNewFile();

        OutputStreamWriter os = null;
        BufferedWriter bw = null;
        os = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        bw = new BufferedWriter(os);
        bw.append(sourceStr);

        bw.close();
        os.close();
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream is = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }
}
