package com.admin;

import com.king.common.utils.EncryptionUtils;
import com.king.common.utils.JaxbUtils;
import com.king.modules.sys.home.entity.HomeView;
import com.king.modules.sys.home.entity.HomeViewList;
import org.junit.Test;

/**
 * Created by YJH
 * on 2017/7/26 15:08.
 * 注释:
 */
public class JavaTest {

    @Test
    public void testJavaToXml() {
        HomeViewList list = new HomeViewList();
        HomeView entity = new HomeView();
        entity.setId("1");
        entity.setTitle("标题");
        entity.setIsShow("0");
        entity.setSort(0);
        entity.setWidth("500px");
        entity.setUrl("/sys/home");
        HomeView entity_1 = new HomeView();
        entity_1.setId("2");
        entity_1.setTitle("标题2");
        entity_1.setIsShow("1");
        entity_1.setSort(0);
        entity_1.setWidth("500px");
        entity_1.setUrl("/sys/home");
        list.getHomeViews().add(entity);
        list.getHomeViews().add(entity_1);
        System.out.println(JaxbUtils.toXml(list));
    }

    @Test
    public void testXmlToJava() {
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<MenuViews>\n" +
                "    <MenuView>\n" +
                "        <id>1</id>\n" +
                "        <isShow>0</isShow>\n" +
                "        <sort>0</sort>\n" +
                "        <title>标题</title>\n" +
                "        <url>/sys/home</url>\n" +
                "        <width>500px</width>\n" +
                "    </MenuView>\n" +
                "    <MenuView>\n" +
                "        <id>2</id>\n" +
                "        <isShow>1</isShow>\n" +
                "        <sort>0</sort>\n" +
                "        <title>标题2</title>\n" +
                "        <url>/sys/home</url>\n" +
                "        <width>500px</width>\n" +
                "    </MenuView>\n" +
                "</MenuViews>";

        HomeViewList list = JaxbUtils.fromXml(xmlStr, HomeViewList.class);
        System.out.println("");
    }

    @Test
    public void md5(){
        String str = EncryptionUtils.MD5("123456");
        System.out.println(str);
    }
}
