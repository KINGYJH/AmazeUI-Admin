package com.king.modules.sys.web;

import com.king.common.utils.WebUtils;
import com.king.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Created by YJH
 * on 2017/7/26 10:41.
 * 注释:
 */
@Controller
@RequestMapping("${adminPath}/util")
public class UtilController extends BaseController {

    @RequestMapping(value = "/get_js")
    @ResponseBody
    public String getJs(HttpServletRequest request, HttpServletResponse response, String path) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
        InputStreamReader in = null;
        FileInputStream fi = null;
        LineNumberReader reader = null;
        StringBuffer str = null;
        String s = "";
        try {
            str = new StringBuffer();
            fi = new FileInputStream(WebUtils.getPath() + path);
            in = new InputStreamReader(fi, "UTF-8");
            reader = new LineNumberReader(in);
            while ((s = reader.readLine()) != null) {
                str.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (in != null) in.close();
                if (fi != null) fi.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return str.toString();
        }
    }

}
