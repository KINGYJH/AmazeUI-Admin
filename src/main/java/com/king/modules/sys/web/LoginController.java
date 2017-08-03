package com.king.modules.sys.web;

import com.king.common.config.Global;
import com.king.common.shiro.LoginEntity;
import com.king.common.utils.HttpUtils;
import com.king.common.utils.VerifyCodeUtils;
import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.modules.sys.user.entity.User;
import com.king.modules.sys.user.service.IAuthService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by YJH
 * on 2017/8/3 9:07.
 * 注释:
 */
@Controller
public class LoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("/pages/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage login(LoginEntity login, HttpServletRequest request) {
        boolean flag = login.getCaptcha().equals(request.getSession().getAttribute("code").toString());
        if (flag) {
            try {
                String loginIP = HttpUtils.getClientIP(request);
                String loginPlatform = HttpUtils.getLoginPlatform(request);
                login.setLoginIP(loginIP);
                login.setLoginPlatform(loginPlatform);

                User user = authService.login(login);

                Subject subject = SecurityUtils.getSubject();

                logger.info(subject.getPrincipal() + "登录成功！时间:" + new Date());
                request.getSession().setAttribute(Global.SESSION_USER, user);

                return new JSONMessage(JSONMessage.Status.SUCCESS, "登录成功");
            } catch (UnknownAccountException ue) {
                return new JSONMessage(JSONMessage.Status.FAIL, "用户不存在");
            } catch (LockedAccountException e) {
                return new JSONMessage(JSONMessage.Status.FAIL, "账户已禁用,请联系管理员.");
            } catch (IncorrectCredentialsException e) {
                return new JSONMessage(JSONMessage.Status.FAIL, "用户名或密码错误");
            } catch (Exception e) {
                return new JSONMessage(JSONMessage.Status.FAIL, "系统错误,请稍后再试");
            }
        } else {
            return new JSONMessage(JSONMessage.Status.FAIL, "验证码错误");
        }
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/verificationCode")
    @ResponseBody
    public void verificationCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        //生成4位随机验证码
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4).toLowerCase();
        session.setAttribute("code", verifyCode);

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

}
