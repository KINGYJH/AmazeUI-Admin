$(function () {
    var menu_tree_id = mini.get("menu_tree_id");
    menu_tree_id.load(pojectpath + "/sys/menu/user_tree_data");
    // $('#menu_tree_id').tree({
    //     url: pojectpath + "/sys/menu/user_tree_data",//数据加载地址
    //     ajaxType: "post",//ajax类型：get/post。
    //     showTreeIcon: true,//显示节点图标
    //     showTreeLines: true,//显示树形线条
    //     expandOnNodeClick: true,//单击节点展开收缩
    //     nodecheck: function (sender, node, isLeaf) {
    //         if (node.attributes.url === "") {
    //             return;
    //         }
    //         if ($('#menu_tree_id').tree('isLeaf', node.target)) {
    //             if (node.attributes.url.indexOf("#basepath#") != -1) {
    //                 addTab(node.text, node.attributes.url
    //                         .replace("#basepath#", basePath),
    //                     "iframe" + node.id);
    //             } else {
    //                 addTab(node.text, pojectpath
    //                     + node.attributes.url, "iframe"
    //                     + node.id);
    //             }
    //         }
    //     }
    // });
});

function addTab(subtitle, url, id) {
    //add tab
    mini.parse();
    var tabs = mini.get("tabs");

    var tab = {title: subtitle, url: url, showCloseButton: true, id: id};
    //当tabs销毁时
    tab.ondestroy = function (e) {
        var tabs = e.sender;
        var iframe = tabs.getTabIFrameEl(e.tab);

        //获取子页面返回数据
        var pageReturnData = iframe.contentWindow.getData ? iframe.contentWindow.getData() : "";

        alert(e.tab.removeAction + " : " + pageReturnData);

        //如果禁止销毁的时候，自动active一个新tab：e.autoActive = false;
    }
    tabs.addTab(tab);

    //active tab
    tabs.activeTab(tab);
}