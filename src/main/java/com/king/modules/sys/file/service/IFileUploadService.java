package com.king.modules.sys.file.service;

import com.king.common.upload.UploadResult;
import com.king.common.web.JSONMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by YJH
 * on 2017/8/10 14:04.
 * 注释:
 */
public interface IFileUploadService {
    UploadResult upload(MultipartFile[] files, String type) throws IOException;

    JSONMessage delTemp(String fileName);
}
