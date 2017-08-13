package com.king.modules.sys.file.web;

import com.king.common.upload.UploadResult;
import com.king.common.utils.StringUtils;
import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.modules.sys.file.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * Created by YJH
 * on 2017/8/10 14:00.
 * 注释:
 */
@Controller
@RequestMapping
public class FileUploadController extends BaseController {

    @Autowired
    private IFileUploadService fileUploadService;

    @RequestMapping(value = "${adminPath}/upload_page")
    public ModelAndView uploadPage(String type, Model model) {
        if (StringUtils.isBlank(type)) {
            model.addAttribute("alertMessage", "参数错误，请重试!");
        }
        model.addAttribute("type", type);
        return new ModelAndView("/pages/sys/upload/FileUpload");
    }

    @RequestMapping(value = "${adminPath}/upload", method = RequestMethod.POST)
    @ResponseBody
    public UploadResult upload(MultipartFile[] files, String type) throws IOException {
        return fileUploadService.upload(files, type);
    }

    @RequestMapping(value = "${adminPath}/upload/del_temp")
    @ResponseBody
    public JSONMessage delTemp(String fileName) {
        return fileUploadService.delTemp(fileName);
    }
}
