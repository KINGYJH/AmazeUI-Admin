package com.king.modules.sys.home.service;

import com.king.modules.sys.home.entity.HomeView;
import com.king.modules.sys.home.entity.HomeViewList;

import java.io.IOException;

/**
 * Created by YJH
 * on 2017/7/26 14:48.
 * 注释:
 */
public interface IHomeService {

    HomeViewList getAllView();

    HomeView getById(String id);

    void save(HomeView homeView) throws IOException;

    void edit(HomeView homeView) throws IOException;

    void del(String id) throws IOException;
}
