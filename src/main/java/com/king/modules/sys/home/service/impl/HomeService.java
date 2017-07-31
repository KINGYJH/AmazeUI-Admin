package com.king.modules.sys.home.service.impl;

import com.king.common.exception.NoFoundException;
import com.king.common.utils.FileUtils;
import com.king.common.utils.JaxbUtil;
import com.king.common.utils.WebUtils;
import com.king.modules.sys.home.entity.HomeView;
import com.king.modules.sys.home.entity.HomeViewList;
import com.king.modules.sys.home.service.IHomeService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by YJH
 * on 2017/7/26 14:48.
 * 注释:
 */
@Service("homeService")
public class HomeService implements IHomeService {

    private static final String HOME_VIEW_CONFIG_PATH = "\\config\\homeViewConfig.xml";

    @Override
    public HomeViewList getAllView() {
        File file = new File(WebUtils.getPath() + HOME_VIEW_CONFIG_PATH);
        if (!file.exists()) {
            FileUtils.createFile(file.getPath());
        }
        String xmlStr = FileUtils.readFileByLines(file.getPath());
        HomeViewList data = JaxbUtil.fromXml(xmlStr, HomeViewList.class);
        return data == null ? new HomeViewList() : data.sort();
    }

    @Override
    public HomeView getById(String id) {
        List<HomeView> list = getAllView().getHomeViews();
        for (HomeView item : list) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new NoFoundException("id为[" + id + "]的数据未找到");
    }

    @Override
    public void save(HomeView homeView) throws IOException {
        homeView.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        HomeViewList homeViewList = getAllView();
        homeViewList.getHomeViews().add(homeView);
        String xmlStr = JaxbUtil.toXml(homeViewList);
        FileUtils.createFile(WebUtils.getPath() + HOME_VIEW_CONFIG_PATH, xmlStr);
    }

    @Override
    public void edit(HomeView homeView) throws IOException {
        HomeViewList homeViewList = getAllView();
        for (HomeView item : homeViewList.getHomeViews()) {
            if (item.getId().equals(homeView.getId())) {
                homeViewList.getHomeViews().remove(item);
                break;
            }
        }
        homeViewList.getHomeViews().add(homeView);
        String xmlStr = JaxbUtil.toXml(homeViewList);
        FileUtils.createFile(WebUtils.getPath() + HOME_VIEW_CONFIG_PATH, xmlStr);
    }

    @Override
    public void del(String id) throws IOException {
        HomeViewList homeViewList = getAllView();
        for (HomeView item : homeViewList.getHomeViews()) {
            if (item.getId().equals(id)) {
                homeViewList.getHomeViews().remove(item);
                break;
            }
        }
        String xmlStr = JaxbUtil.toXml(homeViewList);
        FileUtils.createFile(WebUtils.getPath() + HOME_VIEW_CONFIG_PATH, xmlStr);
    }
}
