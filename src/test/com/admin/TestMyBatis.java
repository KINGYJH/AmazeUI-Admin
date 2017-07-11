package com.admin;

import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import com.king.modules.sys.user.entity.User;
import com.king.modules.sys.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/2 20:06
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestMyBatis {

    @Resource
    private IUserService userService = null;

    @Resource
    private IMenuService menuService = null;

    @Test
    public void test1() {
        List<Menu> menu = menuService.findByParentId("menu_000001");
        System.out.println("");
    }
}
