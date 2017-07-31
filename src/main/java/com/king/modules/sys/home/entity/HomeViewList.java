package com.king.modules.sys.home.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by YJH
 * on 2017/7/26 15:46.
 * 注释:
 */
//设置生成的xml的根节点的名称
@XmlRootElement(name = "MenuViews")
public class HomeViewList implements Serializable {

    private List<HomeView> homeViews = new ArrayList<>();

    @XmlElement(name = "MenuView")
    public List<HomeView> getHomeViews() {
        return homeViews;
    }

    public void setHomeViews(List<HomeView> homeViews) {
        this.homeViews = homeViews;
    }

    public HomeViewList sort() {
        Collections.sort(this.homeViews, new Comparator<HomeView>() {
            @Override
            public int compare(HomeView o1, HomeView o2) {
                return o1.getSort().compareTo(o2.getSort());
            }
        });
        return this;
    }

    public List<HomeView> getShowHomeViews() {
        List<HomeView> returnList = new ArrayList<>();
        for (HomeView item : this.homeViews) {
            if (item.getIsShow().equals("0")) {
                returnList.add(item);
            }
        }
        return returnList;
    }
}
