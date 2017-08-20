package com.king.modules.sys.file.service.impl;

import com.king.common.upload.FileUploadConfig;
import com.king.common.upload.UploadFailure;
import com.king.common.upload.UploadResult;
import com.king.common.upload.UploadSuccess;
import com.king.common.utils.Encodes;
import com.king.common.utils.StringUtils;
import com.king.common.utils.WebUtils;
import com.king.common.web.JSONMessage;
import com.king.modules.sys.file.service.IFileUploadService;
import com.sun.xml.internal.bind.v2.runtime.output.Encoded;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by YJH
 * on 2017/8/10 14:04.
 * 注释:
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements IFileUploadService {

    //初始化文件用途类型
    public static final Map<String, FileUploadConfig> FILE_TYPE = new HashMap<String, FileUploadConfig>() {{
        put("1", new FileUploadConfig("菜单图标", "/upload/menu_ico/", 10485760, "jpg,png,bmp,ico,gif,jpeg", "/temp/menu_ico/"));
        put("2", new FileUploadConfig("头像图标", "/upload/head_ico/", 10485760, "jpg,png,bmp,ico,gif,jpeg", "/temp/head_ico/"));
    }};

    @Override
    public UploadResult upload(MultipartFile[] files, String type) throws IOException {
        List<Object> resultFiles = new ArrayList<>();
        if (FILE_TYPE.containsKey(type)) {
            if (type.equals("1")) {
                resultFiles = img(files, FILE_TYPE.get(type));
            } else if (type.equals("2")) {
                resultFiles = img(files, FILE_TYPE.get(type));
            }
        } else {
            UploadFailure failure = new UploadFailure("所有文件", "上传参数错误");
            resultFiles.add(failure);
        }
        return new UploadResult(resultFiles.toArray());
    }

    @Override
    public JSONMessage delTemp(String fileName) {
        File file = new File(WebUtils.getPath() + fileName);
        if (file.exists()) {
            file.delete();
        }
        return new JSONMessage(JSONMessage.Status.SUCCESS, "删除成功");
    }

    /**
     * 图片上传
     *
     * @param files 文件集合
     * @return 结果
     */
    private List<Object> img(MultipartFile[] files, FileUploadConfig config) throws IOException {
        List<Object> results = new ArrayList<>();

        File folder = new File(WebUtils.getPath(), config.getTemp());
        folder.mkdirs();
        String url = config.getTemp();
        for (MultipartFile file : files) {
            String message = null;
            if (file.isEmpty()) {
                message = "文件未上传";
            }

            //返回客户端的文件系统中的原始文件名
            String fileName = file.getOriginalFilename();
            //获取文件后缀名
            String type = (fileName.substring(fileName.lastIndexOf(".") + 1)).toLowerCase();
            //获取文件大小
            long fileSize = file.getSize();

            if (file.getSize() > config.getMaxSize()) {
                message = "文件过大,无法上传!";
            }

            if (!config.getSuffixName().contains(type)) {
                message = "文件类型错误!";
            }

            if (StringUtils.isEmpty(message)) {
                String saveFileName = folder + "/" + UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;
                File saveFile = new File(saveFileName);
                file.transferTo(saveFile);
                results.add(new UploadSuccess(fileName, fileSize,
                        url + saveFile.getName(),
                        "/upload/del_temp?fileName=" + url + saveFile.getName()));
            } else {
                results.add(new UploadFailure(file.getOriginalFilename(), message));
            }
        }

        return results;
    }

}
