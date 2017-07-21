package com.admin;

import com.king.common.web.TreeNode;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import com.king.modules.sys.sequence.util.SequenceUtil;
import com.king.modules.sys.user.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/2 20:06
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-test.xml"})
public class TestMyBatis {

    @Resource
    private IUserService userService = null;

    @Resource
    private IMenuService menuService = null;

    @Test
    public void testSave() {
        Menu menu = new Menu();
        menu.setName("123");
        menu.setPermission("123");
        menuService.save(menu);
    }

    @Test
    public void testFindAll() {
        List<Menu> menus = menuService.findAll();
        System.out.println("");
    }

    @Test
    public void testTreeNodeMenu() {
        List<TreeNode> list = menuService.getTreeNode(null);
        System.out.println("");
    }

    @Test
    public void testSequenceId() {
        String id = SequenceUtil.getNextId("sys_sequence");
        System.out.println(id);
    }
}
