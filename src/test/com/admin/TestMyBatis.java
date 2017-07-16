package com.admin;

import com.king.common.persistence.search.Criterion;
import com.king.common.persistence.search.CriterionEnum;
import com.king.common.persistence.search.MatchMode;
import com.king.common.persistence.search.Restrictions;
import com.king.common.web.Pagination;
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
import java.util.ArrayList;
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
        menuService.isHasChild("sys_menu_0000001");
        System.out.println("");
    }

    @Test
    public void testPagination() {
        Menu menu = new Menu();
        menu.setName("and name like '%菜单%'");
        Pagination<Menu> pagination = new Pagination<>();
        pagination.setPageNumber(1);
        pagination.setPageSize(20);
        pagination.setDataStartNumber(0);
        pagination.setOrderBy("'sort ASC'");
        pagination.setQueryEntity(menu);

        pagination = menuService.pagination(pagination);

        System.out.println("1");
    }

    @Test
    public void testCriterion() {
        List<Criterion> criteria = new ArrayList<>();
        criteria.add(Restrictions.eq("name", "菜单"));
        criteria.add(Restrictions.like("name", "菜单", MatchMode.ALL));
        criteria.add(Restrictions.gt("name", 2));
        criteria.add(Restrictions.or(Restrictions.eq("sort", 4), Restrictions.gt("name", 6)));
        StringBuilder sb = new StringBuilder();
        for (Criterion criterion : criteria) {
            sb.append(criterion.toSqlStr());
        }
        System.out.println(sb.toString());
    }
}
